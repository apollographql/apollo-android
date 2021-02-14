// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.starships.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.doubleResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.starships.TestQuery
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<TestQuery.Data> {
  val starshipAdapter: ResponseAdapter<TestQuery.Data.Starship?> =
      NullableResponseAdapter(Starship(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var starship: TestQuery.Data.Starship? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> starship = starshipAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      starship = starship
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("starship")
    starshipAdapter.toResponse(writer, value.starship)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Starship"),
        responseName = "starship",
        fieldName = "starship",
        arguments = mapOf<String, Any?>(
          "id" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "id")),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet(null, Starship.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Starship(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Starship> {
    val idAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val coordinatesAdapter: ResponseAdapter<List<List<Double>>?> =
        NullableResponseAdapter(ListResponseAdapter(ListResponseAdapter(doubleResponseAdapter)))

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Starship {
      var id: String? = null
      var name: String? = null
      var coordinates: List<List<Double>>? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> id = idAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("id")
          1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
          2 -> coordinates = coordinatesAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return TestQuery.Data.Starship(
        id = id!!,
        name = name!!,
        coordinates = coordinates
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Starship) {
      writer.beginObject()
      writer.name("id")
      idAdapter.toResponse(writer, value.id)
      writer.name("name")
      nameAdapter.toResponse(writer, value.name)
      writer.name("coordinates")
      coordinatesAdapter.toResponse(writer, value.coordinates)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "id",
          fieldName = "id",
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
          type =
              ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Float"))))),
          responseName = "coordinates",
          fieldName = "coordinates",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }
  }
}
