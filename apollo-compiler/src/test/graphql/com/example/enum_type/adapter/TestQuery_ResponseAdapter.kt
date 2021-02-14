// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.enum_type.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.enum_type.TestQuery
import com.example.enum_type.type.Episode
import com.example.enum_type.type.Episode_ResponseAdapter
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
  val heroAdapter: ResponseAdapter<TestQuery.Data.Hero?> =
      NullableResponseAdapter(Hero(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var hero: TestQuery.Data.Hero? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> hero = heroAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      hero = hero
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("hero")
    heroAdapter.toResponse(writer, value.hero)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        fieldName = "hero",
        fieldSets = listOf(
          ResponseField.FieldSet(null, Hero.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val appearsInAdapter: ResponseAdapter<List<Episode?>> =
        ListResponseAdapter(NullableResponseAdapter(Episode_ResponseAdapter))

    val firstAppearsInAdapter: ResponseAdapter<Episode> = Episode_ResponseAdapter

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero {
      var name: String? = null
      var appearsIn: List<Episode?>? = null
      var firstAppearsIn: Episode? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
          1 -> appearsIn = appearsInAdapter.fromResponse(reader) ?: throw
              UnexpectedNullValue("appearsIn")
          2 -> firstAppearsIn = firstAppearsInAdapter.fromResponse(reader) ?: throw
              UnexpectedNullValue("firstAppearsIn")
          else -> break
        }
      }
      reader.endObject()
      return TestQuery.Data.Hero(
        name = name!!,
        appearsIn = appearsIn!!,
        firstAppearsIn = firstAppearsIn!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      writer.beginObject()
      writer.name("name")
      nameAdapter.toResponse(writer, value.name)
      writer.name("appearsIn")
      appearsInAdapter.toResponse(writer, value.appearsIn)
      writer.name("firstAppearsIn")
      firstAppearsInAdapter.toResponse(writer, value.firstAppearsIn)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          fieldName = "name",
        ),
        ResponseField(
          type =
              ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.Named.Other("Episode"))),
          fieldName = "appearsIn",
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Episode")),
          fieldName = "firstAppearsIn",
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }
  }
}
