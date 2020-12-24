package com.apollographql.apollo.compiler.backend

import com.apollographql.apollo.api.CustomScalar
import com.apollographql.apollo.compiler.applyIf
import com.apollographql.apollo.compiler.backend.ast.AstBuilder.Companion.buildAst
import com.apollographql.apollo.compiler.backend.ast.CodeGenerationAst
import com.apollographql.apollo.compiler.backend.codegen.implementationTypeSpec
import com.apollographql.apollo.compiler.backend.codegen.interfaceTypeSpec
import com.apollographql.apollo.compiler.backend.codegen.patchKotlinNativeOptionalArrayProperties
import com.apollographql.apollo.compiler.backend.codegen.responseAdapterTypeSpec
import com.apollographql.apollo.compiler.backend.codegen.typeSpec
import com.apollographql.apollo.compiler.backend.ir.BackendIr
import com.apollographql.apollo.compiler.frontend.Schema
import com.apollographql.apollo.compiler.frontend.toIntrospectionSchema
import com.apollographql.apollo.compiler.operationoutput.OperationOutput
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import java.io.File

internal class GraphQLCodeGenerator(
    private val backendIr: BackendIr,
    private val schema: Schema,
    private val customScalarsMapping: Map<String, String>,
    private val generateAsInternal: Boolean = false,
    private val operationOutput: OperationOutput,
    private val generateFilterNotNull: Boolean,
    private val enumAsSealedClassPatternFilters: List<Regex>,
    private val enumsToGenerate: Set<String>,
    private val inputObjectsToGenerate: Set<String>,
    private val generateScalarMapping: Boolean,
    private val typesPackageName: String,
    private val fragmentsPackageName: String,
) {
  fun write(outputDir: File) {

    val introspectionSchema = schema.toIntrospectionSchema()
    val ast = backendIr.buildAst(
        schema = introspectionSchema,
        customScalarsMapping = customScalarsMapping,
        operationOutput = operationOutput,
        typesPackageName = typesPackageName,
        fragmentsPackage = fragmentsPackageName
    )

    if (generateScalarMapping) {
      fileSpec(typesPackageName, "CustomScalars") {
        ast.customScalarTypes.values.forEach {
          addProperty(propertySpec = it.propertySpec(generateAsInternal))
        }
      }.writeTo(outputDir)
    }

    ast.enumTypes
        .filter { enumType -> enumsToGenerate.contains(enumType.graphqlName) }
        .forEach { enumType ->
          enumType
              .typeSpec(
                  generateAsInternal = generateAsInternal,
                  enumAsSealedClassPatternFilters = enumAsSealedClassPatternFilters
              )
              .fileSpec(typesPackageName)
              .writeTo(outputDir)
        }

    ast.inputTypes
        .filter { inputType -> inputObjectsToGenerate.contains(inputType.graphqlName) }
        .forEach { inputType ->
          inputType
              .typeSpec(generateAsInternal)
              .fileSpec(typesPackageName)
              .writeTo(outputDir)
        }

    ast.fragmentTypes
        .forEach { fragmentType ->
          fragmentType
              .interfaceTypeSpec(generateAsInternal)
              .fileSpec(fragmentsPackageName)
              .writeTo(outputDir)

          fragmentType
              .implementationTypeSpec(generateAsInternal)
              .fileSpec(fragmentsPackageName)
              .writeTo(outputDir)
        }

    ast.fragmentTypes
        .forEach { fragmentType ->
          fragmentType
              .responseAdapterTypeSpec(generateAsInternal)
              .fileSpec("${fragmentsPackageName}.adapter")
              .writeTo(outputDir)
        }

    ast.operationTypes.forEach { operationType ->
      operationType
          .typeSpec(
              targetPackage = operationType.packageName,
              generateAsInternal = generateAsInternal
          )
          .let {
            if (generateFilterNotNull) {
              it.patchKotlinNativeOptionalArrayProperties()
            } else it
          }
          .fileSpec(operationType.packageName)
          .writeTo(outputDir)
    }

    ast.operationTypes.forEach { operationType ->
      operationType.responseAdapterTypeSpec(generateAsInternal)
          .fileSpec("${operationType.packageName}.adapter")
          .writeTo(outputDir)
    }
  }

  internal fun CodeGenerationAst.CustomScalarType.propertySpec(generateAsInternal: Boolean): PropertySpec {
    return PropertySpec
        .builder(name.toUpperCase(), CustomScalar::class)
        .applyIf(generateAsInternal) { addModifiers(KModifier.INTERNAL)}
        .receiver(CustomScalar.Companion::class)
        .getter(
            FunSpec.getterBuilder()
                .addCode("return %T(%S, %S)", CustomScalar::class.asTypeName(), schemaType, mappedType)
            .build()
        )
        .build()
  }

  private fun TypeSpec.fileSpec(packageName: String) = fileSpec(packageName, name!!) {
    addType(this@fileSpec)
  }

  private fun fileSpec(packageName: String, name: String, block: FileSpec.Builder.() -> Unit) =
      FileSpec
          .builder(packageName, name)
          .apply(block)
          .addComment("AUTO-GENERATED FILE. DO NOT MODIFY.\n\n" +
              "This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.\n" +
              "It should not be modified by hand.\n"
          )
          .build()
}
