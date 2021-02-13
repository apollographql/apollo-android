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
import com.apollographql.apollo.exception.UnexpectedNullValue
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
  val searchAdapter: ResponseAdapter<List<TestQuery.Data.Search?>?> =
      NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Search(customScalarAdapters))))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var search: List<TestQuery.Data.Search?>? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> search = searchAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      search = search
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    searchAdapter.toResponse(writer, value.search)
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.List(ResponseField.Type.Named.Object("SearchResult")),
        responseName = "search",
        fieldName = "search",
        arguments = mapOf<String, Any?>(
          "text" to "test"),
        conditions = emptyList(),
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
    val characterSearchAdapter: CharacterSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch(customScalarAdapters)

    val starshipSearchAdapter: StarshipSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.StarshipSearch(customScalarAdapters)

    val otherSearchAdapter: OtherSearch =
        com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.OtherSearch(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Search {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> characterSearchAdapter.fromResponse(reader, typename)
        "Droid" -> characterSearchAdapter.fromResponse(reader, typename)
        "Starship" -> starshipSearchAdapter.fromResponse(reader, typename)
        else -> otherSearchAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search) {
      when(value) {
        is TestQuery.Data.Search.CharacterSearch -> characterSearchAdapter.toResponse(writer, value)
        is TestQuery.Data.Search.StarshipSearch -> starshipSearchAdapter.toResponse(writer, value)
        is TestQuery.Data.Search.OtherSearch -> otherSearchAdapter.toResponse(writer, value)
      }
    }

    class CharacterSearch(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val idAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val friendsAdapter: ResponseAdapter<List<TestQuery.Data.Search.CharacterSearch.Friend?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friend(customScalarAdapters))))

      fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Search.CharacterSearch {
        var __typename: String? = __typename
        var id: String? = null
        var name: String? = null
        var friends: List<TestQuery.Data.Search.CharacterSearch.Friend?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> id = idAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("id")
            2 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            3 -> friends = friendsAdapter.fromResponse(reader)
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
        __typenameAdapter.toResponse(writer, value.__typename)
        idAdapter.toResponse(writer, value.id)
        nameAdapter.toResponse(writer, value.name)
        friendsAdapter.toResponse(writer, value.friends)
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
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            responseName = "friends",
            fieldName = "friends",
            arguments = emptyMap(),
            conditions = emptyList(),
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
        val characterDroidFriendAdapter: CharacterDroidFriend =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.CharacterDroidFriend(customScalarAdapters)

        val characterHumanFriendAdapter: CharacterHumanFriend =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.CharacterHumanFriend(customScalarAdapters)

        val otherFriendAdapter: OtherFriend =
            com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.OtherFriend(customScalarAdapters)

        override fun fromResponse(reader: JsonReader):
            TestQuery.Data.Search.CharacterSearch.Friend {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "Droid" -> characterDroidFriendAdapter.fromResponse(reader, typename)
            "Human" -> characterHumanFriendAdapter.fromResponse(reader, typename)
            else -> otherFriendAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter,
            value: TestQuery.Data.Search.CharacterSearch.Friend) {
          when(value) {
            is TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend -> characterDroidFriendAdapter.toResponse(writer, value)
            is TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend -> characterHumanFriendAdapter.toResponse(writer, value)
            is TestQuery.Data.Search.CharacterSearch.Friend.OtherFriend -> otherFriendAdapter.toResponse(writer, value)
          }
        }

        class CharacterDroidFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val primaryFunctionAdapter: ResponseAdapter<String?> =
              NullableResponseAdapter(stringResponseAdapter)

          val friendsAdapter:
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
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                2 -> primaryFunction = primaryFunctionAdapter.fromResponse(reader)
                3 -> friends = friendsAdapter.fromResponse(reader)
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
            __typenameAdapter.toResponse(writer, value.__typename)
            nameAdapter.toResponse(writer, value.name)
            primaryFunctionAdapter.toResponse(writer, value.primaryFunction)
            friendsAdapter.toResponse(writer, value.friends)
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
                type = ResponseField.Type.Named.Other("String"),
                responseName = "primaryFunction",
                fieldName = "primaryFunction",
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
            val idAdapter: ResponseAdapter<String> = stringResponseAdapter

            override fun fromResponse(reader: JsonReader):
                TestQuery.Data.Search.CharacterSearch.Friend.CharacterDroidFriend.Friend {
              var id: String? = null
              reader.beginObject()
              while(true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                  0 -> id = idAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("id")
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
              idAdapter.toResponse(writer, value.id)
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
                )
              )

              val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
            }
          }
        }

        class CharacterHumanFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val homePlanetAdapter: ResponseAdapter<String?> =
              NullableResponseAdapter(stringResponseAdapter)

          val friendsAdapter:
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
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                2 -> homePlanet = homePlanetAdapter.fromResponse(reader)
                3 -> friends = friendsAdapter.fromResponse(reader)
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
            __typenameAdapter.toResponse(writer, value.__typename)
            nameAdapter.toResponse(writer, value.name)
            homePlanetAdapter.toResponse(writer, value.homePlanet)
            friendsAdapter.toResponse(writer, value.friends)
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
                type = ResponseField.Type.Named.Other("String"),
                responseName = "homePlanet",
                fieldName = "homePlanet",
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
            val characterFriendAdapter: CharacterFriend =
                com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.CharacterFriend(customScalarAdapters)

            val otherFriendAdapter: OtherFriend =
                com.example.union_inline_fragments.adapter.TestQuery_ResponseAdapter.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend(customScalarAdapters)

            override fun fromResponse(reader: JsonReader):
                TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend {
              reader.beginObject()
              check(reader.nextName() == "__typename")
              val typename = reader.nextString()

              return when(typename) {
                "Droid" -> characterFriendAdapter.fromResponse(reader, typename)
                "Human" -> characterFriendAdapter.fromResponse(reader, typename)
                else -> otherFriendAdapter.fromResponse(reader, typename)
              }
              .also { reader.endObject() }
            }

            override fun toResponse(writer: JsonWriter,
                value: TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend) {
              when(value) {
                is TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.CharacterFriend -> characterFriendAdapter.toResponse(writer, value)
                is TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend -> otherFriendAdapter.toResponse(writer, value)
              }
            }

            class CharacterFriend(
              customScalarAdapters: CustomScalarAdapters
            ) {
              val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

              val firstAppearsInAdapter: ResponseAdapter<Episode> = Episode_ResponseAdapter

              fun fromResponse(reader: JsonReader, __typename: String?):
                  TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.CharacterFriend {
                var __typename: String? = __typename
                var firstAppearsIn: Episode? = null
                while(true) {
                  when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                        UnexpectedNullValue("__typename")
                    1 -> firstAppearsIn = firstAppearsInAdapter.fromResponse(reader) ?: throw
                        UnexpectedNullValue("firstAppearsIn")
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
                __typenameAdapter.toResponse(writer, value.__typename)
                firstAppearsInAdapter.toResponse(writer, value.firstAppearsIn)
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
                    type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Episode")),
                    responseName = "firstAppearsIn",
                    fieldName = "firstAppearsIn",
                    arguments = emptyMap(),
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

              fun fromResponse(reader: JsonReader, __typename: String?):
                  TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend {
                var __typename: String? = __typename
                while(true) {
                  when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                        UnexpectedNullValue("__typename")
                    else -> break
                  }
                }
                return TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend(
                  __typename = __typename!!
                )
              }

              fun toResponse(writer: JsonWriter,
                  value: TestQuery.Data.Search.CharacterSearch.Friend.CharacterHumanFriend.Friend.OtherFriend) {
                __typenameAdapter.toResponse(writer, value.__typename)
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
                  )
                )

                val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
              }
            }
          }
        }

        class OtherFriend(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Search.CharacterSearch.Friend.OtherFriend {
            var __typename: String? = __typename
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                else -> break
              }
            }
            return TestQuery.Data.Search.CharacterSearch.Friend.OtherFriend(
              __typename = __typename!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Search.CharacterSearch.Friend.OtherFriend) {
            __typenameAdapter.toResponse(writer, value.__typename)
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
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }
      }
    }

    class StarshipSearch(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Search.StarshipSearch {
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
        return TestQuery.Data.Search.StarshipSearch(
          __typename = __typename!!,
          name = name!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search.StarshipSearch) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
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

    class OtherSearch(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Search.OtherSearch {
        var __typename: String? = __typename
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            else -> break
          }
        }
        return TestQuery.Data.Search.OtherSearch(
          __typename = __typename!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Search.OtherSearch) {
        __typenameAdapter.toResponse(writer, value.__typename)
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
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }
  }
}
