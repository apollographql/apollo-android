// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.arguments_simple

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField.forObject("hero", "hero", mapOf<String, Any>(
      "episode" to mapOf<String, Any>(
        "kind" to "Variable",
        "variableName" to "episode"),
      "listOfListOfStringArgs" to mapOf<String, Any>(
        "kind" to "Variable",
        "variableName" to "listOfListOfStringArgs")), true, null),
    ResponseField.forObject("heroWithReview", "heroWithReview", mapOf<String, Any>(
      "episode" to mapOf<String, Any>(
        "kind" to "Variable",
        "variableName" to "episode"),
      "review" to emptyMap<String, Any>()), true, null)
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return reader.run {
      var hero: TestQuery.Hero? = null
      var heroWithReview: TestQuery.HeroWithReview? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> hero = readObject<TestQuery.Hero>(RESPONSE_FIELDS[0]) { reader ->
            TestQuery_ResponseAdapter.Hero_ResponseAdapter.fromResponse(reader)
          }
          1 -> heroWithReview = readObject<TestQuery.HeroWithReview>(RESPONSE_FIELDS[1]) { reader ->
            TestQuery_ResponseAdapter.HeroWithReview_ResponseAdapter.fromResponse(reader)
          }
          else -> break
        }
      }
      TestQuery.Data(
        hero = hero,
        heroWithReview = heroWithReview
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: TestQuery.Data) {
    if(value.hero == null) {
      writer.writeObject(RESPONSE_FIELDS[0], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
        TestQuery_ResponseAdapter.Hero_ResponseAdapter.toResponse(writer, value.hero)
      }
    }
    if(value.heroWithReview == null) {
      writer.writeObject(RESPONSE_FIELDS[1], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[1]) { writer ->
        TestQuery_ResponseAdapter.HeroWithReview_ResponseAdapter.toResponse(writer, value.heroWithReview)
      }
    }
  }

  object Node_ResponseAdapter : ResponseAdapter<TestQuery.Node> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, true, listOf(
        ResponseField.Condition.booleanCondition("IncludeName", false)
      ))
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Node {
      return reader.run {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            else -> break
          }
        }
        TestQuery.Node(
          __typename = __typename!!,
          name = name
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Node) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
    }
  }

  object Edge_ResponseAdapter : ResponseAdapter<TestQuery.Edge> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forObject("node", "node", null, true, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Edge {
      return reader.run {
        var __typename: String? = __typename
        var node: TestQuery.Node? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> node = readObject<TestQuery.Node>(RESPONSE_FIELDS[1]) { reader ->
              TestQuery_ResponseAdapter.Node_ResponseAdapter.fromResponse(reader)
            }
            else -> break
          }
        }
        TestQuery.Edge(
          __typename = __typename!!,
          node = node
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Edge) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      if(value.node == null) {
        writer.writeObject(RESPONSE_FIELDS[1], null)
      } else {
        writer.writeObject(RESPONSE_FIELDS[1]) { writer ->
          TestQuery_ResponseAdapter.Node_ResponseAdapter.toResponse(writer, value.node)
        }
      }
    }
  }

  object FriendsConnection_ResponseAdapter : ResponseAdapter<TestQuery.FriendsConnection> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forInt("totalCount", "totalCount", null, true, null),
      ResponseField.forList("edges", "edges", null, true, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?):
        TestQuery.FriendsConnection {
      return reader.run {
        var __typename: String? = __typename
        var totalCount: Int? = null
        var edges: List<TestQuery.Edge?>? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> totalCount = readInt(RESPONSE_FIELDS[1])
            2 -> edges = readList<TestQuery.Edge>(RESPONSE_FIELDS[2]) { reader ->
              reader.readObject<TestQuery.Edge> { reader ->
                TestQuery_ResponseAdapter.Edge_ResponseAdapter.fromResponse(reader)
              }
            }
            else -> break
          }
        }
        TestQuery.FriendsConnection(
          __typename = __typename!!,
          totalCount = totalCount,
          edges = edges
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.FriendsConnection) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeInt(RESPONSE_FIELDS[1], value.totalCount)
      writer.writeList(RESPONSE_FIELDS[2], value.edges) { values, listItemWriter ->
        values?.forEach { value ->
          if(value == null) {
            listItemWriter.writeObject(null)
          } else {
            listItemWriter.writeObject { writer ->
              TestQuery_ResponseAdapter.Edge_ResponseAdapter.toResponse(writer, value)
            }
          }
        }
      }
    }
  }

  object Hero_ResponseAdapter : ResponseAdapter<TestQuery.Hero> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, true, listOf(
        ResponseField.Condition.booleanCondition("IncludeName", false)
      )),
      ResponseField.forObject("friendsConnection", "friendsConnection", mapOf<String, Any>(
        "first" to mapOf<String, Any>(
          "kind" to "Variable",
          "variableName" to "friendsCount")), false, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Hero {
      return reader.run {
        var __typename: String? = __typename
        var name: String? = null
        var friendsConnection: TestQuery.FriendsConnection? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            2 -> friendsConnection = readObject<TestQuery.FriendsConnection>(RESPONSE_FIELDS[2]) { reader ->
              TestQuery_ResponseAdapter.FriendsConnection_ResponseAdapter.fromResponse(reader)
            }
            else -> break
          }
        }
        TestQuery.Hero(
          __typename = __typename!!,
          name = name,
          friendsConnection = friendsConnection!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Hero) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
      writer.writeObject(RESPONSE_FIELDS[2]) { writer ->
        TestQuery_ResponseAdapter.FriendsConnection_ResponseAdapter.toResponse(writer, value.friendsConnection)
      }
    }
  }

  object HeroWithReview_ResponseAdapter : ResponseAdapter<TestQuery.HeroWithReview> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, false, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?):
        TestQuery.HeroWithReview {
      return reader.run {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            else -> break
          }
        }
        TestQuery.HeroWithReview(
          __typename = __typename!!,
          name = name!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.HeroWithReview) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
    }
  }
}
