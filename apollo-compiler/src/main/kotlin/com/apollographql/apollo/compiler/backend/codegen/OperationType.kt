package com.apollographql.apollo.compiler.backend.codegen

import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.Subscription
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.compiler.applyIf
import com.apollographql.apollo.compiler.backend.ast.CodeGenerationAst
import com.apollographql.apollo.compiler.escapeKotlinReservedWord
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

internal fun CodeGenerationAst.OperationType.typeSpec(targetPackage: String, generateAsInternal: Boolean = false): TypeSpec {
  val operationResponseAdapter = CodeGenerationAst.TypeRef(name = name, packageName = targetPackage).asAdapterTypeName()
  return TypeSpec
      .classBuilder(name.escapeKotlinReservedWord())
      .addAnnotation(suppressWarningsAnnotation)
      .addSuperinterface(superInterfaceType(targetPackage))
      .applyIf(generateAsInternal) { addModifiers(KModifier.INTERNAL) }
      .applyIf(description.isNotBlank()) { addKdoc("%L", description) }
      .addVariablesIfNeeded(variables, name)
      .addFunction(FunSpec.builder("operationId")
          .addModifiers(KModifier.OVERRIDE)
          .returns(String::class)
          .addStatement("return OPERATION_ID")
          .build()
      )
      .addFunction(FunSpec.builder("queryDocument")
          .addModifiers(KModifier.OVERRIDE)
          .returns(String::class)
          .addStatement("return QUERY_DOCUMENT")
          .build()
      )
      .addFunction(variables.variablesFunSpec())
      .addFunction(FunSpec.builder("name")
          .addModifiers(KModifier.OVERRIDE)
          .returns(String::class)
          .addStatement("return OPERATION_NAME")
          .build()
      )
      .addFunction(
          FunSpec.builder("adapter")
              .addModifiers(KModifier.OVERRIDE)
              .returns(ResponseAdapter::class.asClassName().parameterizedBy(ClassName(packageName = "", "Data")))
              .addCode("return %T", operationResponseAdapter)
              .build()
      )
      .addFunction(
          FunSpec.builder(
              "responseFields",
          )
              .addModifiers(KModifier.OVERRIDE)
              .returns(
                  Map::class.asClassName().parameterizedBy(
                      String::class.asClassName(),
                      Array::class.asClassName().parameterizedBy(ResponseField::class.asClassName()),
                  )
              )
              .addCode("return %L", responseFieldsCode())
              .build()
      )
      .addType(this.dataType.typeSpec())
      .addType(TypeSpec.companionObjectBuilder()
          .addProperty(PropertySpec.builder("OPERATION_ID", String::class)
              .addModifiers(KModifier.CONST)
              .initializer("%S", operationId)
              .build()
          )
          .addProperty(PropertySpec.builder("QUERY_DOCUMENT", String::class)
              .initializer(
                  CodeBlock.builder()
                      .add("%T.minify(\n", QueryDocumentMinifier::class.java)
                      .indent()
                      .add("%S\n", queryDocument)
                      .unindent()
                      .add(")")
                      .build()
              )
              .build()
          )
          .addProperty(PropertySpec
              .builder("OPERATION_NAME", String::class)
              .initializer("%S", operationName)
              .build()
          )
          .build()
      )
      .build()
}

private fun CodeGenerationAst.OperationType.superInterfaceType(targetPackage: String): TypeName {
  val dataTypeName = ClassName(targetPackage, name.escapeKotlinReservedWord(), "Data")
  return when (type) {
    CodeGenerationAst.OperationType.Type.QUERY -> Query::class.asClassName()
    CodeGenerationAst.OperationType.Type.MUTATION -> Mutation::class.asClassName()
    CodeGenerationAst.OperationType.Type.SUBSCRIPTION -> Subscription::class.asClassName()
  }.parameterizedBy(dataTypeName)
}

private fun CodeGenerationAst.OperationType.responseFieldsCode(): CodeBlock {
  val builder = CodeBlock.builder()

  builder.add("mapOf(\n")
  builder.indent()
  when (val kind = dataType.kind) {
    is CodeGenerationAst.ObjectType.Kind.Object -> {
      builder.add("%S to %T.RESPONSE_FIELDS\n", "", dataType.typeRef.asAdapterTypeName())
    }
    is CodeGenerationAst.ObjectType.Kind.Fragment -> {
      kind.possibleImplementations.forEach {
        builder.add("%S to %T.RESPONSE_FIELDS,\n", it.key, it.value.asAdapterTypeName())
      }
      builder.add("%S to %T.RESPONSE_FIELDS,\n", "", kind.defaultImplementation.asAdapterTypeName())
    }
  }
  builder.unindent()
  builder.add(")")

  return builder.build()
}