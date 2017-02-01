package com.apollographql.android.compiler

import com.squareup.javapoet.*
import javax.lang.model.element.Modifier

fun TypeName.overrideTypeName(typeNameOverrideMap: Map<String, String>): TypeName {
  if (this is ParameterizedTypeName) {
    val typeArguments = typeArguments.map { it.overrideTypeName(typeNameOverrideMap) }.toTypedArray()
    return ParameterizedTypeName.get(rawType, *typeArguments)
  } else if (this is ClassName) {
    return ClassName.get(packageName(), typeNameOverrideMap[simpleName()] ?: simpleName())
  } else if (this is WildcardTypeName) {
    return WildcardTypeName.subtypeOf(upperBounds[0].overrideTypeName(typeNameOverrideMap))
  } else {
    return this
  }
}

fun FieldSpec.overrideType(typeNameOverrideMap: Map<String, String>): FieldSpec = FieldSpec
    .builder(type.overrideTypeName(typeNameOverrideMap).annotated(type.annotations), name)
    .addModifiers(*modifiers.toTypedArray())
    .addAnnotations(annotations)
    .initializer(initializer)
    .build()

fun MethodSpec.overrideReturnType(typeNameOverrideMap: Map<String, String>): MethodSpec = MethodSpec
    .methodBuilder(name)
    .returns(returnType.overrideTypeName(typeNameOverrideMap).annotated(returnType.annotations))
    .addModifiers(*modifiers.toTypedArray())
    .addCode(code)
    .build()

fun TypeSpec.creatorTypeSpec(): TypeSpec = TypeSpec
    .interfaceBuilder(Util.CREATOR_INTERFACE_NAME)
    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
    .addMethod(MethodSpec
        .methodBuilder(Util.CREATOR_METHOD_CREATE)
        .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
        .addParameters(
            methodSpecs.filter { !it.isConstructor }.map {
              val paramType = it.returnType
              val paramName = it.name
              ParameterSpec.builder(paramType, paramName).build()
            })
        .returns(ClassName.get("", name))
        .build())
    .build()

fun TypeSpec.factoryTypeSpec(): TypeSpec = TypeSpec
    .interfaceBuilder(Util.FACTORY_INTERFACE_NAME)
    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
    .addMethod(
        MethodSpec
            .methodBuilder(Util.CREATOR_INTERFACE_NAME.decapitalize())
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .returns(ClassName.get("", Util.CREATOR_INTERFACE_NAME))
            .build())
    .addMethods(
        typeSpecs.filter { it.name != Util.CREATOR_INTERFACE_NAME }.map {
          MethodSpec
              .methodBuilder("${it.name.decapitalize()}${Util.FACTORY_INTERFACE_NAME}")
              .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
              .returns(ClassName.get("", "${it.name}.${Util.FACTORY_INTERFACE_NAME}"))
              .build()
        })
    .build()

object Util {
  const val CREATOR_INTERFACE_NAME: String = "Creator"
  const val CREATOR_METHOD_CREATE: String = "create"
  const val FACTORY_INTERFACE_NAME: String = "Factory"
}