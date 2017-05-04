package com.apollographql.apollo.gradle.unit

import com.apollographql.apollo.gradle.ApolloExtension
import com.apollographql.apollo.gradle.ApolloIRGenTask
import com.apollographql.apollo.gradle.ApolloPlugin
import com.apollographql.apollo.gradle.ApolloPluginTestHelper
import com.apollographql.apollo.gradle.GraphQLSourceDirectorySet
import com.moowork.gradle.node.NodePlugin
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class ApolloAndroidPluginSpec extends Specification {
  def "creates an IRGen task under the apollo group for a default project"() {
    setup:
    def project = ProjectBuilder.builder().build()
    ApolloPluginTestHelper.setupDefaultAndroidProject(project)

    when:
    ApolloPluginTestHelper.applyApolloPlugin(project)
    project.evaluate()

    def debugTask = project.tasks.getByName(String.format(ApolloIRGenTask.NAME, "Debug"))
    def releaseTask = project.tasks.getByName(String.format(ApolloIRGenTask.NAME, "Release"))

    then:
    debugTask.group.equals(ApolloPlugin.TASK_GROUP)
    debugTask.description.equals("Generate an IR file using apollo-codegen for Debug GraphQL queries")

    releaseTask.group.equals(ApolloPlugin.TASK_GROUP)
    releaseTask.description.equals("Generate an IR file using apollo-codegen for Release GraphQL queries")
  }

  def "creates an IRGen task under the apollo group for a product-flavoured project"() {
    setup:
    def project = ProjectBuilder.builder().build()
    def flavors = ["Demo", "Full"]
    ApolloPluginTestHelper.setupAndroidProjectWithProductFlavours(project)

    when:
    ApolloPluginTestHelper.applyApolloPlugin(project)
    project.evaluate()

    then:
    flavors.each { flavor ->
      def debugTask = project.tasks.getByName(String.format(ApolloIRGenTask.NAME, "${flavor}Debug"))
      def releaseTask = project.tasks.getByName(String.format(ApolloIRGenTask.NAME, "${flavor}Release"))

      assert (debugTask.group) == ApolloPlugin.TASK_GROUP
      assert (debugTask.description) == "Generate an IR file using apollo-codegen for " + flavor + "Debug GraphQL queries"

      assert (releaseTask.group) == ApolloPlugin.TASK_GROUP
      assert (releaseTask.description) == "Generate an IR file using apollo-codegen for " + flavor + "Release GraphQL queries"
    }
  }

  def "adds the node plugin to the project"() {
    given:
    def project = ProjectBuilder.builder().build()
    ApolloPluginTestHelper.setupDefaultAndroidProject(project)

    when:
    ApolloPluginTestHelper.applyApolloPlugin(project)
    project.evaluate()

    then:
    project.plugins.hasPlugin(NodePlugin.class)
  }

  def "adds a graphql extension for all sourceSets in a default project"() {
    given:
    def project = ProjectBuilder.builder().build()
    ApolloPluginTestHelper.setupDefaultAndroidProject(project)

    when:
    ApolloPluginTestHelper.applyApolloPlugin(project)
    project.evaluate()

    then:
    project.android.sourceSets.all { sourceSet ->
      assert (sourceSet.extensions.findByName("graphql")) != null
      assert (sourceSet.extensions.findByType(GraphQLSourceDirectorySet.class)) != null
    }
  }

  def "adds a graphql extensions for all sourceSets in a product-flavoured project"() {
    given:
    def project = ProjectBuilder.builder().build()
    ApolloPluginTestHelper.setupAndroidProjectWithProductFlavours(project)

    when:
    ApolloPluginTestHelper.applyApolloPlugin(project)
    project.evaluate()

    then:
    project.android.sourceSets.all { sourceSet ->
      assert (sourceSet.extensions.findByName("graphql")) != null
      assert (sourceSet.extensions.findByType(GraphQLSourceDirectorySet.class)) != null
    }
  }

  def "adds apollo project-level extension"() {
    given:
    def project = ProjectBuilder.builder().build()
    ApolloPluginTestHelper.setupAndroidProjectWithProductFlavours(project)

    when:
    ApolloPluginTestHelper.applyApolloPlugin(project)
    project.evaluate()

    then:
    assert (project.extensions.findByName("apollo")) != null
    assert (project.extensions.findByType(ApolloExtension.class)) != null
  }

  def "adds apollo-runtime dependency if not skipped and not found in compile dep list"() {
    given:
    def project = ProjectBuilder.builder().build()
    ApolloPluginTestHelper.setupDefaultAndroidProject(project)

    when:
    ApolloPluginTestHelper.applyApolloPlugin(project)
    project.evaluate()

    then:
    def apolloRuntime = project.configurations.getByName("compile").dependencies.find {
      it.group == "com.apollographql.apollo" && it.name == "apollo-runtime"
    }
    assert apolloRuntime != null
  }

  def "doesn't add apollo-runtime dependency if skip property is set" () {
    given:
    def project = ProjectBuilder.builder().build()
    ApolloPluginTestHelper.setupDefaultAndroidProject(project)

    project.beforeEvaluate {
      System.setProperty("apollographql.skipRuntimeDep", "true")
    }

    when:
    ApolloPluginTestHelper.applyApolloPlugin(project)
    project.evaluate()

    then:
    def compileDepSet = project.configurations.getByName("compile").dependencies
    assert compileDepSet.isEmpty()
  }
}
