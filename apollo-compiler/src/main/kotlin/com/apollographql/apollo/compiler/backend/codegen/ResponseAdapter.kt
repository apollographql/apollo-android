package com.apollographql.apollo.compiler.backend.codegen

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.compiler.applyIf
import com.apollographql.apollo.compiler.backend.ast.CodeGenerationAst
import com.apollographql.apollo.compiler.escapeKotlinReservedWord
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.joinToCode

internal fun CodeGenerationAst.OperationType.responseAdapterTypeSpec(generateAsInternal: Boolean = false): TypeSpec {
  return this.dataType
      .copy(name = "${this.name.escapeKotlinReservedWord()}_ResponseAdapter")
      .rootResponseAdapterTypeSpec(generateAsInternal)
}

internal fun CodeGenerationAst.FragmentType.responseAdapterTypeSpec(generateAsInternal: Boolean = false): TypeSpec {
  val dataType = this.implementationType.nestedObjects.single()
  return dataType
      .copy(name = "${this.implementationType.name.escapeKotlinReservedWord()}_ResponseAdapter")
      .rootResponseAdapterTypeSpec(generateAsInternal)
}

private fun CodeGenerationAst.ObjectType.rootResponseAdapterTypeSpec(generateAsInternal: Boolean = false): TypeSpec {
  return this.responseAdapterTypeSpec()
      .toBuilder()
      .applyIf(generateAsInternal) { addModifiers(KModifier.INTERNAL) }
      .addAnnotation(suppressWarningsAnnotation)
      .build()
}

private fun CodeGenerationAst.ObjectType.responseAdapterTypeSpec(): TypeSpec {
  return TypeSpec.objectBuilder(this.name)
      .addSuperinterface(ResponseAdapter::class.asTypeName().parameterizedBy(this.typeRef.asTypeName()))
      .applyIf(this.fields.isNotEmpty()) { addProperty(responseFieldsPropertySpec(this@responseAdapterTypeSpec.fields)) }
      .addFunction(readFromResponseFunSpec())
      .addFunction(writeToResponseFunSpec())
      .addTypes(
          this.nestedObjects
              .filter { it.kind !is CodeGenerationAst.ObjectType.Kind.Fragment || it.kind.possibleImplementations.isNotEmpty() }
              .mapNotNull { nestedObject ->
                nestedObject
                    .takeUnless { it.kind is CodeGenerationAst.ObjectType.Kind.Interface }
                    ?.responseAdapterTypeSpec()
              }
      )
      .build()
}

internal fun CodeGenerationAst.TypeRef.asAdapterTypeName(): ClassName {

  return when {
    // We are called on a root typeRef such as TestQuery, just add '_ResponseAdapter'
    enclosingType == null -> {
      ClassName(packageName = "$packageName.adapter", "${this.name.escapeKotlinReservedWord()}_ResponseAdapter")
    }
    // We are called on a data typeRef such as TestQuery.Data, skip Data
    enclosingType.enclosingType == null -> {
      ClassName(packageName = "$packageName.adapter", "${this.enclosingType.name.escapeKotlinReservedWord()}_ResponseAdapter")
    }
    else -> {
      ClassName(packageName = "$packageName.adapter", enclosingType.asAdapterTypeName().simpleNames + this.name.escapeKotlinReservedWord())
    }
  }
}


private fun responseFieldsPropertySpec(fields: List<CodeGenerationAst.Field>): PropertySpec {
  val initializer = CodeBlock.builder()
      .addStatement("arrayOf(")
      .indent()
      .add(fields.map { field -> field.responseFieldInitializerCode }.joinToCode(separator = ",\n"))
      .unindent()
      .addStatement("")
      .add(")")
      .build()
  return PropertySpec
      .builder(
          name = "RESPONSE_FIELDS",
          type = Array<ResponseField>::class.asClassName().parameterizedBy(ResponseField::class.asClassName()),
          modifiers = arrayOf(KModifier.PRIVATE)
      )
      .initializer(initializer)
      .build()
}

private val CodeGenerationAst.Field.responseFieldInitializerCode: CodeBlock
  get() {
    val factoryMethod = when (type) {
      is CodeGenerationAst.FieldType.Scalar -> when (type) {
        is CodeGenerationAst.FieldType.Scalar.ID -> "forString"
        is CodeGenerationAst.FieldType.Scalar.String -> "forString"
        is CodeGenerationAst.FieldType.Scalar.Int -> "forInt"
        is CodeGenerationAst.FieldType.Scalar.Boolean -> "forBoolean"
        is CodeGenerationAst.FieldType.Scalar.Float -> "forDouble"
        is CodeGenerationAst.FieldType.Scalar.Enum -> "forEnum"
        is CodeGenerationAst.FieldType.Scalar.Custom -> "forCustomScalar"
      }
      is CodeGenerationAst.FieldType.Object -> "forObject"
      is CodeGenerationAst.FieldType.Array -> "forList"
    }

    val builder = CodeBlock.builder().add("%T.%L", ResponseField::class, factoryMethod)
    when {
      type is CodeGenerationAst.FieldType.Scalar && type is CodeGenerationAst.FieldType.Scalar.Custom -> {
        builder.add("(%S,·%S,·%L,·%L,·%T,·%L)", responseName, schemaName, arguments.takeIf { it.isNotEmpty() }.toCode(), type.nullable,
            type.typeRef.asTypeName(), conditionsListCode(conditions))
      }
      else -> {
        builder.add("(%S,·%S,·%L,·%L,·%L)", responseName, schemaName, arguments.takeIf { it.isNotEmpty() }.toCode(), type.nullable,
            conditionsListCode(conditions))
      }
    }
    return builder.build()
  }

private fun conditionsListCode(conditions: Set<CodeGenerationAst.Field.Condition>): CodeBlock {
  return conditions
      .map { condition ->
        when (condition) {
          is CodeGenerationAst.Field.Condition.Directive -> CodeBlock.of("%T.booleanCondition(%S,·%L)",
              ResponseField.Condition::class, condition.variableName.escapeKotlinReservedWord(), condition.inverted)
        }
      }
      .joinToCode(separator = ",\n")
      .let {
        if (conditions.isEmpty()) {
          CodeBlock.of("null")
        } else {
          CodeBlock.builder()
              .add("listOf(\n")
              .indent().add(it).unindent()
              .add("\n)")
              .build()
        }
      }
}

private fun Any?.toCode(): CodeBlock {
  return when {
    this == null -> CodeBlock.of("null")
    this is Map<*, *> && this.isEmpty() -> CodeBlock.of("emptyMap<%T,·Any?>()", String::class.asTypeName())
    this is Map<*, *> -> CodeBlock.builder()
        .add("mapOf<%T,·Any?>(\n", String::class.asTypeName())
        .indent()
        .add(map { CodeBlock.of("%S to %L", it.key, it.value.toCode()) }.joinToCode(separator = ",\n"))
        .unindent()
        .add(")")
        .build()
    this is List<*> && this.isEmpty() -> CodeBlock.of("emptyList<Any?>()")
    this is List<*> -> CodeBlock.builder()
        .add("listOf<Any?>(\n")
        .indent()
        .add(map { it.toCode() }.joinToCode(separator = ",\n"))
        .unindent()
        .add(")")
        .build()
    this is String -> CodeBlock.of("%S", this)
    this is Number -> CodeBlock.of("%L", this)
    this is Boolean -> CodeBlock.of("%L", this)
    else -> throw IllegalStateException("Cannot generate code for $this")
  }
}