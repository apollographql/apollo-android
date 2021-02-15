// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_inline_fragments.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.example.union_inline_fragments.TestQuery
import com.example.union_inline_fragments.type.Episode
import com.example.union_inline_fragments.type.Episode_ResponseAdapter
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
  val nullableListOfNullableSearchAdapter: ResponseAdapter<List<TestQuery.Data.Search?>?> =
      NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Search(customScalarAdapters))))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var search: List<TestQuery.Data.Search?>? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> search = nullableListOfNullableSearchAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      search = search
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("search")
    nullableListOfNullableSearchAdapter.toResponse(writer, value.search)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.List(ResponseField.Type.Named.Object("SearchResult")),
        fieldName = "search",
        arguments = mapOf<String, Any?>(
          "text" to "test"),
        fieldSets = listOf(
          ResponseField.FieldSet("Human", Search.CharacterSearch.RESPONSE_FIELDS),
          ResponseField.FieldSet("Droid", Search.CharacterSearch.RESPONSE_FIELDS),
          ResponseField.FieldSet("Starship", Search.StarshipSearch.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Search.OtherSearch.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Search(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Search> {
    val CharacterSearchAdapter: CharacterSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch(customScalarAdapters)

    val StarshipSearchAdapter: StarshipSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.StarshipSearch(customScalarAdapters)

    val OtherSearchAdapter: OtherSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.OtherSearch(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Search {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> CharacterSearchAdapter.fromResponse(reader, typename)
        "Droid" -> CharacterSearchAdapter.fromResponse(reader, typename)
        "Starship" -> StarshipSearchAdapter.fromResponse(reader, typename)
        else -> OtherSearchAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search) {
      when(value) {
        is TestQuery.Data.Search.CharacterSearch -> CharacterSearchAdapter.toResponse(writer, value)
        is TestQuery.Data.Search.StarshipSearch -> StarshipSearchAdapter.toResponse(writer, value)
        is TestQuery.Data.Search.OtherSearch -> OtherSearchAdapter.toResponse(writer, value)
      }
    }

    class CharacterSearch(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nullableListOfNullableFriendAdapter:
          ResponseAdapter<List<TestQuery.Data.Search.CharacterSearch.Friend?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friend(customScalarAdapters))))

      fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Search.CharacterSearch {
        var __typename: String? = __typename
        var id: String? = null
        var name: String? = null
        var friends: List<TestQuery.Data.Search.CharacterSearch.Friend?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> id = stringAdapter.fromResponse(reader)
            2 -> name = stringAdapter.fromResponse(reader)
            3 -> friends = nullableListOfNullableFriendAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Search.CharacterSearch(
          __typename = __typename!!,
          id = id!!,
          name = name!!,
          friends = friends
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search.CharacterSearch) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("id")
        stringAdapter.toResponse(writer, value.id)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("friends")
        nullableListOfNullableFriendAdapter.toResponse(writer, value.friends)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename,
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            fieldName = "id",
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            fieldName = "name",
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            fieldName = "friends",
            fieldSets = listOf(
              ResponseField.FieldSet("Droid", Friend.CharacterDroidFriend.RESPONSE_FIELDS),
              ResponseField.FieldSet("Human", Friend.CharacterHumanFriend.RESPONSE_FIELDS),
              ResponseField.FieldSet(null, Friend.OtherFriend.RESPONSE_FIELDS),
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friend(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<TestQuery.Data.Search.CharacterSearch.Friend> {
        val CharacterDroidFriendAdapter: CharacterDroidFriend =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.CharacterDroidFriend(customScalarAdapters)

        val CharacterHumanFriendAdapter: CharacterHumanFriend =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.CharacterHumanFriend(customScalarAdapters)

        val OtherFriendAdapter: OtherFriend =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.OtherFriend(customScalarAdapters)

        override fun fromResponse(reader: JsonReader):
            TestQuery.Data.Search.CharacterSearch.Friend {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "Droid" -> CharacterDroidFriendAdapter.fromResponse(reader, typename)
            "Human" -> CharacterHumanFriendAdapter.fromResponse(reader, typename)
            else -> OtherFriendAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter,
            value: TestQuery.Data.Search.CharacterSearch.Friend) {
          when(value) {
            is TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend -> CharacterDroidFriendAdapter.toResponse(writer, value)
            is TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend -> CharacterHumanFriendAdapter.toResponse(writer, value)
            is TestQuery.Data.Search.CharacterSearch.Friend.OtherFriend -> OtherFriendAdapter.toResponse(writer, value)
          }
        }

        class CharacterDroidFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nullableStringAdapter: ResponseAdapter<String?> =
              NullableResponseAdapter(stringResponseAdapter)

          val nullableListOfNullableFriendAdapter:
              ResponseAdapter<List<TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend.Friend?>?>
              =
              NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friend(customScalarAdapters))))

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend {
            var __typename: String? = __typename
            var name: String? = null
            var primaryFunction: String? = null
            var friends: List<TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend.Friend?>? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                1 -> name = stringAdapter.fromResponse(reader)
                2 -> primaryFunction = nullableStringAdapter.fromResponse(reader)
                3 -> friends = nullableListOfNullableFriendAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend(
              __typename = __typename!!,
              name = name!!,
              primaryFunction = primaryFunction,
              friends = friends
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
            writer.name("primaryFunction")
            nullableStringAdapter.toResponse(writer, value.primaryFunction)
            writer.name("friends")
            nullableListOfNullableFriendAdapter.toResponse(writer, value.friends)
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
                  ResponseField.FieldSet(null, Friend.RESPONSE_FIELDS)
                ),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }

          class Friend(
            customScalarAdapters: CustomScalarAdapters
          ) :
              ResponseAdapter<TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend.Friend>
              {
            val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

            override fun fromResponse(reader: JsonReader):
                TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend.Friend {
              var id: String? = null
              reader.beginObject()
              while(true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                  0 -> id = stringAdapter.fromResponse(reader)
                  else -> break
                }
              }
              reader.endObject()
              return TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend.Friend(
                id = id!!
              )
            }

            override fun toResponse(writer: JsonWriter,
                value: TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend.Friend) {
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

        class CharacterHumanFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nullableStringAdapter: ResponseAdapter<String?> =
              NullableResponseAdapter(stringResponseAdapter)

          val nullableListOfNullableFriendAdapter:
              ResponseAdapter<List<TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend?>?>
              =
              NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friend(customScalarAdapters))))

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend {
            var __typename: String? = __typename
            var name: String? = null
            var homePlanet: String? = null
            var friends: List<TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend?>? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                1 -> name = stringAdapter.fromResponse(reader)
                2 -> homePlanet = nullableStringAdapter.fromResponse(reader)
                3 -> friends = nullableListOfNullableFriendAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend(
              __typename = __typename!!,
              name = name!!,
              homePlanet = homePlanet,
              friends = friends
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
            writer.name("homePlanet")
            nullableStringAdapter.toResponse(writer, value.homePlanet)
            writer.name("friends")
            nullableListOfNullableFriendAdapter.toResponse(writer, value.friends)
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
                fieldName = "homePlanet",
              ),
              ResponseField(
                type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
                fieldName = "friends",
                fieldSets = listOf(
                  ResponseField.FieldSet("Droid", Friend.CharacterFriend.RESPONSE_FIELDS),
                  ResponseField.FieldSet("Human", Friend.CharacterFriend.RESPONSE_FIELDS),
                  ResponseField.FieldSet(null, Friend.OtherFriend.RESPONSE_FIELDS),
                ),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }

          class Friend(
            customScalarAdapters: CustomScalarAdapters
          ) :
              ResponseAdapter<TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend>
              {
            val CharacterFriendAdapter: CharacterFriend =
                com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.CharacterFriend(customScalarAdapters)

            val OtherFriendAdapter: OtherFriend =
                com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend(customScalarAdapters)

            override fun fromResponse(reader: JsonReader):
                TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend {
              reader.beginObject()
              check(reader.nextName() == "__typename")
              val typename = reader.nextString()

              return when(typename) {
                "Droid" -> CharacterFriendAdapter.fromResponse(reader, typename)
                "Human" -> CharacterFriendAdapter.fromResponse(reader, typename)
                else -> OtherFriendAdapter.fromResponse(reader, typename)
              }
              .also { reader.endObject() }
            }

            override fun toResponse(writer: JsonWriter,
                value: TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend) {
              when(value) {
                is TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.CharacterFriend -> CharacterFriendAdapter.toResponse(writer, value)
                is TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend -> OtherFriendAdapter.toResponse(writer, value)
              }
            }

            class CharacterFriend(
              customScalarAdapters: CustomScalarAdapters
            ) {
              val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

              val episodeAdapter: ResponseAdapter<Episode> = Episode_ResponseAdapter

              fun fromResponse(reader: JsonReader, __typename: String?):
                  TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.CharacterFriend {
                var __typename: String? = __typename
                var firstAppearsIn: Episode? = null
                while(true) {
                  when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> __typename = stringAdapter.fromResponse(reader)
                    1 -> firstAppearsIn = episodeAdapter.fromResponse(reader)
                    else -> break
                  }
                }
                return TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.CharacterFriend(
                  __typename = __typename!!,
                  firstAppearsIn = firstAppearsIn!!
                )
              }

              fun toResponse(writer: JsonWriter,
                  value: TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.CharacterFriend) {
                writer.beginObject()
                writer.name("__typename")
                stringAdapter.toResponse(writer, value.__typename)
                writer.name("firstAppearsIn")
                episodeAdapter.toResponse(writer, value.firstAppearsIn)
                writer.endObject()
              }

              companion object {
                val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                  ResponseField.Typename,
                  ResponseField(
                    type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Episode")),
                    fieldName = "firstAppearsIn",
                  )
                )

                val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
              }
            }

            class OtherFriend(
              customScalarAdapters: CustomScalarAdapters
            ) {
              val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

              fun fromResponse(reader: JsonReader, __typename: String?):
                  TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend {
                var __typename: String? = __typename
                while(true) {
                  when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> __typename = stringAdapter.fromResponse(reader)
                    else -> break
                  }
                }
                return TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend(
                  __typename = __typename!!
                )
              }

              fun toResponse(writer: JsonWriter,
                  value: TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend) {
                writer.beginObject()
                writer.name("__typename")
                stringAdapter.toResponse(writer, value.__typename)
                writer.endObject()
              }

              companion object {
                val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                  ResponseField.Typename
                )

                val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
              }
            }
          }
        }

        class OtherFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Search.CharacterSearch.Friend.OtherFriend {
            var __typename: String? = __typename
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = stringAdapter.fromResponse(reader)
                else -> break
              }
            }
            return TestQuery.Data.Search.CharacterSearch.Friend.OtherFriend(
              __typename = __typename!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Search.CharacterSearch.Friend.OtherFriend) {
            writer.beginObject()
            writer.name("__typename")
            stringAdapter.toResponse(writer, value.__typename)
            writer.endObject()
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField.Typename
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }
      }
    }

    class StarshipSearch(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Search.StarshipSearch {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Search.StarshipSearch(
          __typename = __typename!!,
          name = name!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search.StarshipSearch) {
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

    class OtherSearch(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Search.OtherSearch {
        var __typename: String? = __typename
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Search.OtherSearch(
          __typename = __typename!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search.OtherSearch) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }
  }
}
