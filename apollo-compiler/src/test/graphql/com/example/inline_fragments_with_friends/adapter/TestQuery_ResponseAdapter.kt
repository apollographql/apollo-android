// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_fragments_with_friends.adapter

import com.apollographql.apollo.api.ResponseAdapterCache
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.DoubleResponseAdapter
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.StringResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.example.inline_fragments_with_friends.TestQuery
import com.example.inline_fragments_with_friends.type.Episode
import com.example.inline_fragments_with_friends.type.Episode_ResponseAdapter
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery_ResponseAdapter(
  responseAdapterCache: ResponseAdapterCache
) : ResponseAdapter<TestQuery.Data> {
  private val nullableHeroAdapter: ResponseAdapter<TestQuery.Data.Hero?> =
      NullableResponseAdapter(Hero(responseAdapterCache))

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
          ResponseField.FieldSet("Human", Hero.HumanHero.RESPONSE_FIELDS),
          ResponseField.FieldSet("Droid", Hero.DroidHero.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    responseAdapterCache: ResponseAdapterCache
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val HumanHeroAdapter: HumanHero =
        com.example.inline_fragments_with_friends.adapter.TestQuery_ResponseAdapter.Hero.HumanHero(responseAdapterCache)

    val DroidHeroAdapter: DroidHero =
        com.example.inline_fragments_with_friends.adapter.TestQuery_ResponseAdapter.Hero.DroidHero(responseAdapterCache)

    val OtherHeroAdapter: OtherHero =
        com.example.inline_fragments_with_friends.adapter.TestQuery_ResponseAdapter.Hero.OtherHero(responseAdapterCache)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> HumanHeroAdapter.fromResponse(reader, typename)
        "Droid" -> DroidHeroAdapter.fromResponse(reader, typename)
        else -> OtherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.HumanHero -> HumanHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.DroidHero -> DroidHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> OtherHeroAdapter.toResponse(writer, value)
      }
    }

    class HumanHero(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      private val nullableFloatAdapter: ResponseAdapter<Double?> =
          NullableResponseAdapter(DoubleResponseAdapter)

      private val nullableListOfNullableFriendsAdapter:
          ResponseAdapter<List<TestQuery.Data.Hero.HumanHero.Friends?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friends(responseAdapterCache))))

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.HumanHero {
        var __typename: String? = __typename
        var name: String? = null
        var height: Double? = null
        var friends: List<TestQuery.Data.Hero.HumanHero.Friends?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            2 -> height = nullableFloatAdapter.fromResponse(reader)
            3 -> friends = nullableListOfNullableFriendsAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.HumanHero(
          __typename = __typename!!,
          name = name!!,
          height = height,
          friends = friends
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.HumanHero) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("height")
        nullableFloatAdapter.toResponse(writer, value.height)
        writer.name("friends")
        nullableListOfNullableFriendsAdapter.toResponse(writer, value.friends)
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
            type = ResponseField.Type.Named.Other("Float"),
            fieldName = "height",
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            fieldName = "friends",
            fieldSets = listOf(
              ResponseField.FieldSet(null, Friends.RESPONSE_FIELDS)
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friends(
        responseAdapterCache: ResponseAdapterCache
      ) : ResponseAdapter<TestQuery.Data.Hero.HumanHero.Friends> {
        private val listOfNullableEpisodeAdapter: ResponseAdapter<List<Episode?>> =
            ListResponseAdapter(NullableResponseAdapter(Episode_ResponseAdapter))

        override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero.HumanHero.Friends {
          var appearsIn: List<Episode?>? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> appearsIn = listOfNullableEpisodeAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return TestQuery.Data.Hero.HumanHero.Friends(
            appearsIn = appearsIn!!
          )
        }

        override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.HumanHero.Friends) {
          writer.beginObject()
          writer.name("appearsIn")
          listOfNullableEpisodeAdapter.toResponse(writer, value.appearsIn)
          writer.endObject()
        }

        companion object {
          val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField(
              type =
                  ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.Named.Other("Episode"))),
              fieldName = "appearsIn",
            )
          )

          val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
        }
      }
    }

    class DroidHero(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      private val nullableStringAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(StringResponseAdapter)

      private val nullableListOfNullableFriendsAdapter:
          ResponseAdapter<List<TestQuery.Data.Hero.DroidHero.Friends?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friends(responseAdapterCache))))

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.DroidHero {
        var __typename: String? = __typename
        var name: String? = null
        var primaryFunction: String? = null
        var friends: List<TestQuery.Data.Hero.DroidHero.Friends?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            2 -> primaryFunction = nullableStringAdapter.fromResponse(reader)
            3 -> friends = nullableListOfNullableFriendsAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.DroidHero(
          __typename = __typename!!,
          name = name!!,
          primaryFunction = primaryFunction,
          friends = friends
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("primaryFunction")
        nullableStringAdapter.toResponse(writer, value.primaryFunction)
        writer.name("friends")
        nullableListOfNullableFriendsAdapter.toResponse(writer, value.friends)
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
            type = ResponseField.Type.Named.Other("String"),
            fieldName = "primaryFunction",
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            fieldName = "friends",
            fieldSets = listOf(
              ResponseField.FieldSet(null, Friends.RESPONSE_FIELDS)
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friends(
        responseAdapterCache: ResponseAdapterCache
      ) : ResponseAdapter<TestQuery.Data.Hero.DroidHero.Friends> {
        private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

        override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero.DroidHero.Friends {
          var id: String? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> id = stringAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return TestQuery.Data.Hero.DroidHero.Friends(
            id = id!!
          )
        }

        override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero.Friends) {
          writer.beginObject()
          writer.name("id")
          stringAdapter.toResponse(writer, value.id)
          writer.endObject()
        }

        companion object {
          val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField(
              type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
              fieldName = "id",
            )
          )

          val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
        }
      }
    }

    class OtherHero(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.OtherHero {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.OtherHero(
          __typename = __typename!!,
          name = name!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.OtherHero) {
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
  }
}
