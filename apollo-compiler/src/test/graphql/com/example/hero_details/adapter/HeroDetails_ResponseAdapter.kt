// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_details.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.hero_details.HeroDetails
import com.example.hero_details.type.Hero_type
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object HeroDetails_ResponseAdapter : ResponseAdapter<HeroDetails.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.Named.Object("Character"),
      responseName = "hero",
      fieldName = "hero",
      arguments = emptyMap(),
      conditions = emptyList(),
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetails.Data {
    return reader.run {
      var hero: HeroDetails.Data.Hero? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> hero = readObject<HeroDetails.Data.Hero>(RESPONSE_FIELDS[0]) { reader ->
            Hero.fromResponse(reader)
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
      writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
        Hero.toResponse(writer, value.hero)
      }
    }
  }

  object Hero : ResponseAdapter<HeroDetails.Data.Hero> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("hero_type")),
        responseName = "type",
        fieldName = "type",
        arguments = emptyMap(),
        conditions = emptyList(),
      ),
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "name",
        fieldName = "name",
        arguments = emptyMap(),
        conditions = emptyList(),
      ),
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("FriendsConnection")),
        responseName = "friendsConnection",
        fieldName = "friendsConnection",
        arguments = emptyMap(),
        conditions = emptyList(),
      )
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetails.Data.Hero {
      return reader.run {
        var type: Hero_type? = null
        var name: String? = null
        var friendsConnection: HeroDetails.Data.Hero.FriendsConnection? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> type = readString(RESPONSE_FIELDS[0])?.let { Hero_type.safeValueOf(it) }
            1 -> name = readString(RESPONSE_FIELDS[1])
            2 -> friendsConnection = readObject<HeroDetails.Data.Hero.FriendsConnection>(RESPONSE_FIELDS[2]) { reader ->
              FriendsConnection.fromResponse(reader)
            }
            else -> break
          }
        }
        HeroDetails.Data.Hero(
          type = type!!,
          name = name!!,
          friendsConnection = friendsConnection!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: HeroDetails.Data.Hero) {
      writer.writeString(RESPONSE_FIELDS[0], value.type.rawValue)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
      writer.writeObject(RESPONSE_FIELDS[2]) { writer ->
        FriendsConnection.toResponse(writer, value.friendsConnection)
      }
    }

    object FriendsConnection : ResponseAdapter<HeroDetails.Data.Hero.FriendsConnection> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.Named.Other("Int"),
          responseName = "totalCount",
          fieldName = "totalCount",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.List(ResponseField.Type.Named.Object("FriendsEdge")),
          responseName = "edges",
          fieldName = "edges",
          arguments = emptyMap(),
          conditions = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          HeroDetails.Data.Hero.FriendsConnection {
        return reader.run {
          var totalCount: Int? = null
          var edges: List<HeroDetails.Data.Hero.FriendsConnection.Edge?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> totalCount = readInt(RESPONSE_FIELDS[0])
              1 -> edges = readList<HeroDetails.Data.Hero.FriendsConnection.Edge>(RESPONSE_FIELDS[1]) { reader ->
                reader.readObject<HeroDetails.Data.Hero.FriendsConnection.Edge> { reader ->
                  Edge.fromResponse(reader)
                }
              }
              else -> break
            }
          }
          HeroDetails.Data.Hero.FriendsConnection(
            totalCount = totalCount,
            edges = edges
          )
        }
      }

      override fun toResponse(writer: ResponseWriter,
          value: HeroDetails.Data.Hero.FriendsConnection) {
        writer.writeInt(RESPONSE_FIELDS[0], value.totalCount)
        writer.writeList(RESPONSE_FIELDS[1], value.edges) { value, listItemWriter ->
          listItemWriter.writeObject { writer ->
            Edge.toResponse(writer, value)
          }
        }
      }

      object Edge : ResponseAdapter<HeroDetails.Data.Hero.FriendsConnection.Edge> {
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.Named.Object("Character"),
            responseName = "node",
            fieldName = "node",
            arguments = emptyMap(),
            conditions = emptyList(),
          )
        )

        override fun fromResponse(reader: ResponseReader, __typename: String?):
            HeroDetails.Data.Hero.FriendsConnection.Edge {
          return reader.run {
            var node: HeroDetails.Data.Hero.FriendsConnection.Edge.Node? = null
            while(true) {
              when (selectField(RESPONSE_FIELDS)) {
                0 -> node = readObject<HeroDetails.Data.Hero.FriendsConnection.Edge.Node>(RESPONSE_FIELDS[0]) { reader ->
                  Node.fromResponse(reader)
                }
                else -> break
              }
            }
            HeroDetails.Data.Hero.FriendsConnection.Edge(
              node = node
            )
          }
        }

        override fun toResponse(writer: ResponseWriter,
            value: HeroDetails.Data.Hero.FriendsConnection.Edge) {
          if(value.node == null) {
            writer.writeObject(RESPONSE_FIELDS[0], null)
          } else {
            writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
              Node.toResponse(writer, value.node)
            }
          }
        }

        object Node : ResponseAdapter<HeroDetails.Data.Hero.FriendsConnection.Edge.Node> {
          private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField(
              type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
              responseName = "name",
              fieldName = "name",
              arguments = emptyMap(),
              conditions = emptyList(),
            )
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              HeroDetails.Data.Hero.FriendsConnection.Edge.Node {
            return reader.run {
              var name: String? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> name = readString(RESPONSE_FIELDS[0])
                  else -> break
                }
              }
              HeroDetails.Data.Hero.FriendsConnection.Edge.Node(
                name = name!!
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: HeroDetails.Data.Hero.FriendsConnection.Edge.Node) {
            writer.writeString(RESPONSE_FIELDS[0], value.name)
          }
        }
      }
    }
  }
}
