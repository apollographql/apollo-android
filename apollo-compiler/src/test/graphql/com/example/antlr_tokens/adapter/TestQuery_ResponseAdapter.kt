// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.antlr_tokens.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.example.antlr_tokens.TestQuery
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<TestQuery.Data> {
  val nullableTypeWithGraphQLKeywordsAdapter:
      ResponseAdapter<TestQuery.Data.TypeWithGraphQLKeywords?> =
      NullableResponseAdapter(TypeWithGraphQLKeywords(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var typeWithGraphQLKeywords: TestQuery.Data.TypeWithGraphQLKeywords? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> typeWithGraphQLKeywords = nullableTypeWithGraphQLKeywordsAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      typeWithGraphQLKeywords = typeWithGraphQLKeywords
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("typeWithGraphQLKeywords")
    nullableTypeWithGraphQLKeywordsAdapter.toResponse(writer, value.typeWithGraphQLKeywords)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("TypeWithGraphQLKeywords"),
        fieldName = "typeWithGraphQLKeywords",
        fieldSets = listOf(
          ResponseField.FieldSet(null, TypeWithGraphQLKeywords.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class TypeWithGraphQLKeywords(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.TypeWithGraphQLKeywords> {
    val nullableStringAdapter: ResponseAdapter<String?> =
        NullableResponseAdapter(stringResponseAdapter)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.TypeWithGraphQLKeywords {
      var on: String? = null
      var null_: String? = null
      var alias: String? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> on = nullableStringAdapter.fromResponse(reader)
          1 -> null_ = nullableStringAdapter.fromResponse(reader)
          2 -> alias = nullableStringAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return TestQuery.Data.TypeWithGraphQLKeywords(
        on = on,
        null_ = null_,
        alias = alias
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.TypeWithGraphQLKeywords) {
      writer.beginObject()
      writer.name("on")
      nullableStringAdapter.toResponse(writer, value.on)
      writer.name("null")
      nullableStringAdapter.toResponse(writer, value.null_)
      writer.name("alias")
      nullableStringAdapter.toResponse(writer, value.alias)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.Named.Other("String"),
          fieldName = "on",
        ),
        ResponseField(
          type = ResponseField.Type.Named.Other("String"),
          fieldName = "null",
          arguments = mapOf<String, Any?>(
            "fragment" to mapOf<String, Any?>(
              "kind" to "Variable",
              "variableName" to "operation")),
        ),
        ResponseField(
          type = ResponseField.Type.Named.Other("String"),
          fieldName = "null",
          responseName = "alias",
          arguments = mapOf<String, Any?>(
            "fragment" to """
            |A string
            |with a new line
            """.trimMargin()),
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }
  }
}
