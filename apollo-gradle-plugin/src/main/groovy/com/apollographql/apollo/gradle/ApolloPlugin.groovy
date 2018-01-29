package com.apollographql.apollo.gradle

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.api.BaseVariant
import com.google.common.collect.ImmutableList
import com.moowork.gradle.node.NodeExtension
import com.moowork.gradle.node.NodePlugin
import org.gradle.api.DomainObjectCollection
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.internal.file.FileResolver
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.compile.AbstractCompile
import org.gradle.api.tasks.compile.JavaCompile

import javax.inject.Inject

class ApolloPlugin implements Plugin<Project> {
  private static final String NODE_VERSION = "6.7.0"
  public static final String TASK_GROUP = "apollo"

  private Project project
  private final FileResolver fileResolver

  @Inject
  public ApolloPlugin(FileResolver fileResolver) {
    this.fileResolver = fileResolver
  }

  @Override void apply(Project project) {
    this.project = project
    if (project.plugins.hasPlugin(AppPlugin) || project.plugins.hasPlugin(LibraryPlugin) || project.plugins.hasPlugin(
        JavaPlugin)) {
      applyApolloPlugin()
    } else {
      throw new IllegalArgumentException(
          "Apollo plugin couldn't be applied. The Android or Java plugin must be configured first")
    }
  }

  private void applyApolloPlugin() {
    setupNode()
    project.extensions.create(ApolloExtension.NAME, ApolloExtension)
    createSourceSetExtensions()

    project.tasks.create(ApolloCodeGenInstallTask.NAME, ApolloCodeGenInstallTask.class)
    addApolloTasks()
  }

  private void addApolloTasks() {
    Task apolloIRGenTask = project.task("generateApolloIR")
    apolloIRGenTask.group(TASK_GROUP)
    Task apolloClassGenTask = project.task("generateApolloClasses")
    apolloClassGenTask.group(TASK_GROUP)

    if (isAndroidProject()) {
      getVariants().all { v ->
        addVariantTasks(v, apolloIRGenTask, apolloClassGenTask, v.sourceSets)
      }
      project.android.testVariants.each { tv ->
        addVariantTasks(tv, apolloIRGenTask, apolloClassGenTask, tv.sourceSets)
      }
    } else {
      getSourceSets().all { sourceSet ->
        addSourceSetTasks(sourceSet, apolloIRGenTask, apolloClassGenTask)
      }
    }
  }

  private void addVariantTasks(BaseVariant variant, Task apolloIRGenTask, Task apolloClassGenTask, Collection<?> sourceSets) {
    ApolloIRGenTask variantIRTask = createApolloIRGenTask(variant.name, sourceSets)
    ApolloClassGenTask variantClassTask = createApolloClassGenTask(variant.name)
    variant.registerJavaGeneratingTask(variantClassTask, variantClassTask.outputDir)
    apolloIRGenTask.dependsOn(variantIRTask)
    apolloClassGenTask.dependsOn(variantClassTask)
  }

  private void addSourceSetTasks(SourceSet sourceSet, Task apolloIRGenTask, Task apolloClassGenTask) {
    String taskName = "main".equals(sourceSet.name) ? "" : sourceSet.name

    ApolloIRGenTask sourceSetIRTask = createApolloIRGenTask(sourceSet.name, [sourceSet])
    ApolloClassGenTask sourceSetClassTask = createApolloClassGenTask(sourceSet.name)
    apolloIRGenTask.dependsOn(sourceSetIRTask)
    apolloClassGenTask.dependsOn(sourceSetClassTask)

    JavaCompile compileTask = (JavaCompile) project.tasks.findByName("compile${taskName.capitalize()}Java")
    compileTask.source += project.fileTree(sourceSetClassTask.outputDir)
    compileTask.dependsOn(apolloClassGenTask)

    sourceSet.java.srcDir(sourceSetClassTask.outputDir)

    AbstractCompile compileKotlinTask = (AbstractCompile) project.tasks.findByName(
        "compile${taskName.capitalize()}Kotlin")
    if (compileKotlinTask != null) {
      // kotlinc uses the generated java classes as input too so we need the generated classes
      compileKotlinTask.dependsOn(apolloClassGenTask)
      // this is somewhat redundant with sourceSet.java.srcDir above but I believe by the time we come here the java plugin
      // has been configured already so we need to manually tell kotlinc where to find the generated classes
      compileKotlinTask.source(sourceSetClassTask.outputDir)
    }
  }

  private void setupNode() {
    project.plugins.apply NodePlugin
    NodeExtension nodeConfig = project.extensions.findByName("node") as NodeExtension
    nodeConfig.download = true
    nodeConfig.version = NODE_VERSION
  }

  private ApolloIRGenTask createApolloIRGenTask(String sourceSetOrVariantName, Collection<Object> sourceSets) {
    String taskName = String.format(ApolloIRGenTask.NAME, sourceSetOrVariantName.capitalize())
    ApolloIRGenTask task = project.tasks.create(taskName, ApolloIRGenTask) {
      group = TASK_GROUP
      description = "Generate an IR file using apollo-codegen for ${sourceSetOrVariantName.capitalize()} GraphQL queries"
      dependsOn(ApolloCodeGenInstallTask.NAME)
      sourceSets.each { sourceSet ->
        inputs.files(sourceSet.graphql).skipWhenEmpty()
      }
      schemaFilePath = project.apollo.schemaFilePath
      outputPackageName = project.apollo.outputPackageName
    }
    ImmutableList.Builder<String> sourceSetNamesList = ImmutableList.builder()
    sourceSets.each { sourceSet -> sourceSetNamesList.add(sourceSet.name) }
    task.init(sourceSetOrVariantName, sourceSetNamesList.build())
    return task
  }

  private ApolloClassGenTask createApolloClassGenTask(String name) {
    String taskName = String.format(ApolloClassGenTask.NAME, name.capitalize())
    return project.tasks.create(taskName, ApolloClassGenTask) {
      group = TASK_GROUP
      description = "Generate Android classes for ${name.capitalize()} GraphQL queries"
      dependsOn(getProject().getTasks().findByName(String.format(ApolloIRGenTask.NAME, name.capitalize())))
      source = project.tasks.findByName(String.format(ApolloIRGenTask.NAME, name.capitalize())).outputFolder
      include "**${File.separatorChar}*API.json"
      customTypeMapping = project.apollo.customTypeMapping
      nullableValueType = project.apollo.nullableValueType
      useSemanticNaming = project.apollo.useSemanticNaming
      generateModelBuilder = project.apollo.generateModelBuilder
      useJavaBeansSemanticNaming = project.apollo.useJavaBeansSemanticNaming
      outputPackageName = project.apollo.outputPackageName
    }
  }

  private void createSourceSetExtensions() {
    getSourceSets().all { sourceSet ->
      sourceSet.extensions.create(GraphQLSourceDirectorySet.NAME, GraphQLSourceDirectorySet, sourceSet.name,
          fileResolver)
    }
  }

  private boolean isAndroidProject() {
    return project.hasProperty('android') && project.android.sourceSets
  }

  private Object getSourceSets() {
    return (isAndroidProject() ? project.android.sourceSets : project.sourceSets)
  }

  private DomainObjectCollection<BaseVariant> getVariants() {
    return project.android.hasProperty(
        'libraryVariants') ? project.android.libraryVariants : project.android.applicationVariants
  }
}
