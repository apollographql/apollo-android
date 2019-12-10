package com.apollographql.apollo.gradle.internal

import com.apollographql.apollo.gradle.api.CompilationUnit
import com.apollographql.apollo.gradle.api.CompilerParams
import org.gradle.api.Project
import org.gradle.api.file.*
import java.io.File
import javax.inject.Inject

abstract class DefaultCompilationUnit @Inject constructor(
    val project: Project,
    val apolloExtension: DefaultApolloExtension,
    val apolloVariant: ApolloVariant,
    val service: DefaultService,
    override val kotlin: Boolean
) : CompilationUnit, CompilerParams by project.objects.newInstance(DefaultCompilerParams::class.java) {

  final override val androidVariant = apolloVariant.androidVariant
  final override val variantName = apolloVariant.name
  final override val serviceName = service.name

  override val name = "${variantName}${serviceName.capitalize()}${if (kotlin) "Kotlin" else "Java"}"

  abstract override val outputDir: DirectoryProperty
  abstract override val transformedQueriesDir: DirectoryProperty

  private fun resolveSchema(graphqlSourceDirectorySet: SourceDirectorySet): File {
    if (service.schemaPath.isPresent) {
      val schemaPath = service.schemaPath.get()
      if (schemaPath.startsWith(File.separator)) {
        return project.file(schemaPath)
      } else if (schemaPath.startsWith("..")) {
        return project.file("src/main/graphql/$schemaPath").normalize()
      } else {
        val all = apolloVariant.sourceSetNames.map {
          project.file("src/$it/graphql/$schemaPath")
        }

        val candidates = all.filter {
          it.exists()
        }

        require(candidates.size <= 1) {
          "ApolloGraphQL: duplicate(s) schema file(s) found:\n${candidates.map { it.absolutePath }.joinToString("\n")}"
        }
        require(candidates.size == 1) {
          "ApolloGraphQL: cannot find a schema file at $schemaPath. Tried:\n${all.map { it.absolutePath }.joinToString("\n")}"
        }

        return candidates.first()
      }
    } else {
      val candidates = graphqlSourceDirectorySet.srcDirs.flatMap {
        it.walkTopDown().filter { it.name == "schema.json" }.toList()
      }

      require(candidates.size <= 1) {
        multipleSchemaError(candidates)
      }
      require(candidates.size == 1) {
        "ApolloGraphQL: cannot find schema.json. Please specify it explicitely. Looked under:\n" +
            graphqlSourceDirectorySet.srcDirs.map { it.absolutePath }.joinToString("\n")
      }
      return candidates.first()
    }
  }

  fun setSourcesIfNeeded(graphqlSourceDirectorySet: SourceDirectorySet, schemaFile: RegularFileProperty) {
    if (graphqlSourceDirectorySet.srcDirs.isEmpty()) {
      if (schemaFile.isPresent) {
        graphqlSourceDirectorySet.srcDir(schemaFile.asFile.get().parent)
      } else {
        val sourceFolder = service.sourceFolder.orElse(".").get()
        if (sourceFolder.startsWith(File.separator)) {
          graphqlSourceDirectorySet.srcDir(sourceFolder)
        } else if (sourceFolder.startsWith("..")) {
          graphqlSourceDirectorySet.srcDir(project.file("src/main/graphql/$sourceFolder").normalize())
        } else {
          apolloVariant.sourceSetNames.forEach {
            graphqlSourceDirectorySet.srcDir("src/$it/graphql/$sourceFolder")
          }
        }
      }

      graphqlSourceDirectorySet.include("**/*.graphql", "**/*.gql")
      graphqlSourceDirectorySet.exclude(service.exclude.getOrElse(emptyList()))
    }

    if (!schemaFile.isPresent) {
      schemaFile.set { resolveSchema(graphqlSourceDirectorySet) }
    }
  }

  companion object {
    fun createDefaultCompilationUnit(
        project: Project,
        apolloExtension: DefaultApolloExtension,
        apolloVariant: ApolloVariant,
        service: DefaultService,
        kotlin: Boolean
    ): DefaultCompilationUnit {
      return project.objects.newInstance(DefaultCompilationUnit::class.java,
          project,
          apolloExtension,
          apolloVariant,
          service,
          kotlin
      ).apply {
        graphqlSourceDirectorySet.include("**/*.graphql", "**/*.gql")
      }
    }

    private fun multipleSchemaError(schemaList: List<File>): String {
      val services = schemaList.joinToString("\n") {
        """|
          |  service("${it.parentFile.name}") {
          |    sourceFolder = "${it.parentFile.normalize().absolutePath}"
          |  }
        """.trimMargin()
      }
      return "ApolloGraphQL: By default only one schema.json file is supported.\nPlease use multiple services instead:\napollo {\n$services\n}"
    }
  }
}

