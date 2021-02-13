// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_with_inline_fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.intResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.fragment_with_inline_fragment.TestQuery
import com.example.fragment_with_inline_fragment.type.Episode
import com.example.fragment_with_inline_fragment.type.Episode_ResponseAdapter
import kotlin.Array
import kotlin.Int
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
    heroAdapter.toResponse(writer, value.hero)
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        responseName = "hero",
        fieldName = "hero",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet("Droid", Hero.CharacterDroidHero.RESPONSE_FIELDS),
          ResponseField.FieldSet("Human", Hero.CharacterHumanHero.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val characterDroidHeroAdapter: CharacterDroidHero =
        com.example.fragment_with_inline_fragment.adapter.TestQuery_ResponseAdapter.Hero.CharacterDroidHero(customScalarAdapters)

    val characterHumanHeroAdapter: CharacterHumanHero =
        com.example.fragment_with_inline_fragment.adapter.TestQuery_ResponseAdapter.Hero.CharacterHumanHero(customScalarAdapters)

    val otherHeroAdapter: OtherHero =
        com.example.fragment_with_inline_fragment.adapter.TestQuery_ResponseAdapter.Hero.OtherHero(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Droid" -> characterDroidHeroAdapter.fromResponse(reader, typename)
        "Human" -> characterHumanHeroAdapter.fromResponse(reader, typename)
        else -> otherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.CharacterDroidHero -> characterDroidHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.CharacterHumanHero -> characterHumanHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> otherHeroAdapter.toResponse(writer, value)
      }
    }

    class CharacterDroidHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val appearsInAdapter: ResponseAdapter<List<Episode?>> =
          ListResponseAdapter(NullableResponseAdapter(Episode_ResponseAdapter))

      val friendsConnectionAdapter:
          ResponseAdapter<TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection> =
          FriendsConnection(customScalarAdapters)

      val primaryFunctionAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(stringResponseAdapter)

      fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.CharacterDroidHero {
        var __typename: String? = __typename
        var name: String? = null
        var appearsIn: List<Episode?>? = null
        var friendsConnection: TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection? = null
        var primaryFunction: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> appearsIn = appearsInAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("appearsIn")
            3 -> friendsConnection = friendsConnectionAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("friendsConnection")
            4 -> primaryFunction = primaryFunctionAdapter.fromResponse(reader)
            else -> break
          }
        }
        return TestQuery.Data.Hero.CharacterDroidHero(
          __typename = __typename!!,
          name = name!!,
          appearsIn = appearsIn!!,
          friendsConnection = friendsConnection!!,
          primaryFunction = primaryFunction
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.CharacterDroidHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        appearsInAdapter.toResponse(writer, value.appearsIn)
        friendsConnectionAdapter.toResponse(writer, value.friendsConnection)
        primaryFunctionAdapter.toResponse(writer, value.primaryFunction)
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
            type =
                ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.Named.Other("Episode"))),
            responseName = "appearsIn",
            fieldName = "appearsIn",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("FriendsConnection")),
            responseName = "friendsConnection",
            fieldName = "friendsConnection",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet(null, FriendsConnection.RESPONSE_FIELDS)
            ),
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

      class FriendsConnection(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection> {
        val totalCountAdapter: ResponseAdapter<Int?> = NullableResponseAdapter(intResponseAdapter)

        val edgesAdapter:
            ResponseAdapter<List<TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge?>?> =
            NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

        override fun fromResponse(reader: JsonReader):
            TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection {
          var totalCount: Int? = null
          var edges: List<TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge?>? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> totalCount = totalCountAdapter.fromResponse(reader)
              1 -> edges = edgesAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection(
            totalCount = totalCount,
            edges = edges
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection) {
          totalCountAdapter.toResponse(writer, value.totalCount)
          edgesAdapter.toResponse(writer, value.edges)
        }

        companion object {
          val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField(
              type = ResponseField.Type.Named.Other("Int"),
              responseName = "totalCount",
              fieldName = "totalCount",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            ),
            ResponseField(
              type = ResponseField.Type.List(ResponseField.Type.Named.Object("FriendsEdge")),
              responseName = "edges",
              fieldName = "edges",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = listOf(
                ResponseField.FieldSet(null, Edge.RESPONSE_FIELDS)
              ),
            )
          )

          val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
        }

        class Edge(
          customScalarAdapters: CustomScalarAdapters
        ) : ResponseAdapter<TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge> {
          val nodeAdapter:
              ResponseAdapter<TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge.Node?> =
              NullableResponseAdapter(Node(customScalarAdapters))

          override fun fromResponse(reader: JsonReader):
              TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge {
            var node: TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge.Node? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> node = nodeAdapter.fromResponse(reader)
                else -> break
              }
            }
            reader.endObject()
            return TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge(
              node = node
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge) {
            nodeAdapter.toResponse(writer, value.node)
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField(
                type = ResponseField.Type.Named.Object("Character"),
                responseName = "node",
                fieldName = "node",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = listOf(
                  ResponseField.FieldSet(null, Node.RESPONSE_FIELDS)
                ),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }

          class Node(
            customScalarAdapters: CustomScalarAdapters
          ) : ResponseAdapter<TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge.Node> {
            val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

            override fun fromResponse(reader: JsonReader):
                TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge.Node {
              var name: String? = null
              reader.beginObject()
              while(true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                  0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                  else -> break
                }
              }
              reader.endObject()
              return TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge.Node(
                name = name!!
              )
            }

            override fun toResponse(writer: JsonWriter,
                value: TestQuery.Data.Hero.CharacterDroidHero.FriendsConnection.Edge.Node) {
              nameAdapter.toResponse(writer, value.name)
            }

            companion object {
              val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
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
    }

    class CharacterHumanHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val appearsInAdapter: ResponseAdapter<List<Episode?>> =
          ListResponseAdapter(NullableResponseAdapter(Episode_ResponseAdapter))

      val friendsConnectionAdapter:
          ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection> =
          FriendsConnection(customScalarAdapters)

      fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.CharacterHumanHero {
        var __typename: String? = __typename
        var name: String? = null
        var appearsIn: List<Episode?>? = null
        var friendsConnection: TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> appearsIn = appearsInAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("appearsIn")
            3 -> friendsConnection = friendsConnectionAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("friendsConnection")
            else -> break
          }
        }
        return TestQuery.Data.Hero.CharacterHumanHero(
          __typename = __typename!!,
          name = name!!,
          appearsIn = appearsIn!!,
          friendsConnection = friendsConnection!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.CharacterHumanHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        appearsInAdapter.toResponse(writer, value.appearsIn)
        friendsConnectionAdapter.toResponse(writer, value.friendsConnection)
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
            type =
                ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.Named.Other("Episode"))),
            responseName = "appearsIn",
            fieldName = "appearsIn",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("FriendsConnection")),
            responseName = "friendsConnection",
            fieldName = "friendsConnection",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet(null, FriendsConnection.RESPONSE_FIELDS)
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class FriendsConnection(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection> {
        val totalCountAdapter: ResponseAdapter<Int?> = NullableResponseAdapter(intResponseAdapter)

        val edgesAdapter:
            ResponseAdapter<List<TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge?>?> =
            NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

        override fun fromResponse(reader: JsonReader):
            TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection {
          var totalCount: Int? = null
          var edges: List<TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge?>? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> totalCount = totalCountAdapter.fromResponse(reader)
              1 -> edges = edgesAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection(
            totalCount = totalCount,
            edges = edges
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection) {
          totalCountAdapter.toResponse(writer, value.totalCount)
          edgesAdapter.toResponse(writer, value.edges)
        }

        companion object {
          val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField(
              type = ResponseField.Type.Named.Other("Int"),
              responseName = "totalCount",
              fieldName = "totalCount",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            ),
            ResponseField(
              type = ResponseField.Type.List(ResponseField.Type.Named.Object("FriendsEdge")),
              responseName = "edges",
              fieldName = "edges",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = listOf(
                ResponseField.FieldSet(null, Edge.RESPONSE_FIELDS)
              ),
            )
          )

          val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
        }

        class Edge(
          customScalarAdapters: CustomScalarAdapters
        ) : ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge> {
          val nodeAdapter:
              ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge.Node?> =
              NullableResponseAdapter(Node(customScalarAdapters))

          override fun fromResponse(reader: JsonReader):
              TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge {
            var node: TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge.Node? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> node = nodeAdapter.fromResponse(reader)
                else -> break
              }
            }
            reader.endObject()
            return TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge(
              node = node
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge) {
            nodeAdapter.toResponse(writer, value.node)
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField(
                type = ResponseField.Type.Named.Object("Character"),
                responseName = "node",
                fieldName = "node",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = listOf(
                  ResponseField.FieldSet(null, Node.RESPONSE_FIELDS)
                ),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }

          class Node(
            customScalarAdapters: CustomScalarAdapters
          ) : ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge.Node> {
            val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

            override fun fromResponse(reader: JsonReader):
                TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge.Node {
              var name: String? = null
              reader.beginObject()
              while(true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                  0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                  else -> break
                }
              }
              reader.endObject()
              return TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge.Node(
                name = name!!
              )
            }

            override fun toResponse(writer: JsonWriter,
                value: TestQuery.Data.Hero.CharacterHumanHero.FriendsConnection.Edge.Node) {
              nameAdapter.toResponse(writer, value.name)
            }

            companion object {
              val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
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
    }

    class OtherHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val appearsInAdapter: ResponseAdapter<List<Episode?>> =
          ListResponseAdapter(NullableResponseAdapter(Episode_ResponseAdapter))

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero.OtherHero {
        var __typename: String? = __typename
        var name: String? = null
        var appearsIn: List<Episode?>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> appearsIn = appearsInAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("appearsIn")
            else -> break
          }
        }
        return TestQuery.Data.Hero.OtherHero(
          __typename = __typename!!,
          name = name!!,
          appearsIn = appearsIn!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.OtherHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        appearsInAdapter.toResponse(writer, value.appearsIn)
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
            type =
                ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.Named.Other("Episode"))),
            responseName = "appearsIn",
            fieldName = "appearsIn",
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
