// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_name.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.example.hero_name.TestQuery
import com.example.hero_name.type.CustomScalars
import kotlin.Any
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
  val nullableHeroAdapter: ResponseAdapter<TestQuery.Data.Hero?> =
      NullableResponseAdapter(Hero(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var hero: TestQuery.Data.Hero? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> hero = nullableHeroAdapter.fromResponse(reader)
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
    nullableHeroAdapter.toResponse(writer, value.hero)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        fieldName = "hero",
        fieldSets = listOf(
          ResponseField.FieldSet("Droid", Hero.DroidHero.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val DroidHeroAdapter: DroidHero =
        com.example.hero_name.adapter.TestQuery_ResponseAdapter.Hero.DroidHero(customScalarAdapters)

    val OtherHeroAdapter: OtherHero =
        com.example.hero_name.adapter.TestQuery_ResponseAdapter.Hero.OtherHero(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Droid" -> DroidHeroAdapter.fromResponse(reader, typename)
        else -> OtherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.DroidHero -> DroidHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> OtherHeroAdapter.toResponse(writer, value)
      }
    }

    class DroidHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

      val dateAdapter: ResponseAdapter<Any> =
          customScalarAdapters.responseAdapterFor<Any>(CustomScalars.Date)

      val nullableStringAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(stringResponseAdapter)

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.DroidHero {
        var __typename: String? = __typename
        var name: String? = null
        var birthDate: Any? = null
        var primaryFunction: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            2 -> birthDate = dateAdapter.fromResponse(reader)
            3 -> primaryFunction = nullableStringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.DroidHero(
          __typename = __typename!!,
          name = name!!,
          birthDate = birthDate!!,
          primaryFunction = primaryFunction
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("birthDate")
        dateAdapter.toResponse(writer, value.birthDate)
        writer.name("primaryFunction")
        nullableStringAdapter.toResponse(writer, value.primaryFunction)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename,
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            fieldName = "name",
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
            fieldName = "birthDate",
          ),
          ResponseField(
            type = ResponseField.Type.Named.Other("String"),
            fieldName = "primaryFunction",
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class OtherHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

      val dateAdapter: ResponseAdapter<Any> =
          customScalarAdapters.responseAdapterFor<Any>(CustomScalars.Date)

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.OtherHero {
        var __typename: String? = __typename
        var name: String? = null
        var birthDate: Any? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            2 -> birthDate = dateAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.OtherHero(
          __typename = __typename!!,
          name = name!!,
          birthDate = birthDate!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.OtherHero) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("birthDate")
        dateAdapter.toResponse(writer, value.birthDate)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename,
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            fieldName = "name",
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
            fieldName = "birthDate",
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }
  }
}
