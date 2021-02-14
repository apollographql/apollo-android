// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_friends_connection.fragment.adapter

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
import com.example.fragment_friends_connection.fragment.HeroDetailsImpl
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HeroDetailsImpl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<HeroDetailsImpl.Data> {
  val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val friendsConnectionAdapter: ResponseAdapter<HeroDetailsImpl.Data.FriendsConnection> =
      FriendsConnection(customScalarAdapters)

  override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data {
    var __typename: String? = null
    var name: String? = null
    var friendsConnection: HeroDetailsImpl.Data.FriendsConnection? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("__typename")
        1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
        2 -> friendsConnection = friendsConnectionAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("friendsConnection")
        else -> break
      }
    }
    reader.endObject()
    return HeroDetailsImpl.Data(
      __typename = __typename!!,
      name = name!!,
      friendsConnection = friendsConnection!!
    )
  }

  override fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data) {
    writer.beginObject()
    writer.name("__typename")
    __typenameAdapter.toResponse(writer, value.__typename)
    writer.name("name")
    nameAdapter.toResponse(writer, value.name)
    writer.name("friendsConnection")
    friendsConnectionAdapter.toResponse(writer, value.friendsConnection)
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
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("FriendsConnection")),
        fieldName = "friendsConnection",
        fieldSets = listOf(
          ResponseField.FieldSet(null, FriendsConnection.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class FriendsConnection(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<HeroDetailsImpl.Data.FriendsConnection> {
    val totalCountAdapter: ResponseAdapter<Int?> = NullableResponseAdapter(intResponseAdapter)

    val edgesAdapter: ResponseAdapter<List<HeroDetailsImpl.Data.FriendsConnection.Edge?>?> =
        NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

    override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data.FriendsConnection {
      var totalCount: Int? = null
      var edges: List<HeroDetailsImpl.Data.FriendsConnection.Edge?>? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> totalCount = totalCountAdapter.fromResponse(reader)
          1 -> edges = edgesAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return HeroDetailsImpl.Data.FriendsConnection(
        totalCount = totalCount,
        edges = edges
      )
    }

    override fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.FriendsConnection) {
      writer.beginObject()
      writer.name("totalCount")
      totalCountAdapter.toResponse(writer, value.totalCount)
      writer.name("edges")
      edgesAdapter.toResponse(writer, value.edges)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.Named.Other("Int"),
          fieldName = "totalCount",
        ),
        ResponseField(
          type = ResponseField.Type.List(ResponseField.Type.Named.Object("FriendsEdge")),
          fieldName = "edges",
          fieldSets = listOf(
            ResponseField.FieldSet(null, Edge.RESPONSE_FIELDS)
          ),
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }

    class Edge(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<HeroDetailsImpl.Data.FriendsConnection.Edge> {
      val nodeAdapter: ResponseAdapter<HeroDetailsImpl.Data.FriendsConnection.Edge.Node?> =
          NullableResponseAdapter(Node(customScalarAdapters))

      override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data.FriendsConnection.Edge {
        var node: HeroDetailsImpl.Data.FriendsConnection.Edge.Node? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> node = nodeAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return HeroDetailsImpl.Data.FriendsConnection.Edge(
          node = node
        )
      }

      override fun toResponse(writer: JsonWriter,
          value: HeroDetailsImpl.Data.FriendsConnection.Edge) {
        writer.beginObject()
        writer.name("node")
        nodeAdapter.toResponse(writer, value.node)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.Named.Object("Character"),
            fieldName = "node",
            fieldSets = listOf(
              ResponseField.FieldSet(null, Node.RESPONSE_FIELDS)
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Node(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<HeroDetailsImpl.Data.FriendsConnection.Edge.Node> {
        val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

        override fun fromResponse(reader: JsonReader):
            HeroDetailsImpl.Data.FriendsConnection.Edge.Node {
          var name: String? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
              else -> break
            }
          }
          reader.endObject()
          return HeroDetailsImpl.Data.FriendsConnection.Edge.Node(
            name = name!!
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: HeroDetailsImpl.Data.FriendsConnection.Edge.Node) {
          writer.beginObject()
          writer.name("name")
          nameAdapter.toResponse(writer, value.name)
          writer.endObject()
        }

        companion object {
          val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
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
}
