package com.apollostack.compiler.ir

import com.apollostack.api.Query
import com.apollostack.compiler.InterfaceTypeSpecBuilder
import com.apollostack.compiler.resolveNestedTypeNameDuplication
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeSpec

data class Operation(
    val operationName: String,
    val operationType: String,
    val variables: List<Variable>,
    val source: String,
    val fields: List<Field>
) : CodeGenerator {
  override fun toTypeSpec(): TypeSpec =
    InterfaceTypeSpecBuilder()
        .build(INTERFACE_TYPE_SPEC_NAME, fields, emptyList(), emptyList())
        .toBuilder()
        .addSuperinterface(ClassName.get(Query.Data::class.java))
        .build()
        .resolveNestedTypeNameDuplication(emptyList())

  companion object {
    val INTERFACE_TYPE_SPEC_NAME = "Data"
  }
}