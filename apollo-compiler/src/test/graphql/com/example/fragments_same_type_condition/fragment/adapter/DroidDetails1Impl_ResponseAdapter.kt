// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragments_same_type_condition.fragment.adapter

import com.apollographql.apollo.api.ResponseAdapterCache
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.StringResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.example.fragments_same_type_condition.fragment.DroidDetails1Impl
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class DroidDetails1Impl_ResponseAdapter(
  responseAdapterCache: ResponseAdapterCache
) : ResponseAdapter<DroidDetails1Impl.Data> {
  private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

  override fun fromResponse(reader: JsonReader): DroidDetails1Impl.Data {
    var __typename: String? = null
    var name: String? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = stringAdapter.fromResponse(reader)
        1 -> name = stringAdapter.fromResponse(reader)
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
    writer.beginObject()
    writer.name("__typename")
    stringAdapter.toResponse(writer, value.__typename)
    writer.name("name")
    stringAdapter.toResponse(writer, value.name)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.Typename,
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        fieldName = "name",
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }
}
