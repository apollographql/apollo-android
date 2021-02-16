// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.directive_with_fragment.fragment.adapter

import com.apollographql.apollo.api.ResponseAdapterCache
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.example.directive_with_fragment.fragment.HumanDetailsImpl
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HumanDetailsImpl_ResponseAdapter(
  responseAdapterCache: ResponseAdapterCache
) : ResponseAdapter<HumanDetailsImpl.Data> {
  private val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

  private val nullableStringAdapter: ResponseAdapter<String?> =
      NullableResponseAdapter(stringResponseAdapter)

  override fun fromResponse(reader: JsonReader): HumanDetailsImpl.Data {
    var __typename: String? = null
    var homePlanet: String? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = stringAdapter.fromResponse(reader)
        1 -> homePlanet = nullableStringAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return HumanDetailsImpl.Data(
      __typename = __typename!!,
      homePlanet = homePlanet
    )
  }

  override fun toResponse(writer: JsonWriter, value: HumanDetailsImpl.Data) {
    writer.beginObject()
    writer.name("__typename")
    stringAdapter.toResponse(writer, value.__typename)
    writer.name("homePlanet")
    nullableStringAdapter.toResponse(writer, value.homePlanet)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.Typename,
      ResponseField(
        type = ResponseField.Type.Named.Other("String"),
        fieldName = "homePlanet",
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }
}
