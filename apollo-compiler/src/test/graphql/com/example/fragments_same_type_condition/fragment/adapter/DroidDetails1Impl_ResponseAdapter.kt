// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragments_same_type_condition.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.fragments_same_type_condition.fragment.DroidDetails1Impl
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class DroidDetails1Impl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<DroidDetails1Impl.Data> {
  val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

  override fun fromResponse(reader: JsonReader): DroidDetails1Impl.Data {
    var __typename: String? = null
    var name: String? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("__typename")
        1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
        else -> break
      }
    }
    reader.endObject()
    return DroidDetails1Impl.Data(
      __typename = __typename!!,
      name = name!!
    )
  }

  override fun toResponse(writer: JsonWriter, value: DroidDetails1Impl.Data) {
    __typenameAdapter.toResponse(writer, value.__typename)
    nameAdapter.toResponse(writer, value.name)
  }

  companion object {
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
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }
}
