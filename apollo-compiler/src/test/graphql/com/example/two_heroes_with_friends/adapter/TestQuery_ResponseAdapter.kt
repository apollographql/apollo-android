// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.two_heroes_with_friends.adapter

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
import com.example.two_heroes_with_friends.TestQuery
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
  val r2Adapter: ResponseAdapter<TestQuery.Data.R2?> =
      NullableResponseAdapter(R2(customScalarAdapters))

  val lukeAdapter: ResponseAdapter<TestQuery.Data.Luke?> =
      NullableResponseAdapter(Luke(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var r2: TestQuery.Data.R2? = null
    var luke: TestQuery.Data.Luke? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> r2 = r2Adapter.fromResponse(reader)
        1 -> luke = lukeAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      r2 = r2,
      luke = luke
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("r2")
    r2Adapter.toResponse(writer, value.r2)
    writer.name("luke")
    lukeAdapter.toResponse(writer, value.luke)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        fieldName = "hero",
        responseName = "r2",
        fieldSets = listOf(
          ResponseField.FieldSet(null, R2.RESPONSE_FIELDS)
        ),
      ),
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        fieldName = "hero",
        responseName = "luke",
        arguments = mapOf<String, Any?>(
          "episode" to "EMPIRE"),
        fieldSets = listOf(
          ResponseField.FieldSet(null, Luke.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class R2(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.R2> {
    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val friendsConnectionAdapter: ResponseAdapter<TestQuery.Data.R2.FriendsConnection> =
        FriendsConnection(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.R2 {
      var name: String? = null
      var friendsConnection: TestQuery.Data.R2.FriendsConnection? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
          1 -> friendsConnection = friendsConnectionAdapter.fromResponse(reader) ?: throw
              UnexpectedNullValue("friendsConnection")
          else -> break
        }
      }
      reader.endObject()
      return TestQuery.Data.R2(
        name = name!!,
        friendsConnection = friendsConnection!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.R2) {
      writer.beginObject()
      writer.name("name")
      nameAdapter.toResponse(writer, value.name)
      writer.name("friendsConnection")
      friendsConnectionAdapter.toResponse(writer, value.friendsConnection)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
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
    ) : ResponseAdapter<TestQuery.Data.R2.FriendsConnection> {
      val totalCountAdapter: ResponseAdapter<Int?> = NullableResponseAdapter(intResponseAdapter)

      val edgesAdapter: ResponseAdapter<List<TestQuery.Data.R2.FriendsConnection.Edge?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

      override fun fromResponse(reader: JsonReader): TestQuery.Data.R2.FriendsConnection {
        var totalCount: Int? = null
        var edges: List<TestQuery.Data.R2.FriendsConnection.Edge?>? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> totalCount = totalCountAdapter.fromResponse(reader)
            1 -> edges = edgesAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.R2.FriendsConnection(
          totalCount = totalCount,
          edges = edges
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.R2.FriendsConnection) {
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
      ) : ResponseAdapter<TestQuery.Data.R2.FriendsConnection.Edge> {
        val nodeAdapter: ResponseAdapter<TestQuery.Data.R2.FriendsConnection.Edge.Node?> =
            NullableResponseAdapter(Node(customScalarAdapters))

        override fun fromResponse(reader: JsonReader): TestQuery.Data.R2.FriendsConnection.Edge {
          var node: TestQuery.Data.R2.FriendsConnection.Edge.Node? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> node = nodeAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return TestQuery.Data.R2.FriendsConnection.Edge(
            node = node
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: TestQuery.Data.R2.FriendsConnection.Edge) {
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
        ) : ResponseAdapter<TestQuery.Data.R2.FriendsConnection.Edge.Node> {
          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          override fun fromResponse(reader: JsonReader):
              TestQuery.Data.R2.FriendsConnection.Edge.Node {
            var name: String? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                else -> break
              }
            }
            reader.endObject()
            return TestQuery.Data.R2.FriendsConnection.Edge.Node(
              name = name!!
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.R2.FriendsConnection.Edge.Node) {
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

  class Luke(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Luke> {
    val idAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val friendsConnectionAdapter: ResponseAdapter<TestQuery.Data.Luke.FriendsConnection> =
        FriendsConnection(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Luke {
      var id: String? = null
      var name: String? = null
      var friendsConnection: TestQuery.Data.Luke.FriendsConnection? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> id = idAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("id")
          1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
          2 -> friendsConnection = friendsConnectionAdapter.fromResponse(reader) ?: throw
              UnexpectedNullValue("friendsConnection")
          else -> break
        }
      }
      reader.endObject()
      return TestQuery.Data.Luke(
        id = id!!,
        name = name!!,
        friendsConnection = friendsConnection!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Luke) {
      writer.beginObject()
      writer.name("id")
      idAdapter.toResponse(writer, value.id)
      writer.name("name")
      nameAdapter.toResponse(writer, value.name)
      writer.name("friendsConnection")
      friendsConnectionAdapter.toResponse(writer, value.friendsConnection)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          fieldName = "id",
        ),
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
    ) : ResponseAdapter<TestQuery.Data.Luke.FriendsConnection> {
      val totalCountAdapter: ResponseAdapter<Int?> = NullableResponseAdapter(intResponseAdapter)

      val edgesAdapter: ResponseAdapter<List<TestQuery.Data.Luke.FriendsConnection.Edge?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

      override fun fromResponse(reader: JsonReader): TestQuery.Data.Luke.FriendsConnection {
        var totalCount: Int? = null
        var edges: List<TestQuery.Data.Luke.FriendsConnection.Edge?>? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> totalCount = totalCountAdapter.fromResponse(reader)
            1 -> edges = edgesAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Luke.FriendsConnection(
          totalCount = totalCount,
          edges = edges
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Luke.FriendsConnection) {
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
      ) : ResponseAdapter<TestQuery.Data.Luke.FriendsConnection.Edge> {
        val nodeAdapter: ResponseAdapter<TestQuery.Data.Luke.FriendsConnection.Edge.Node?> =
            NullableResponseAdapter(Node(customScalarAdapters))

        override fun fromResponse(reader: JsonReader): TestQuery.Data.Luke.FriendsConnection.Edge {
          var node: TestQuery.Data.Luke.FriendsConnection.Edge.Node? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> node = nodeAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return TestQuery.Data.Luke.FriendsConnection.Edge(
            node = node
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: TestQuery.Data.Luke.FriendsConnection.Edge) {
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
        ) : ResponseAdapter<TestQuery.Data.Luke.FriendsConnection.Edge.Node> {
          val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

          override fun fromResponse(reader: JsonReader):
              TestQuery.Data.Luke.FriendsConnection.Edge.Node {
            var name: String? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                else -> break
              }
            }
            reader.endObject()
            return TestQuery.Data.Luke.FriendsConnection.Edge.Node(
              name = name!!
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Luke.FriendsConnection.Edge.Node) {
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
