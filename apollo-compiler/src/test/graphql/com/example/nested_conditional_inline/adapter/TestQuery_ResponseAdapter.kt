// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.nested_conditional_inline.adapter

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
import com.example.nested_conditional_inline.TestQuery
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
        responseName = "hero",
        fieldName = "hero",
        arguments = mapOf<String, Any?>(
          "episode" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "episode")),
        conditions = emptyList(),
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
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val humanHeroAdapter: HumanHero =
        com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.HumanHero(customScalarAdapters)

    val droidHeroAdapter: DroidHero =
        com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.DroidHero(customScalarAdapters)

    val otherHeroAdapter: OtherHero =
        com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.OtherHero(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> humanHeroAdapter.fromResponse(reader, typename)
        "Droid" -> droidHeroAdapter.fromResponse(reader, typename)
        else -> otherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.HumanHero -> humanHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.DroidHero -> droidHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> otherHeroAdapter.toResponse(writer, value)
      }
    }

    class HumanHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val friendsAdapter: ResponseAdapter<List<TestQuery.Data.Hero.HumanHero.Friend?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friend(customScalarAdapters))))

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.HumanHero {
        var __typename: String? = __typename
        var name: String? = null
        var friends: List<TestQuery.Data.Hero.HumanHero.Friend?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> friends = friendsAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.HumanHero(
          __typename = __typename!!,
          name = name!!,
          friends = friends
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.HumanHero) {
        writer.beginObject()
        writer.name("__typename")
        __typenameAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        nameAdapter.toResponse(writer, value.name)
        writer.name("friends")
        friendsAdapter.toResponse(writer, value.friends)
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
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "name",
            fieldName = "name",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            responseName = "friends",
            fieldName = "friends",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet("Human", Friend.HumanFriend.RESPONSE_FIELDS),
              ResponseField.FieldSet(null, Friend.OtherFriend.RESPONSE_FIELDS),
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friend(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<TestQuery.Data.Hero.HumanHero.Friend> {
        val humanFriendAdapter: HumanFriend =
            com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.HumanHero.Friend.HumanFriend(customScalarAdapters)

        val otherFriendAdapter: OtherFriend =
            com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.HumanHero.Friend.OtherFriend(customScalarAdapters)

        override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero.HumanHero.Friend {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "Human" -> humanFriendAdapter.fromResponse(reader, typename)
            else -> otherFriendAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.HumanHero.Friend) {
          when(value) {
            is TestQuery.Data.Hero.HumanHero.Friend.HumanFriend -> humanFriendAdapter.toResponse(writer, value)
            is TestQuery.Data.Hero.HumanHero.Friend.OtherFriend -> otherFriendAdapter.toResponse(writer, value)
          }
        }

        class HumanFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val heightAdapter: ResponseAdapter<Double?> =
              NullableResponseAdapter(doubleResponseAdapter)

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.HumanHero.Friend.HumanFriend {
            var __typename: String? = __typename
            var name: String? = null
            var height: Double? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                2 -> height = heightAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Hero.HumanHero.Friend.HumanFriend(
              __typename = __typename!!,
              name = name!!,
              height = height
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.HumanHero.Friend.HumanFriend) {
            writer.beginObject()
            writer.name("__typename")
            __typenameAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            nameAdapter.toResponse(writer, value.name)
            writer.name("height")
            heightAdapter.toResponse(writer, value.height)
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
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                responseName = "name",
                fieldName = "name",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = emptyList(),
              ),
              ResponseField(
                type = ResponseField.Type.Named.Other("Float"),
                responseName = "height",
                fieldName = "height",
                arguments = mapOf<String, Any?>(
                  "unit" to "FOOT"),
                conditions = emptyList(),
                fieldSets = emptyList(),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }

        class OtherFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.HumanHero.Friend.OtherFriend {
            var __typename: String? = __typename
            var name: String? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                else -> break
              }
            }
            return TestQuery.Data.Hero.HumanHero.Friend.OtherFriend(
              __typename = __typename!!,
              name = name!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.HumanHero.Friend.OtherFriend) {
            writer.beginObject()
            writer.name("__typename")
            __typenameAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            nameAdapter.toResponse(writer, value.name)
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
      }
    }

    class DroidHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val friendsAdapter: ResponseAdapter<List<TestQuery.Data.Hero.DroidHero.Friend?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friend(customScalarAdapters))))

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.DroidHero {
        var __typename: String? = __typename
        var name: String? = null
        var friends: List<TestQuery.Data.Hero.DroidHero.Friend?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> friends = friendsAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.DroidHero(
          __typename = __typename!!,
          name = name!!,
          friends = friends
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero) {
        writer.beginObject()
        writer.name("__typename")
        __typenameAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        nameAdapter.toResponse(writer, value.name)
        writer.name("friends")
        friendsAdapter.toResponse(writer, value.friends)
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
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "name",
            fieldName = "name",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            responseName = "friends",
            fieldName = "friends",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet("Human", Friend.HumanFriend.RESPONSE_FIELDS),
              ResponseField.FieldSet(null, Friend.OtherFriend.RESPONSE_FIELDS),
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friend(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<TestQuery.Data.Hero.DroidHero.Friend> {
        val humanFriendAdapter: HumanFriend =
            com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.DroidHero.Friend.HumanFriend(customScalarAdapters)

        val otherFriendAdapter: OtherFriend =
            com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter.Hero.DroidHero.Friend.OtherFriend(customScalarAdapters)

        override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero.DroidHero.Friend {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "Human" -> humanFriendAdapter.fromResponse(reader, typename)
            else -> otherFriendAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero.Friend) {
          when(value) {
            is TestQuery.Data.Hero.DroidHero.Friend.HumanFriend -> humanFriendAdapter.toResponse(writer, value)
            is TestQuery.Data.Hero.DroidHero.Friend.OtherFriend -> otherFriendAdapter.toResponse(writer, value)
          }
        }

        class HumanFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val heightAdapter: ResponseAdapter<Double?> =
              NullableResponseAdapter(doubleResponseAdapter)

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.DroidHero.Friend.HumanFriend {
            var __typename: String? = __typename
            var name: String? = null
            var height: Double? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                2 -> height = heightAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Hero.DroidHero.Friend.HumanFriend(
              __typename = __typename!!,
              name = name!!,
              height = height
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.DroidHero.Friend.HumanFriend) {
            writer.beginObject()
            writer.name("__typename")
            __typenameAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            nameAdapter.toResponse(writer, value.name)
            writer.name("height")
            heightAdapter.toResponse(writer, value.height)
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
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                responseName = "name",
                fieldName = "name",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = emptyList(),
              ),
              ResponseField(
                type = ResponseField.Type.Named.Other("Float"),
                responseName = "height",
                fieldName = "height",
                arguments = mapOf<String, Any?>(
                  "unit" to "METER"),
                conditions = emptyList(),
                fieldSets = emptyList(),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }

        class OtherFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.DroidHero.Friend.OtherFriend {
            var __typename: String? = __typename
            var name: String? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                else -> break
              }
            }
            return TestQuery.Data.Hero.DroidHero.Friend.OtherFriend(
              __typename = __typename!!,
              name = name!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.DroidHero.Friend.OtherFriend) {
            writer.beginObject()
            writer.name("__typename")
            __typenameAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            nameAdapter.toResponse(writer, value.name)
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
      }
    }

    class OtherHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.OtherHero {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
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
        __typenameAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        nameAdapter.toResponse(writer, value.name)
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
  }
}
