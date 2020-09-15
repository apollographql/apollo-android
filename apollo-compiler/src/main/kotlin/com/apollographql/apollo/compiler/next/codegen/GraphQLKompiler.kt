package com.apollographql.apollo.compiler.next.codegen

import com.apollographql.apollo.compiler.ast.CustomTypes
import com.apollographql.apollo.compiler.codegen.kotlin.KotlinCodeGen.patchKotlinNativeOptionalArrayProperties
import com.apollographql.apollo.compiler.ir.CodeGenerationIR
import com.apollographql.apollo.compiler.ir.ScalarType
import com.apollographql.apollo.compiler.ir.TypeDeclaration
import com.apollographql.apollo.compiler.next.ast.buildCodeGenerationAst
import com.apollographql.apollo.compiler.operationoutput.OperationOutput
import com.apollographql.apollo.compiler.parser.introspection.IntrospectionSchema
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import java.io.File

class GraphQLKompiler(
    private val ir: CodeGenerationIR,
    private val schema: IntrospectionSchema,
    private val customTypeMap: CustomTypes,
    private val useSemanticNaming: Boolean,
    private val generateAsInternal: Boolean = false,
    private val operationOutput: OperationOutput,
    private val kotlinMultiPlatformProject: Boolean,
    private val enumAsSealedClassPatternFilters: List<Regex>
) {
  fun write(outputDir: File) {
    val ast = ir.buildCodeGenerationAst(
        schema = schema,
        customTypeMap = customTypeMap,
        operationOutput = operationOutput,
        useSemanticNaming = useSemanticNaming,
        typesPackageName = ir.typesPackageName,
        fragmentsPackage = ir.fragmentsPackageName
    )

    customTypeMap
        .filterKeys {
          ir.scalarsToGenerate.contains(it)
        }.takeIf {
          /**
           * Skip generating the ScalarType enum if it's empty
           * This happens in multi-module for leaf modules
           */
          it.isNotEmpty()
        }
        ?.let {
          CustomTypes(it)
        }
        ?.typeSpec(generateAsInternal)
        ?.fileSpec(ir.typesPackageName)
        ?.writeTo(outputDir)

    ast.enumTypes.forEach { enumType ->
      enumType
          .typeSpec(
              generateAsInternal = generateAsInternal,
              enumAsSealedClassPatternFilters = enumAsSealedClassPatternFilters
          )
          .fileSpec(ir.typesPackageName)
          .writeTo(outputDir)
    }

    ast.inputTypes.forEach { inputType ->
      inputType
          .typeSpec(generateAsInternal)
          .fileSpec(ir.typesPackageName)
          .writeTo(outputDir)
    }

    ast.operationTypes.forEach { operationType ->
      operationType
          .typeSpec(
              targetPackage = operationType.packageName,
              generateAsInternal = generateAsInternal
          )
          .let {
            if (kotlinMultiPlatformProject) {
              it.patchKotlinNativeOptionalArrayProperties()
            } else it
          }
          .fileSpec(operationType.packageName)
          .writeTo(outputDir)
    }

    ast.fragmentTypes.forEach { fragmentType ->
      fragmentType
          .typeSpec(generateAsInternal)
          .fileSpec(ir.fragmentsPackageName)
          .writeTo(outputDir)
    }
  }

  private fun TypeSpec.fileSpec(packageName: String) =
      FileSpec
          .builder(packageName, name!!)
          .addType(this)
          .addComment("AUTO-GENERATED FILE. DO NOT MODIFY.\n\n" +
              "This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.\n" +
              "It should not be modified by hand.\n"
          )
          .build()
}
