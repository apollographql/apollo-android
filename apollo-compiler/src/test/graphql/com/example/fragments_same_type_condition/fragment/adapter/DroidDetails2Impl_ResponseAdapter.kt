// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragments_same_type_condition.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.fragments_same_type_condition.fragment.DroidDetails2Impl
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class DroidDetails2Impl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<DroidDetails2Impl.Data> {
  val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val primaryFunctionAdapter: ResponseAdapter<String?> =
      NullableResponseAdapter(stringResponseAdapter)

  override fun fromResponse(reader: JsonReader): DroidDetails2Impl.Data {
    var __typename: String? = null
    var primaryFunction: String? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("__typename")
        1 -> primaryFunction = primaryFunctionAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return DroidDetails2Impl.Data(
      __typename = __typename!!,
      primaryFunction = primaryFunction
    )
  }

  override fun toResponse(writer: JsonWriter, value: DroidDetails2Impl.Data) {
    writer.beginObject()
    writer.name("__typename")
    __typenameAdapter.toResponse(writer, value.__typename)
    writer.name("primaryFunction")
    primaryFunctionAdapter.toResponse(writer, value.primaryFunction)
    writer.endObject()
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
        type = ResponseField.Type.Named.Other("String"),
        responseName = "primaryFunction",
        fieldName = "primaryFunction",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = emptyList(),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }
}
