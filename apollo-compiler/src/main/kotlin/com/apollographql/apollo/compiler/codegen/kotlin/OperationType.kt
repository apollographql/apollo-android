package com.apollographql.apollo.compiler.codegen.kotlin

import com.apollographql.apollo.api.*
import com.apollographql.apollo.compiler.applyIf
import com.apollographql.apollo.compiler.ast.AST
import com.apollographql.apollo.compiler.codegen.kotlin.KotlinCodeGen.asPropertySpec
import com.apollographql.apollo.compiler.codegen.kotlin.KotlinCodeGen.marshallerFunSpec
import com.apollographql.apollo.compiler.codegen.kotlin.KotlinCodeGen.toMapperFun
import com.apollographql.apollo.compiler.codegen.kotlin.KotlinCodeGen.responseFieldsPropertySpec
import com.apollographql.apollo.compiler.codegen.kotlin.KotlinCodeGen.asTypeName
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.jvm.throws
import java.io.IOException

fun KotlinCodeGen.operationTypeSpec(operationType: AST.OperationType, targetPackage: String): TypeSpec {
  return with(operationType) {
    TypeSpec.classBuilder(name)
        .addAnnotation(generatedByApolloAnnotation)
        .addAnnotation(suppressWarningsAnnotation)
        .addSuperinterface(superInterfaceType(targetPackage))
        .applyIf(variables.fields.isNotEmpty()) {
          addModifiers(KModifier.DATA)
          primaryConstructor(primaryConstructorSpec)
          addProperties(variables.fields.map { variable -> variable.asPropertySpec(CodeBlock.of(variable.name)) })
          addProperty(variablePropertySpec)
        }
        .addFunction(FunSpec.builder("operationId")
            .addModifiers(KModifier.OVERRIDE)
            .returns(String::class)
            .addCode("return OPERATION_ID")
            .build()
        )
        .addFunction(FunSpec.builder("queryDocument")
            .addModifiers(KModifier.OVERRIDE)
            .returns(String::class)
            .addCode("return QUERY_DOCUMENT")
            .build()
        )
        .addFunction(FunSpec.builder("wrapData")
            .addModifiers(KModifier.OVERRIDE)
            .addParameter(ParameterSpec.builder("data", data.asTypeName()).build())
            .returns(data.asTypeName())
            .addCode("return data")
            .build()
        )
        .addFunction(FunSpec.builder("variables")
            .addModifiers(KModifier.OVERRIDE)
            .returns(Operation.Variables::class.asClassName())
            .apply {
              if (variables.fields.isNotEmpty()) {
                addCode("return variables")
              } else {
                addCode("return %T.EMPTY_VARIABLES", Operation::class)
              }
            }
            .build()
        )
        .addFunction(FunSpec.builder("name")
            .addModifiers(KModifier.OVERRIDE)
            .returns(OperationName::class)
            .addCode("return OPERATION_NAME")
            .build()
        )
        .addFunction(FunSpec.builder("responseFieldMapper")
            .addModifiers(KModifier.OVERRIDE)
            .returns(ResponseFieldMapper::class.asClassName().parameterizedBy(data.asTypeName()))
            .beginControlFlow("return %T {", ResponseFieldMapper::class)
            .addStatement("%T(it)", data.asTypeName())
            .endControlFlow()
            .build()
        )
        .addTypes(nestedObjects.map { (ref, type) ->
          if (ref == data) {
            type.toOperationDataTypeSpec(data.name)
          } else {
            objectTypeSpec(type)
          }
        })
        .addType(TypeSpec.companionObjectBuilder()
            .addProperty(PropertySpec.builder("OPERATION_ID", String::class)
                .addModifiers(KModifier.CONST)
                .initializer("%S", operationId)
                .build()
            )
            .addProperty(PropertySpec.builder("QUERY_DOCUMENT", String::class)
                .initializer("%S", queryDocument)
                .build()
            )
            .addProperty(PropertySpec.builder("OPERATION_NAME", OperationName::class)
                .initializer("%T { %S }", OperationName::class, name)
                .build())
            .build()
        )
        .build()
  }
}

private fun AST.OperationType.superInterfaceType(targetPackage: String): TypeName {
  val dataTypeName = ClassName(packageName = targetPackage, simpleName = name, simpleNames = *arrayOf("Data"))
  return when (type) {
    AST.OperationType.Type.QUERY -> Query::class.asClassName()
    AST.OperationType.Type.MUTATION -> Mutation::class.asClassName()
    AST.OperationType.Type.SUBSCRIPTION -> Subscription::class.asClassName()
  }.parameterizedBy(dataTypeName, dataTypeName, Operation.Variables::class.asClassName())
}

private val AST.OperationType.primaryConstructorSpec: FunSpec
  get() {
    return FunSpec.constructorBuilder()
        .addParameters(variables.fields.map { variable ->
          val typeName = variable.type.asTypeName()
          ParameterSpec.builder(
              name = variable.name,
              type = if (variable.isOptional) Input::class.asClassName().parameterizedBy(typeName) else typeName
          ).build()
        })
        .build()
  }

private val AST.OperationType.variablePropertySpec: PropertySpec
  get() {
    return PropertySpec.builder("variables", Operation.Variables::class)
        .addModifiers(KModifier.PRIVATE)
        .addAnnotation(Transient::class)
        .initializer("%L", TypeSpec.anonymousClassBuilder()
            .superclass(Operation.Variables::class)
            .addFunction(variables.variablesValueMapSpec)
            .addFunction(variables.variablesMarshallerSpec)
            .build()
        )
        .build()
  }

private val AST.InputType.variablesValueMapSpec: FunSpec
  get() {
    return FunSpec.builder("valueMap")
        .addModifiers(KModifier.OVERRIDE)
        .returns(Map::class.asClassName().parameterizedBy(String::class.asClassName(),
            Any::class.asClassName().copy(nullable = true)))
        .beginControlFlow("return mutableMapOf<%T, %T>().apply", String::class,
            Any::class.asClassName().copy(nullable = true))
        .addCode(
            fields.map { field ->
              if (field.isOptional) {
                CodeBlock.of("if (%L.defined) this[%S] = %L.value", field.name, field.schemaName,
                    field.name)
              } else {
                CodeBlock.of("this[%S] = %L", field.schemaName, field.name)
              }
            }.joinToCode(separator = "\n", suffix = "\n")
        )
        .endControlFlow()
        .build()
  }

private val AST.InputType.variablesMarshallerSpec: FunSpec
  get() {
    return FunSpec.builder("marshaller")
        .returns(InputFieldMarshaller::class)
        .addModifiers(KModifier.OVERRIDE)
        .addStatement("return %L", TypeSpec.anonymousClassBuilder()
            .addSuperinterface(InputFieldMarshaller::class)
            .addFunction(FunSpec.builder("marshal")
                .addModifiers(KModifier.OVERRIDE)
                .throws(IOException::class)
                .addParameter(ParameterSpec.builder("writer", InputFieldWriter::class.java).build())
                .apply { fields.forEach { field -> addCode(field.writeCodeBlock) } }
                .build()
            ).build()
        ).build()
  }

private fun AST.ObjectType.toOperationDataTypeSpec(name: String): TypeSpec {
  return TypeSpec.classBuilder(name)
      .addModifiers(KModifier.DATA)
      .addSuperinterface(Operation.Data::class)
      .primaryConstructor(FunSpec.constructorBuilder()
          .addParameters(fields.map { field ->
            val typeName = field.type.asTypeName()
            ParameterSpec.builder(
                name = field.name,
                type = if (field.isOptional) typeName.copy(nullable = true) else typeName
            ).build()
          })
          .build()
      )
      .addProperties(fields.map { field -> field.asPropertySpec(initializer = CodeBlock.of(field.name)) })
      .addType(TypeSpec.companionObjectBuilder()
          .addProperty(responseFieldsPropertySpec(fields))
          .addFunction(fields.toMapperFun(ClassName.bestGuess(name)))
          .build()
      )
      .addFunction(marshallerFunSpec(fields).toBuilder().addModifiers(KModifier.OVERRIDE).build())
      .build()
}