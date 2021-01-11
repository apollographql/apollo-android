// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_friends_connection.fragment.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.fragment_friends_connection.fragment.HeroDetailsImpl
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object HeroDetailsImpl_ResponseAdapter : ResponseAdapter<HeroDetailsImpl.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField.forString("__typename", "__typename", null, false, null),
    ResponseField.forString("name", "name", null, false, null),
    ResponseField.forObject("friendsConnection", "friendsConnection", null, false, null)
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetailsImpl.Data {
    return Data.fromResponse(reader, __typename)
  }

  override fun toResponse(writer: ResponseWriter, value: HeroDetailsImpl.Data) {
    Data.toResponse(writer, value)
  }

  object Data : ResponseAdapter<HeroDetailsImpl.Data> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, false, null),
      ResponseField.forObject("friendsConnection", "friendsConnection", null, false, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetailsImpl.Data {
      return reader.run {
        var __typename: String? = __typename
        var name: String? = null
        var friendsConnection: HeroDetailsImpl.Data.FriendsConnection? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            2 -> friendsConnection = readObject<HeroDetailsImpl.Data.FriendsConnection>(RESPONSE_FIELDS[2]) { reader ->
              FriendsConnection.fromResponse(reader)
            }
            else -> break
          }
        }
        HeroDetailsImpl.Data(
          __typename = __typename!!,
          name = name!!,
          friendsConnection = friendsConnection!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: HeroDetailsImpl.Data) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
      writer.writeObject(RESPONSE_FIELDS[2]) { writer ->
        FriendsConnection.toResponse(writer, value.friendsConnection)
      }
    }

    object FriendsConnection : ResponseAdapter<HeroDetailsImpl.Data.FriendsConnection> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forInt("totalCount", "totalCount", null, true, null),
        ResponseField.forList("edges", "edges", null, true, null)
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          HeroDetailsImpl.Data.FriendsConnection {
        return reader.run {
          var totalCount: Int? = null
          var edges: List<HeroDetailsImpl.Data.FriendsConnection.Edge?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> totalCount = readInt(RESPONSE_FIELDS[0])
              1 -> edges = readList<HeroDetailsImpl.Data.FriendsConnection.Edge>(RESPONSE_FIELDS[1]) { reader ->
                reader.readObject<HeroDetailsImpl.Data.FriendsConnection.Edge> { reader ->
                  Edge.fromResponse(reader)
                }
              }
              else -> break
            }
          }
          HeroDetailsImpl.Data.FriendsConnection(
            totalCount = totalCount,
            edges = edges
          )
        }
      }

      override fun toResponse(writer: ResponseWriter,
          value: HeroDetailsImpl.Data.FriendsConnection) {
        writer.writeInt(RESPONSE_FIELDS[0], value.totalCount)
        writer.writeList(RESPONSE_FIELDS[1], value.edges) { value, listItemWriter ->
          listItemWriter.writeObject { writer ->
            Edge.toResponse(writer, value)
          }
        }
      }

      object Edge : ResponseAdapter<HeroDetailsImpl.Data.FriendsConnection.Edge> {
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("node", "node", null, true, null)
        )

        override fun fromResponse(reader: ResponseReader, __typename: String?):
            HeroDetailsImpl.Data.FriendsConnection.Edge {
          return reader.run {
            var node: HeroDetailsImpl.Data.FriendsConnection.Edge.Node? = null
            while(true) {
              when (selectField(RESPONSE_FIELDS)) {
                0 -> node = readObject<HeroDetailsImpl.Data.FriendsConnection.Edge.Node>(RESPONSE_FIELDS[0]) { reader ->
                  Node.fromResponse(reader)
                }
                else -> break
              }
            }
            HeroDetailsImpl.Data.FriendsConnection.Edge(
              node = node
            )
          }
        }

        override fun toResponse(writer: ResponseWriter,
            value: HeroDetailsImpl.Data.FriendsConnection.Edge) {
          if(value.node == null) {
            writer.writeObject(RESPONSE_FIELDS[0], null)
          } else {
            writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
              Node.toResponse(writer, value.node)
            }
          }
        }

        object Node : ResponseAdapter<HeroDetailsImpl.Data.FriendsConnection.Edge.Node> {
          private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField.forString("name", "name", null, false, null)
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              HeroDetailsImpl.Data.FriendsConnection.Edge.Node {
            return reader.run {
              var name: String? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> name = readString(RESPONSE_FIELDS[0])
                  else -> break
                }
              }
              HeroDetailsImpl.Data.FriendsConnection.Edge.Node(
                name = name!!
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: HeroDetailsImpl.Data.FriendsConnection.Edge.Node) {
            writer.writeString(RESPONSE_FIELDS[0], value.name)
          }
        }
      }
    }
  }
}
