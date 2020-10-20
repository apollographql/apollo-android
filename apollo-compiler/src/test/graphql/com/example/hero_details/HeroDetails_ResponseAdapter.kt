// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_details

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.hero_details.type.Hero_type
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
internal object HeroDetails_ResponseAdapter : ResponseAdapter<HeroDetails.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField.forObject("hero", "hero", null, true, null)
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetails.Data {
    return reader.run {
      var hero: HeroDetails.Hero? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> hero = readObject<HeroDetails.Hero>(RESPONSE_FIELDS[0]) { reader ->
            HeroDetails_ResponseAdapter.Hero_ResponseAdapter.fromResponse(reader)
          }
          else -> break
        }
      }
      HeroDetails.Data(
        hero = hero
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: HeroDetails.Data) {
    if(value.hero == null) {
      writer.writeObject(RESPONSE_FIELDS[0], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[0]) {
        HeroDetails_ResponseAdapter.Hero_ResponseAdapter.toResponse(writer, value.hero)
      }
    }
  }

  object Node_ResponseAdapter : ResponseAdapter<HeroDetails.Node> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, false, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetails.Node {
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
        HeroDetails.Node(
          __typename = __typename!!,
          name = name!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: HeroDetails.Node) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
    }
  }

  object Edge_ResponseAdapter : ResponseAdapter<HeroDetails.Edge> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forObject("node", "node", null, true, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetails.Edge {
      return reader.run {
        var __typename: String? = __typename
        var node: HeroDetails.Node? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> node = readObject<HeroDetails.Node>(RESPONSE_FIELDS[1]) { reader ->
              HeroDetails_ResponseAdapter.Node_ResponseAdapter.fromResponse(reader)
            }
            else -> break
          }
        }
        HeroDetails.Edge(
          __typename = __typename!!,
          node = node
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: HeroDetails.Edge) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      if(value.node == null) {
        writer.writeObject(RESPONSE_FIELDS[1], null)
      } else {
        writer.writeObject(RESPONSE_FIELDS[1]) {
          HeroDetails_ResponseAdapter.Node_ResponseAdapter.toResponse(writer, value.node)
        }
      }
    }
  }

  object FriendsConnection_ResponseAdapter : ResponseAdapter<HeroDetails.FriendsConnection> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forInt("totalCount", "totalCount", null, true, null),
      ResponseField.forList("edges", "edges", null, true, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?):
        HeroDetails.FriendsConnection {
      return reader.run {
        var __typename: String? = __typename
        var totalCount: Int? = null
        var edges: List<HeroDetails.Edge?>? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> totalCount = readInt(RESPONSE_FIELDS[1])
            2 -> edges = readList<HeroDetails.Edge>(RESPONSE_FIELDS[2]) { reader ->
              reader.readObject<HeroDetails.Edge> { reader ->
                HeroDetails_ResponseAdapter.Edge_ResponseAdapter.fromResponse(reader)
              }
            }
            else -> break
          }
        }
        HeroDetails.FriendsConnection(
          __typename = __typename!!,
          totalCount = totalCount,
          edges = edges
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: HeroDetails.FriendsConnection) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeInt(RESPONSE_FIELDS[1], value.totalCount)
      writer.writeList(RESPONSE_FIELDS[2], value.edges) { value, listItemWriter ->
        value?.forEach { value ->
          if(value == null) {
            listItemWriter.writeObject(null)
          } else {
            listItemWriter.writeObject {
              HeroDetails_ResponseAdapter.Edge_ResponseAdapter.toResponse(writer, value)
            }
          }
        }
      }
    }
  }

  object Hero_ResponseAdapter : ResponseAdapter<HeroDetails.Hero> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forEnum("type", "type", null, false, null),
      ResponseField.forString("name", "name", null, false, null),
      ResponseField.forObject("friendsConnection", "friendsConnection", null, false, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetails.Hero {
      return reader.run {
        var __typename: String? = __typename
        var type: Hero_type? = null
        var name: String? = null
        var friendsConnection: HeroDetails.FriendsConnection? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> type = readString(RESPONSE_FIELDS[1])?.let { Hero_type.safeValueOf(it) }
            2 -> name = readString(RESPONSE_FIELDS[2])
            3 -> friendsConnection = readObject<HeroDetails.FriendsConnection>(RESPONSE_FIELDS[3]) { reader ->
              HeroDetails_ResponseAdapter.FriendsConnection_ResponseAdapter.fromResponse(reader)
            }
            else -> break
          }
        }
        HeroDetails.Hero(
          __typename = __typename!!,
          type = type!!,
          name = name!!,
          friendsConnection = friendsConnection!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: HeroDetails.Hero) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.type.rawValue)
      writer.writeString(RESPONSE_FIELDS[2], value.name)
      writer.writeObject(RESPONSE_FIELDS[3]) {
        HeroDetails_ResponseAdapter.FriendsConnection_ResponseAdapter.toResponse(writer, value.friendsConnection)
      }
    }
  }
}
