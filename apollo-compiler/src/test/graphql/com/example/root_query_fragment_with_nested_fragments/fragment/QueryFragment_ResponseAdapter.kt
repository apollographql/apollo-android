// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_fragment_with_nested_fragments.fragment

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object QueryFragment_ResponseAdapter : ResponseAdapter<QueryFragment.QueryFragmentImpl> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField.forString("__typename", "__typename", null, false, null),
    ResponseField.forObject("hero", "hero", null, true, null),
    ResponseField.forObject("droid", "droid", mapOf<String, Any>(
      "id" to "1"), true, null),
    ResponseField.forObject("human", "human", mapOf<String, Any>(
      "id" to "1"), true, null)
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?):
      QueryFragment.QueryFragmentImpl {
    return reader.run {
      var __typename: String? = __typename
      var hero: QueryFragment.Hero1? = null
      var droid: QueryFragment.Droid1? = null
      var human: QueryFragment.Human1? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> __typename = readString(RESPONSE_FIELDS[0])
          1 -> hero = readObject<QueryFragment.Hero1>(RESPONSE_FIELDS[1]) { reader ->
            Hero1_ResponseAdapter.fromResponse(reader)
          }
          2 -> droid = readObject<QueryFragment.Droid1>(RESPONSE_FIELDS[2]) { reader ->
            Droid1_ResponseAdapter.fromResponse(reader)
          }
          3 -> human = readObject<QueryFragment.Human1>(RESPONSE_FIELDS[3]) { reader ->
            Human1_ResponseAdapter.fromResponse(reader)
          }
          else -> break
        }
      }
      QueryFragment.QueryFragmentImpl(
        __typename = __typename!!,
        hero = hero,
        droid = droid,
        human = human
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: QueryFragment.QueryFragmentImpl) {
    writer.writeString(RESPONSE_FIELDS[0], value.__typename)
    if(value.hero == null) {
      writer.writeObject(RESPONSE_FIELDS[1], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[1]) { writer ->
        Hero1_ResponseAdapter.toResponse(writer, value.hero)
      }
    }
    if(value.droid == null) {
      writer.writeObject(RESPONSE_FIELDS[2], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[2]) { writer ->
        Droid1_ResponseAdapter.toResponse(writer, value.droid)
      }
    }
    if(value.human == null) {
      writer.writeObject(RESPONSE_FIELDS[3], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[3]) { writer ->
        Human1_ResponseAdapter.toResponse(writer, value.human)
      }
    }
  }

  object Hero1_ResponseAdapter : ResponseAdapter<QueryFragment.Hero1> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, false, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): QueryFragment.Hero1 {
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
        QueryFragment.Hero1(
          __typename = __typename!!,
          name = name!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: QueryFragment.Hero1) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
    }
  }

  object Droid1_ResponseAdapter : ResponseAdapter<QueryFragment.Droid1> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, false, null),
      ResponseField.forString("primaryFunction", "primaryFunction", null, true, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): QueryFragment.Droid1 {
      return reader.run {
        var __typename: String? = __typename
        var name: String? = null
        var primaryFunction: String? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            2 -> primaryFunction = readString(RESPONSE_FIELDS[2])
            else -> break
          }
        }
        QueryFragment.Droid1(
          __typename = __typename!!,
          name = name!!,
          primaryFunction = primaryFunction
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: QueryFragment.Droid1) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
      writer.writeString(RESPONSE_FIELDS[2], value.primaryFunction)
    }
  }

  object Human1_ResponseAdapter : ResponseAdapter<QueryFragment.Human1> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, false, null),
      ResponseField.forString("homePlanet", "homePlanet", null, true, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): QueryFragment.Human1 {
      return reader.run {
        var __typename: String? = __typename
        var name: String? = null
        var homePlanet: String? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            2 -> homePlanet = readString(RESPONSE_FIELDS[2])
            else -> break
          }
        }
        QueryFragment.Human1(
          __typename = __typename!!,
          name = name!!,
          homePlanet = homePlanet
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: QueryFragment.Human1) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
      writer.writeString(RESPONSE_FIELDS[2], value.homePlanet)
    }
  }
}
