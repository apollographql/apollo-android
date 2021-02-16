// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_details.adapter

import com.apollographql.apollo.api.ResponseAdapterCache
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.intResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.example.hero_details.HeroDetails
import com.example.hero_details.type.Hero_type
import com.example.hero_details.type.Hero_type_ResponseAdapter
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HeroDetails_ResponseAdapter(
  responseAdapterCache: ResponseAdapterCache
) : ResponseAdapter<HeroDetails.Data> {
  private val nullableHeroAdapter: ResponseAdapter<HeroDetails.Data.Hero?> =
      NullableResponseAdapter(Hero(responseAdapterCache))

  override fun fromResponse(reader: JsonReader): HeroDetails.Data {
    var hero: HeroDetails.Data.Hero? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> hero = nullableHeroAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return HeroDetails.Data(
      hero = hero
    )
  }

  override fun toResponse(writer: JsonWriter, value: HeroDetails.Data) {
    writer.beginObject()
    writer.name("hero")
    nullableHeroAdapter.toResponse(writer, value.hero)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        fieldName = "hero",
        fieldSets = listOf(
          ResponseField.FieldSet(null, Hero.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    responseAdapterCache: ResponseAdapterCache
  ) : ResponseAdapter<HeroDetails.Data.Hero> {
    private val hero_typeAdapter: ResponseAdapter<Hero_type> = Hero_type_ResponseAdapter

    private val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

    private val friendsConnectionAdapter: ResponseAdapter<HeroDetails.Data.Hero.FriendsConnection> =
        FriendsConnection(responseAdapterCache)

    override fun fromResponse(reader: JsonReader): HeroDetails.Data.Hero {
      var type: Hero_type? = null
      var name: String? = null
      var friendsConnection: HeroDetails.Data.Hero.FriendsConnection? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> type = hero_typeAdapter.fromResponse(reader)
          1 -> name = stringAdapter.fromResponse(reader)
          2 -> friendsConnection = friendsConnectionAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return HeroDetails.Data.Hero(
        type = type!!,
        name = name!!,
        friendsConnection = friendsConnection!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: HeroDetails.Data.Hero) {
      writer.beginObject()
      writer.name("type")
      hero_typeAdapter.toResponse(writer, value.type)
      writer.name("name")
      stringAdapter.toResponse(writer, value.name)
      writer.name("friendsConnection")
      friendsConnectionAdapter.toResponse(writer, value.friendsConnection)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("hero_type")),
          fieldName = "type",
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
      responseAdapterCache: ResponseAdapterCache
    ) : ResponseAdapter<HeroDetails.Data.Hero.FriendsConnection> {
      private val nullableIntAdapter: ResponseAdapter<Int?> =
          NullableResponseAdapter(intResponseAdapter)

      private val nullableListOfNullableEdgesAdapter:
          ResponseAdapter<List<HeroDetails.Data.Hero.FriendsConnection.Edges?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edges(responseAdapterCache))))

      override fun fromResponse(reader: JsonReader): HeroDetails.Data.Hero.FriendsConnection {
        var totalCount: Int? = null
        var edges: List<HeroDetails.Data.Hero.FriendsConnection.Edges?>? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> totalCount = nullableIntAdapter.fromResponse(reader)
            1 -> edges = nullableListOfNullableEdgesAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return HeroDetails.Data.Hero.FriendsConnection(
          totalCount = totalCount,
          edges = edges
        )
      }

      override fun toResponse(writer: JsonWriter, value: HeroDetails.Data.Hero.FriendsConnection) {
        writer.beginObject()
        writer.name("totalCount")
        nullableIntAdapter.toResponse(writer, value.totalCount)
        writer.name("edges")
        nullableListOfNullableEdgesAdapter.toResponse(writer, value.edges)
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
              ResponseField.FieldSet(null, Edges.RESPONSE_FIELDS)
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Edges(
        responseAdapterCache: ResponseAdapterCache
      ) : ResponseAdapter<HeroDetails.Data.Hero.FriendsConnection.Edges> {
        private val nullableNodeAdapter:
            ResponseAdapter<HeroDetails.Data.Hero.FriendsConnection.Edges.Node?> =
            NullableResponseAdapter(Node(responseAdapterCache))

        override fun fromResponse(reader: JsonReader):
            HeroDetails.Data.Hero.FriendsConnection.Edges {
          var node: HeroDetails.Data.Hero.FriendsConnection.Edges.Node? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> node = nullableNodeAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return HeroDetails.Data.Hero.FriendsConnection.Edges(
            node = node
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: HeroDetails.Data.Hero.FriendsConnection.Edges) {
          writer.beginObject()
          writer.name("node")
          nullableNodeAdapter.toResponse(writer, value.node)
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
          responseAdapterCache: ResponseAdapterCache
        ) : ResponseAdapter<HeroDetails.Data.Hero.FriendsConnection.Edges.Node> {
          private val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

          override fun fromResponse(reader: JsonReader):
              HeroDetails.Data.Hero.FriendsConnection.Edges.Node {
            var name: String? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> name = stringAdapter.fromResponse(reader)
                else -> break
              }
            }
            reader.endObject()
            return HeroDetails.Data.Hero.FriendsConnection.Edges.Node(
              name = name!!
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: HeroDetails.Data.Hero.FriendsConnection.Edges.Node) {
            writer.beginObject()
            writer.name("name")
            stringAdapter.toResponse(writer, value.name)
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
