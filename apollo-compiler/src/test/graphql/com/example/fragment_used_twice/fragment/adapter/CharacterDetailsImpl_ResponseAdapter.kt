// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice.fragment.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.fragment_used_twice.fragment.CharacterDetailsImpl
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object CharacterDetailsImpl_ResponseAdapter : ResponseAdapter<CharacterDetailsImpl.Data> {
  val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
      responseName = "__typename",
      fieldName = "__typename",
      arguments = emptyMap(),
      conditions = emptyList(),
      fieldSets = emptyList(),
    ),
    ResponseField(
      type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
      responseName = "name",
      fieldName = "name",
      arguments = emptyMap(),
      conditions = emptyList(),
      fieldSets = emptyList(),
    ),
    ResponseField(
      type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
      responseName = "birthDate",
      fieldName = "birthDate",
      arguments = emptyMap(),
      conditions = emptyList(),
      fieldSets = emptyList(),
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?):
      CharacterDetailsImpl.Data {
    return reader.run {
      var __typename: String? = __typename
      var name: String? = null
      var birthDate: Any? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> __typename = readString(RESPONSE_FIELDS[0])
          1 -> name = readString(RESPONSE_FIELDS[1])
          2 -> birthDate = readCustomScalar<Any>(RESPONSE_FIELDS[2])
          else -> break
        }
      }
      CharacterDetailsImpl.Data(
        __typename = __typename!!,
        name = name!!,
        birthDate = birthDate!!
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: CharacterDetailsImpl.Data) {
    writer.writeString(RESPONSE_FIELDS[0], value.__typename)
    writer.writeString(RESPONSE_FIELDS[1], value.name)
    writer.writeCustom(RESPONSE_FIELDS[2], value.birthDate)
  }
}
