// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_in_fragment.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.fragment_in_fragment.fragment.StarshipFragmentImpl
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class StarshipFragmentImpl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<StarshipFragmentImpl.Data> {
  val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val idAdapter: ResponseAdapter<String> = stringResponseAdapter

  val nameAdapter: ResponseAdapter<String?> = NullableResponseAdapter(stringResponseAdapter)

  val pilotConnectionAdapter: ResponseAdapter<StarshipFragmentImpl.Data.PilotConnection?> =
      NullableResponseAdapter(PilotConnection(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): StarshipFragmentImpl.Data {
    var __typename: String? = null
    var id: String? = null
    var name: String? = null
    var pilotConnection: StarshipFragmentImpl.Data.PilotConnection? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("__typename")
        1 -> id = idAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("id")
        2 -> name = nameAdapter.fromResponse(reader)
        3 -> pilotConnection = pilotConnectionAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return StarshipFragmentImpl.Data(
      __typename = __typename!!,
      id = id!!,
      name = name,
      pilotConnection = pilotConnection
    )
  }

  override fun toResponse(writer: JsonWriter, value: StarshipFragmentImpl.Data) {
    __typenameAdapter.toResponse(writer, value.__typename)
    idAdapter.toResponse(writer, value.id)
    nameAdapter.toResponse(writer, value.name)
    pilotConnectionAdapter.toResponse(writer, value.pilotConnection)
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
        type = ResponseField.Type.Named.Other("String"),
        responseName = "name",
        fieldName = "name",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = emptyList(),
      ),
      ResponseField(
        type = ResponseField.Type.Named.Object("StarshipPilotsConnection"),
        responseName = "pilotConnection",
        fieldName = "pilotConnection",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet(null, PilotConnection.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class PilotConnection(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<StarshipFragmentImpl.Data.PilotConnection> {
    val edgesAdapter: ResponseAdapter<List<StarshipFragmentImpl.Data.PilotConnection.Edge?>?> =
        NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

    override fun fromResponse(reader: JsonReader): StarshipFragmentImpl.Data.PilotConnection {
      var edges: List<StarshipFragmentImpl.Data.PilotConnection.Edge?>? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> edges = edgesAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return StarshipFragmentImpl.Data.PilotConnection(
        edges = edges
      )
    }

    override fun toResponse(writer: JsonWriter, value: StarshipFragmentImpl.Data.PilotConnection) {
      edgesAdapter.toResponse(writer, value.edges)
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.List(ResponseField.Type.Named.Object("StarshipPilotsEdge")),
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
    ) : ResponseAdapter<StarshipFragmentImpl.Data.PilotConnection.Edge> {
      val nodeAdapter: ResponseAdapter<StarshipFragmentImpl.Data.PilotConnection.Edge.Node?> =
          NullableResponseAdapter(Node(customScalarAdapters))

      override fun fromResponse(reader: JsonReader):
          StarshipFragmentImpl.Data.PilotConnection.Edge {
        var node: StarshipFragmentImpl.Data.PilotConnection.Edge.Node? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> node = nodeAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return StarshipFragmentImpl.Data.PilotConnection.Edge(
          node = node
        )
      }

      override fun toResponse(writer: JsonWriter,
          value: StarshipFragmentImpl.Data.PilotConnection.Edge) {
        nodeAdapter.toResponse(writer, value.node)
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.Named.Object("Person"),
            responseName = "node",
            fieldName = "node",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet("Person", Node.PersonNode.RESPONSE_FIELDS),
              ResponseField.FieldSet(null, Node.OtherNode.RESPONSE_FIELDS),
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Node(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<StarshipFragmentImpl.Data.PilotConnection.Edge.Node> {
        val personNodeAdapter: PersonNode =
            com.example.fragment_in_fragment.fragment.adapter.StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.Node.PersonNode(customScalarAdapters)

        val otherNodeAdapter: OtherNode =
            com.example.fragment_in_fragment.fragment.adapter.StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.Node.OtherNode(customScalarAdapters)

        override fun fromResponse(reader: JsonReader):
            StarshipFragmentImpl.Data.PilotConnection.Edge.Node {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "Person" -> personNodeAdapter.fromResponse(reader, typename)
            else -> otherNodeAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter,
            value: StarshipFragmentImpl.Data.PilotConnection.Edge.Node) {
          when(value) {
            is StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode -> personNodeAdapter.toResponse(writer, value)
            is StarshipFragmentImpl.Data.PilotConnection.Edge.Node.OtherNode -> otherNodeAdapter.toResponse(writer, value)
          }
        }

        class PersonNode(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val nameAdapter: ResponseAdapter<String?> = NullableResponseAdapter(stringResponseAdapter)

          val homeworldAdapter:
              ResponseAdapter<StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld?>
              = NullableResponseAdapter(Homeworld(customScalarAdapters))

          fun fromResponse(reader: JsonReader, __typename: String?):
              StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode {
            var __typename: String? = __typename
            var name: String? = null
            var homeworld: StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                1 -> name = nameAdapter.fromResponse(reader)
                2 -> homeworld = homeworldAdapter.fromResponse(reader)
                else -> break
              }
            }
            return StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode(
              __typename = __typename!!,
              name = name,
              homeworld = homeworld
            )
          }

          fun toResponse(writer: JsonWriter,
              value: StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode) {
            __typenameAdapter.toResponse(writer, value.__typename)
            nameAdapter.toResponse(writer, value.name)
            homeworldAdapter.toResponse(writer, value.homeworld)
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
                type = ResponseField.Type.Named.Other("String"),
                responseName = "name",
                fieldName = "name",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = emptyList(),
              ),
              ResponseField(
                type = ResponseField.Type.Named.Object("Planet"),
                responseName = "homeworld",
                fieldName = "homeworld",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = listOf(
                  ResponseField.FieldSet("Planet", Homeworld.PlanetHomeworld.RESPONSE_FIELDS),
                  ResponseField.FieldSet(null, Homeworld.OtherHomeworld.RESPONSE_FIELDS),
                ),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }

          class Homeworld(
            customScalarAdapters: CustomScalarAdapters
          ) :
              ResponseAdapter<StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld>
              {
            val planetHomeworldAdapter: PlanetHomeworld =
                com.example.fragment_in_fragment.fragment.adapter.StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.Node.PersonNode.Homeworld.PlanetHomeworld(customScalarAdapters)

            val otherHomeworldAdapter: OtherHomeworld =
                com.example.fragment_in_fragment.fragment.adapter.StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.Node.PersonNode.Homeworld.OtherHomeworld(customScalarAdapters)

            override fun fromResponse(reader: JsonReader):
                StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld {
              reader.beginObject()
              check(reader.nextName() == "__typename")
              val typename = reader.nextString()

              return when(typename) {
                "Planet" -> planetHomeworldAdapter.fromResponse(reader, typename)
                else -> otherHomeworldAdapter.fromResponse(reader, typename)
              }
              .also { reader.endObject() }
            }

            override fun toResponse(writer: JsonWriter,
                value: StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld) {
              when(value) {
                is StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld.PlanetHomeworld -> planetHomeworldAdapter.toResponse(writer, value)
                is StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld.OtherHomeworld -> otherHomeworldAdapter.toResponse(writer, value)
              }
            }

            class PlanetHomeworld(
              customScalarAdapters: CustomScalarAdapters
            ) {
              val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

              val nameAdapter: ResponseAdapter<String?> =
                  NullableResponseAdapter(stringResponseAdapter)

              fun fromResponse(reader: JsonReader, __typename: String?):
                  StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld.PlanetHomeworld {
                var __typename: String? = __typename
                var name: String? = null
                while(true) {
                  when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                        UnexpectedNullValue("__typename")
                    1 -> name = nameAdapter.fromResponse(reader)
                    else -> break
                  }
                }
                return StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld.PlanetHomeworld(
                  __typename = __typename!!,
                  name = name
                )
              }

              fun toResponse(writer: JsonWriter,
                  value: StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld.PlanetHomeworld) {
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
                    type = ResponseField.Type.Named.Other("String"),
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

            class OtherHomeworld(
              customScalarAdapters: CustomScalarAdapters
            ) {
              val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

              fun fromResponse(reader: JsonReader, __typename: String?):
                  StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld.OtherHomeworld {
                var __typename: String? = __typename
                while(true) {
                  when (reader.selectName(RESPONSE_NAMES)) {
                    0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                        UnexpectedNullValue("__typename")
                    else -> break
                  }
                }
                return StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld.OtherHomeworld(
                  __typename = __typename!!
                )
              }

              fun toResponse(writer: JsonWriter,
                  value: StarshipFragmentImpl.Data.PilotConnection.Edge.Node.PersonNode.Homeworld.OtherHomeworld) {
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

        class OtherNode(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              StarshipFragmentImpl.Data.PilotConnection.Edge.Node.OtherNode {
            var __typename: String? = __typename
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                else -> break
              }
            }
            return StarshipFragmentImpl.Data.PilotConnection.Edge.Node.OtherNode(
              __typename = __typename!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: StarshipFragmentImpl.Data.PilotConnection.Edge.Node.OtherNode) {
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
  }
}
