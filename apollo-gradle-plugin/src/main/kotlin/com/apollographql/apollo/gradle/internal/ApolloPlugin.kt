package com.apollographql.apollo.gradle.internal

import com.apollographql.apollo.compiler.OperationIdGenerator
import com.apollographql.apollo.compiler.ir.CodeGenerationIR
import com.apollographql.apollo.compiler.operationoutput.OperationDescriptorList
import com.apollographql.apollo.gradle.api.ApolloExtension
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskProvider
import org.gradle.util.GradleVersion
import java.io.File
import java.net.URLDecoder

open class ApolloPlugin : Plugin<Project> {
  companion object {
    const val TASK_GROUP = "apollo"
    const val MIN_GRADLE_VERSION = "6.0"

    val Project.isKotlinMultiplatform get() = pluginManager.hasPlugin("org.jetbrains.kotlin.multiplatform")

    private fun registerCompilationUnits(project: Project, apolloExtension: DefaultApolloExtension, checkVersionsTask: TaskProvider<Task>) {
      val androidExtension = project.extensions.findByName("android")

      val apolloVariants = when {
        project.isKotlinMultiplatform -> KotlinMultiplatformTaskConfigurator.getVariants(project)
        androidExtension != null -> AndroidTaskConfigurator.getVariants(project, androidExtension)
        else -> JvmTaskConfigurator.getVariants(project)
      }

      val rootProvider = project.tasks.register("generateApolloSources") {
        it.group = TASK_GROUP
        it.description = "Generate Apollo models for all services and variants"
      }

      val services = if (apolloExtension.services.isEmpty()) {
        listOf(project.objects.newInstance(DefaultService::class.java, project.objects, "service"))
      } else {
        apolloExtension.services
      }

      apolloVariants.all { apolloVariant ->
        val variantProvider = project.tasks.register("generate${apolloVariant.name.capitalize()}ApolloSources") {
          it.group = TASK_GROUP
          it.description = "Generate Apollo models for all services and variant '${apolloVariant.name}'"
        }

        val compilationUnits = services.map {
          DefaultCompilationUnit.createDefaultCompilationUnit(project, apolloExtension, apolloVariant, it)
        }

        compilationUnits.forEach { compilationUnit ->
          val irTaskProvider = registerIRGenTask(project, compilationUnit)

          compilationUnit.operationDescriptorListFile.set(irTaskProvider.flatMap { it.operationDescriptorListFile })

          val idgenProvider = registerIdGenTask(project, compilationUnit, irTaskProvider.flatMap { it.irFile })

          val codegenProvider = registerCodeGenTask(project, compilationUnit, irTaskProvider.flatMap { it.irFile })

          codegenProvider.configure {
            it.dependsOn(checkVersionsTask)
          }

          variantProvider.configure {
            it.dependsOn(codegenProvider)
          }

          compilationUnit.outputDir.set(codegenProvider.flatMap { it.outputDir })

          /**
           * Order matters here. See https://github.com/apollographql/apollo-android/issues/1970
           * We want to expose the `CompilationUnit` to users before the task is configured by
           * AndroidTaskConfigurator.registerGeneratedDirectory so that schemaFile and sourceDirectorySet are
           * correctly set
           */
          apolloExtension.compilationUnits.add(compilationUnit)

          when {
            project.isKotlinMultiplatform -> {
              KotlinMultiplatformTaskConfigurator.registerGeneratedDirectory(project, compilationUnit, codegenProvider)
            }
            androidExtension != null -> AndroidTaskConfigurator.registerGeneratedDirectory(project, compilationUnit, codegenProvider)
            else -> JvmTaskConfigurator.registerGeneratedDirectory(project, compilationUnit, codegenProvider)
          }
        }

        rootProvider.configure {
          it.dependsOn(variantProvider)
        }
      }
    }

    private fun registerIRGenTask(project: Project, compilationUnit: DefaultCompilationUnit): TaskProvider<ApolloGenerateIRTask> {
      val taskName = "generate${compilationUnit.name.capitalize()}ApolloIR"

      return project.tasks.register(taskName, ApolloGenerateIRTask::class.java) {
        it.group = TASK_GROUP
        it.description = "Generate IR for ${compilationUnit.name.capitalize()} GraphQL queries"

        val (compilerParams, graphqlSourceDirectorySet) = compilationUnit.resolveParams(project)

        it.graphqlFiles.setFrom(graphqlSourceDirectorySet)
        // I'm not sure if gradle is sensitive to the order of the rootFolders. Sort them just in case.
        it.rootFolders.set(project.provider { graphqlSourceDirectorySet.srcDirs.map { it.relativeTo(project.projectDir).path }.sorted() })
        it.schemaFile.set(compilerParams.schemaFile)

        it.rootPackageName.set(compilerParams.rootPackageName)
        if (!graphqlSourceDirectorySet.isEmpty) {
          it.irFile.set(project.layout.buildDirectory.file("apollo/${compilationUnit.variantName}/${compilationUnit.serviceName}/ir.json"))
          it.operationDescriptorListFile.set(project.layout.buildDirectory.file("apollo/${compilationUnit.variantName}/${compilationUnit.serviceName}/operationDescriptorList.json"))
        }
      }
    }

    private fun registerIdGenTask(project: Project, compilationUnit: DefaultCompilationUnit, operationDescriptorListFile: Provider<RegularFile>): TaskProvider<ApolloGenerateDefaultOperationIdsTask> {
      val taskName = "generate${compilationUnit.name.capitalize()}ApolloOperationIDs"

      return project.tasks.register(taskName, ApolloGenerateDefaultOperationIdsTask::class.java) {
        it.group = TASK_GROUP
        it.description = "Generate Apollo operation IDs for ${compilationUnit.name.capitalize()} GraphQL queries"

        val (compilerParams, _) = compilationUnit.resolveParams(project)
        it.operationIdGenerator = compilerParams.operationIdGenerator.orElse(OperationIdGenerator.Sha256()).get()
        it.operationDescriptorListFile.set(operationDescriptorListFile)
        it.operationOutputFile.set(compilationUnit.operationOutputFile)
      }
    }

    private fun registerCodeGenTask(project: Project, compilationUnit: DefaultCompilationUnit, irFileProvider: Provider<RegularFile>): TaskProvider<ApolloGenerateSourcesTask> {
      val taskName = "generate${compilationUnit.name.capitalize()}ApolloSources"

      return project.tasks.register(taskName, ApolloGenerateSourcesTask::class.java) {
        it.group = TASK_GROUP
        it.description = "Generate Apollo models for ${compilationUnit.name.capitalize()} GraphQL queries"

        val (compilerParams, _) = compilationUnit.resolveParams(project)

        it.nullableValueType.set(compilerParams.nullableValueType)
        it.useSemanticNaming.set(compilerParams.useSemanticNaming)
        it.generateModelBuilder.set(compilerParams.generateModelBuilder)
        it.useJavaBeansSemanticNaming.set(compilerParams.useJavaBeansSemanticNaming)
        it.suppressRawTypesWarning.set(compilerParams.suppressRawTypesWarning)
        it.generateKotlinModels.set(compilationUnit.generateKotlinModels())
        it.generateVisitorForPolymorphicDatatypes.set(compilerParams.generateVisitorForPolymorphicDatatypes)
        it.customTypeMapping.set(compilerParams.customTypeMapping)
        it.outputDir.apply {
          set(project.layout.buildDirectory.dir("generated/source/apollo/${compilationUnit.variantName}/${compilationUnit.serviceName}"))
          disallowChanges()
        }
        it.irFile.set(irFileProvider)
        it.operationOutputFile.apply {
          set(compilationUnit.operationOutputFile.orElse { defaultOperationOutput(project, irFileProvider.get().asFile, compilationUnit.operationIdGenerator.orElse(OperationIdGenerator.Sha256()).get()) })
          disallowChanges()
        }

        it.generateAsInternal.set(compilerParams.generateAsInternal)

        it.kotlinMultiPlatformProject.set(project.isKotlinMultiplatform)
        it.sealedClassesForEnumsMatching.set(compilerParams.sealedClassesForEnumsMatching)
      }
    }

    private fun defaultOperationOutput(project: Project, irFile: File, operationIdGenerator: OperationIdGenerator): File {
      val codegenerationIR = CodeGenerationIR(irFile)
    }


    private fun registerDownloadSchemaTasks(project: Project, apolloExtension: DefaultApolloExtension) {
      apolloExtension.services.forEach { service ->
        val introspection = service.introspection
        if (introspection != null) {
          project.tasks.register("download${service.name.capitalize()}ApolloSchema", ApolloDownloadSchemaTask::class.java) { task ->

            val sourceSetName = introspection.sourceSetName.orElse("main")
            task.group = TASK_GROUP
            task.schemaRelativeToProject.set(
                service.schemaPath.map {
                  "src/${sourceSetName.get()}/graphql/$it"
                }
            )

            task.endpoint.set(introspection.endpointUrl.map {
              it.toHttpUrl().newBuilder()
                  .apply {
                    introspection.queryParameters.get().entries.forEach {
                      addQueryParameter(it.key, it.value)
                    }
                  }
                  .build()
                  .toString()
            }
            )
            task.header = introspection.headers.get().map {
              "${it.key}: ${it.value}"
            }
          }
        }
      }

      project.tasks.register("downloadApolloSchema", ApolloDownloadSchemaCliTask::class.java) { task ->
        task.group = TASK_GROUP
        task.compilationUnits = apolloExtension.compilationUnits
      }
    }

    fun toMap(s: String): Map<String, String> {
      return s.split("&")
          .map {
            val keyValue = it.split("=")
            val key = URLDecoder.decode(keyValue[0], "UTF-8")
            val value = URLDecoder.decode(keyValue[1], "UTF-8")

            key to value
          }.toMap()
    }

    private fun afterEvaluate(project: Project, apolloExtension: DefaultApolloExtension) {
      val checkVersionsTask = registerCheckVersionsTask(project)
      registerCompilationUnits(project, apolloExtension, checkVersionsTask)

      registerDownloadSchemaTasks(project, apolloExtension)
    }

    data class Dep(val name: String, val version: String?)

    fun getDeps(configurations: ConfigurationContainer): List<Dep> {
      return configurations.flatMap { configuration ->
        configuration.incoming.dependencies
            .filter {
              it.group == "com.apollographql.apollo"
            }.map { dependency ->
              Dep(dependency.name, dependency.version)
            }
      }
    }

    fun registerCheckVersionsTask(project: Project): TaskProvider<Task> {
      return project.tasks.register("checkApolloVersions") {
        val outputFile = project.layout.buildDirectory.file("apollo/versionCheck")

        val allDeps = (
            getDeps(project.rootProject.buildscript.configurations) +
                getDeps(project.buildscript.configurations) +
                getDeps(project.configurations)
            )

        val allVersions = allDeps.mapNotNull { it.version }.distinct().sorted()
        it.inputs.property("allVersions", allVersions)
        it.outputs.file(outputFile)

        it.doLast {
          check(allVersions.size <= 1) {
            val found = allDeps.map { "${it.name}:${it.version}" }.distinct().joinToString("\n")
            "All apollo versions should be the same. Found:\n$found"
          }

          val version = allVersions.firstOrNull()
          outputFile.get().asFile.parentFile.mkdirs()
          outputFile.get().asFile.writeText("All versions are consistent: $version")
        }
      }

    }
  }

  override fun apply(project: Project) {
    require(GradleVersion.current().compareTo(GradleVersion.version(MIN_GRADLE_VERSION)) >= 0) {
      "apollo-android requires Gradle version $MIN_GRADLE_VERSION or greater"
    }

    val apolloExtension = project.extensions.create(ApolloExtension::class.java, "apollo", DefaultApolloExtension::class.java, project) as DefaultApolloExtension

    // the extension block has not been evaluated yet, register a callback once the project has been evaluated
    project.afterEvaluate {
      afterEvaluate(it, apolloExtension)
    }
  }
}
