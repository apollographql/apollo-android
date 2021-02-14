// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_with_inline_fragment.fragment.adapter

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
import com.example.fragment_with_inline_fragment.fragment.HeroDetailsImpl
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
  val droidDataAdapter: DroidData =
      com.example.fragment_with_inline_fragment.fragment.adapter.HeroDetailsImpl_ResponseAdapter.DroidData(customScalarAdapters)

  val humanDataAdapter: HumanData =
      com.example.fragment_with_inline_fragment.fragment.adapter.HeroDetailsImpl_ResponseAdapter.HumanData(customScalarAdapters)

  val otherDataAdapter: OtherData =
      com.example.fragment_with_inline_fragment.fragment.adapter.HeroDetailsImpl_ResponseAdapter.OtherData(customScalarAdapters)

  override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data {
    reader.beginObject()
    check(reader.nextName() == "__typename")
    val typename = reader.nextString()

    return when(typename) {
      "Droid" -> droidDataAdapter.fromResponse(reader, typename)
      "Human" -> humanDataAdapter.fromResponse(reader, typename)
      else -> otherDataAdapter.fromResponse(reader, typename)
    }
    .also { reader.endObject() }
  }

  override fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data) {
    when(value) {
      is HeroDetailsImpl.Data.DroidData -> droidDataAdapter.toResponse(writer, value)
      is HeroDetailsImpl.Data.HumanData -> humanDataAdapter.toResponse(writer, value)
      is HeroDetailsImpl.Data.OtherData -> otherDataAdapter.toResponse(writer, value)
    }
  }

  class DroidData(
    customScalarAdapters: CustomScalarAdapters
  ) {
    val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val friendsConnectionAdapter: ResponseAdapter<HeroDetailsImpl.Data.DroidData.FriendsConnection>
        = FriendsConnection(customScalarAdapters)

    val primaryFunctionAdapter: ResponseAdapter<String?> =
        NullableResponseAdapter(stringResponseAdapter)

    fun fromResponse(reader: JsonReader, __typename: String?): HeroDetailsImpl.Data.DroidData {
      var __typename: String? = __typename
      var name: String? = null
      var friendsConnection: HeroDetailsImpl.Data.DroidData.FriendsConnection? = null
      var primaryFunction: String? = null
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
              UnexpectedNullValue("__typename")
          1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
          2 -> friendsConnection = friendsConnectionAdapter.fromResponse(reader) ?: throw
              UnexpectedNullValue("friendsConnection")
          3 -> primaryFunction = primaryFunctionAdapter.fromResponse(reader)
          else -> break
        }
      }
      return HeroDetailsImpl.Data.DroidData(
        __typename = __typename!!,
        name = name!!,
        friendsConnection = friendsConnection!!,
        primaryFunction = primaryFunction
      )
    }

    fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.DroidData) {
      writer.beginObject()
      writer.name("__typename")
      __typenameAdapter.toResponse(writer, value.__typename)
      writer.name("name")
      nameAdapter.toResponse(writer, value.name)
      writer.name("friendsConnection")
      friendsConnectionAdapter.toResponse(writer, value.friendsConnection)
      writer.name("primaryFunction")
      primaryFunctionAdapter.toResponse(writer, value.primaryFunction)
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
        ),
        ResponseField(
          type = ResponseField.Type.Named.Other("String"),
          fieldName = "primaryFunction",
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }

    class FriendsConnection(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<HeroDetailsImpl.Data.DroidData.FriendsConnection> {
      val totalCountAdapter: ResponseAdapter<Int?> = NullableResponseAdapter(intResponseAdapter)

      val edgesAdapter:
          ResponseAdapter<List<HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

      override fun fromResponse(reader: JsonReader):
          HeroDetailsImpl.Data.DroidData.FriendsConnection {
        var totalCount: Int? = null
        var edges: List<HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge?>? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> totalCount = totalCountAdapter.fromResponse(reader)
            1 -> edges = edgesAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return HeroDetailsImpl.Data.DroidData.FriendsConnection(
          totalCount = totalCount,
          edges = edges
        )
      }

      override fun toResponse(writer: JsonWriter,
          value: HeroDetailsImpl.Data.DroidData.FriendsConnection) {
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
      ) : ResponseAdapter<HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge> {
        val nodeAdapter:
            ResponseAdapter<HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge.Node?> =
            NullableResponseAdapter(Node(customScalarAdapters))

        override fun fromResponse(reader: JsonReader):
            HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge {
          var node: HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge.Node? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> node = nodeAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge(
            node = node
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge) {
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
        ) : ResponseAdapter<HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge.Node> {
          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          override fun fromResponse(reader: JsonReader):
              HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge.Node {
            var name: String? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                else -> break
              }
            }
            reader.endObject()
            return HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge.Node(
              name = name!!
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: HeroDetailsImpl.Data.DroidData.FriendsConnection.Edge.Node) {
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

  class HumanData(
    customScalarAdapters: CustomScalarAdapters
  ) {
    val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val friendsConnectionAdapter: ResponseAdapter<HeroDetailsImpl.Data.HumanData.FriendsConnection>
        = FriendsConnection(customScalarAdapters)

    fun fromResponse(reader: JsonReader, __typename: String?): HeroDetailsImpl.Data.HumanData {
      var __typename: String? = __typename
      var name: String? = null
      var friendsConnection: HeroDetailsImpl.Data.HumanData.FriendsConnection? = null
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
      return HeroDetailsImpl.Data.HumanData(
        __typename = __typename!!,
        name = name!!,
        friendsConnection = friendsConnection!!
      )
    }

    fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.HumanData) {
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
    ) : ResponseAdapter<HeroDetailsImpl.Data.HumanData.FriendsConnection> {
      val totalCountAdapter: ResponseAdapter<Int?> = NullableResponseAdapter(intResponseAdapter)

      val edgesAdapter:
          ResponseAdapter<List<HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

      override fun fromResponse(reader: JsonReader):
          HeroDetailsImpl.Data.HumanData.FriendsConnection {
        var totalCount: Int? = null
        var edges: List<HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge?>? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> totalCount = totalCountAdapter.fromResponse(reader)
            1 -> edges = edgesAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return HeroDetailsImpl.Data.HumanData.FriendsConnection(
          totalCount = totalCount,
          edges = edges
        )
      }

      override fun toResponse(writer: JsonWriter,
          value: HeroDetailsImpl.Data.HumanData.FriendsConnection) {
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
      ) : ResponseAdapter<HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge> {
        val nodeAdapter:
            ResponseAdapter<HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge.Node?> =
            NullableResponseAdapter(Node(customScalarAdapters))

        override fun fromResponse(reader: JsonReader):
            HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge {
          var node: HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge.Node? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> node = nodeAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge(
            node = node
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge) {
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
        ) : ResponseAdapter<HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge.Node> {
          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          override fun fromResponse(reader: JsonReader):
              HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge.Node {
            var name: String? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                else -> break
              }
            }
            reader.endObject()
            return HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge.Node(
              name = name!!
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: HeroDetailsImpl.Data.HumanData.FriendsConnection.Edge.Node) {
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

  class OtherData(
    customScalarAdapters: CustomScalarAdapters
  ) {
    val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val friendsConnectionAdapter: ResponseAdapter<HeroDetailsImpl.Data.OtherData.FriendsConnection>
        = FriendsConnection(customScalarAdapters)

    fun fromResponse(reader: JsonReader, __typename: String?): HeroDetailsImpl.Data.OtherData {
      var __typename: String? = __typename
      var name: String? = null
      var friendsConnection: HeroDetailsImpl.Data.OtherData.FriendsConnection? = null
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
      return HeroDetailsImpl.Data.OtherData(
        __typename = __typename!!,
        name = name!!,
        friendsConnection = friendsConnection!!
      )
    }

    fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.OtherData) {
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
    ) : ResponseAdapter<HeroDetailsImpl.Data.OtherData.FriendsConnection> {
      val totalCountAdapter: ResponseAdapter<Int?> = NullableResponseAdapter(intResponseAdapter)

      val edgesAdapter:
          ResponseAdapter<List<HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

      override fun fromResponse(reader: JsonReader):
          HeroDetailsImpl.Data.OtherData.FriendsConnection {
        var totalCount: Int? = null
        var edges: List<HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge?>? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> totalCount = totalCountAdapter.fromResponse(reader)
            1 -> edges = edgesAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return HeroDetailsImpl.Data.OtherData.FriendsConnection(
          totalCount = totalCount,
          edges = edges
        )
      }

      override fun toResponse(writer: JsonWriter,
          value: HeroDetailsImpl.Data.OtherData.FriendsConnection) {
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
      ) : ResponseAdapter<HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge> {
        val nodeAdapter:
            ResponseAdapter<HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge.Node?> =
            NullableResponseAdapter(Node(customScalarAdapters))

        override fun fromResponse(reader: JsonReader):
            HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge {
          var node: HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge.Node? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> node = nodeAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge(
            node = node
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge) {
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
        ) : ResponseAdapter<HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge.Node> {
          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          override fun fromResponse(reader: JsonReader):
              HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge.Node {
            var name: String? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                else -> break
              }
            }
            reader.endObject()
            return HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge.Node(
              name = name!!
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: HeroDetailsImpl.Data.OtherData.FriendsConnection.Edge.Node) {
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
}
