package com.apollographql.apollo3.compiler.frontend

import com.apollographql.apollo3.api.VariableValue

fun GQLValue.toKotlinValue(constContext: Boolean): Any? {
  return when (this) {
    is GQLIntValue -> value
    is GQLFloatValue -> value
    is GQLStringValue -> value
    is GQLNullValue -> null
    is GQLListValue -> values.map { it.toKotlinValue(constContext) }
    is GQLObjectValue -> fields.map { it.name to it.value.toKotlinValue(constContext) }.toMap()
    is GQLBooleanValue -> value
    is GQLEnumValue -> value // Could we use something else in Kotlin?
    is GQLVariableValue -> {
      if (constContext) {
        throw ConversionException("Value cannot be a variable in a const context", sourceLocation)
      } else {
        VariableValue(name)
      }
    }
  }
}
