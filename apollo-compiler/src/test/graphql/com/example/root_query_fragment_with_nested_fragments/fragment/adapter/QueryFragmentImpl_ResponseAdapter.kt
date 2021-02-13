// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_fragment_with_nested_fragments.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.root_query_fragment_with_nested_fragments.fragment.QueryFragmentImpl
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class QueryFragmentImpl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<QueryFragmentImpl.Data> {
  val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val heroAdapter: ResponseAdapter<QueryFragmentImpl.Data.Hero?> =
      NullableResponseAdapter(Hero(customScalarAdapters))

  val droidAdapter: ResponseAdapter<QueryFragmentImpl.Data.Droid?> =
      NullableResponseAdapter(Droid(customScalarAdapters))

  val humanAdapter: ResponseAdapter<QueryFragmentImpl.Data.Human?> =
      NullableResponseAdapter(Human(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): QueryFragmentImpl.Data {
    var __typename: String? = null
    var hero: QueryFragmentImpl.Data.Hero? = null
    var droid: QueryFragmentImpl.Data.Droid? = null
    var human: QueryFragmentImpl.Data.Human? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("__typename")
        1 -> hero = heroAdapter.fromResponse(reader)
        2 -> droid = droidAdapter.fromResponse(reader)
        3 -> human = humanAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return QueryFragmentImpl.Data(
      __typename = __typename!!,
      hero = hero,
      droid = droid,
      human = human
    )
  }

  override fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data) {
    __typenameAdapter.toResponse(writer, value.__typename)
    heroAdapter.toResponse(writer, value.hero)
    droidAdapter.toResponse(writer, value.droid)
    humanAdapter.toResponse(writer, value.human)
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
        type = ResponseField.Type.Named.Object("Character"),
        responseName = "hero",
        fieldName = "hero",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet("Droid", Hero.CharacterHero.RESPONSE_FIELDS),
          ResponseField.FieldSet("Human", Hero.CharacterHero.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
        ),
      ),
      ResponseField(
        type = ResponseField.Type.Named.Object("Droid"),
        responseName = "droid",
        fieldName = "droid",
        arguments = mapOf<String, Any?>(
          "id" to 1),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet("Droid", Droid.DroidDroid.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Droid.OtherDroid.RESPONSE_FIELDS),
        ),
      ),
      ResponseField(
        type = ResponseField.Type.Named.Object("Human"),
        responseName = "human",
        fieldName = "human",
        arguments = mapOf<String, Any?>(
          "id" to 1),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet("Human", Human.HumanHuman.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Human.OtherHuman.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<QueryFragmentImpl.Data.Hero> {
    val characterHeroAdapter: CharacterHero =
        com.example.root_query_fragment_with_nested_fragments.fragment.adapter.QueryFragmentImpl_ResponseAdapter.Hero.CharacterHero(customScalarAdapters)

    val otherHeroAdapter: OtherHero =
        com.example.root_query_fragment_with_nested_fragments.fragment.adapter.QueryFragmentImpl_ResponseAdapter.Hero.OtherHero(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): QueryFragmentImpl.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Droid" -> characterHeroAdapter.fromResponse(reader, typename)
        "Human" -> characterHeroAdapter.fromResponse(reader, typename)
        else -> otherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Hero) {
      when(value) {
        is QueryFragmentImpl.Data.Hero.CharacterHero -> characterHeroAdapter.toResponse(writer, value)
        is QueryFragmentImpl.Data.Hero.OtherHero -> otherHeroAdapter.toResponse(writer, value)
      }
    }

    class CharacterHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          QueryFragmentImpl.Data.Hero.CharacterHero {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            else -> break
          }
        }
        return QueryFragmentImpl.Data.Hero.CharacterHero(
          __typename = __typename!!,
          name = name!!
        )
      }

      fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Hero.CharacterHero) {
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
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
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

    class OtherHero(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          QueryFragmentImpl.Data.Hero.OtherHero {
        var __typename: String? = __typename
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            else -> break
          }
        }
        return QueryFragmentImpl.Data.Hero.OtherHero(
          __typename = __typename!!
        )
      }

      fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Hero.OtherHero) {
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

  class Droid(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<QueryFragmentImpl.Data.Droid> {
    val droidDroidAdapter: DroidDroid =
        com.example.root_query_fragment_with_nested_fragments.fragment.adapter.QueryFragmentImpl_ResponseAdapter.Droid.DroidDroid(customScalarAdapters)

    val otherDroidAdapter: OtherDroid =
        com.example.root_query_fragment_with_nested_fragments.fragment.adapter.QueryFragmentImpl_ResponseAdapter.Droid.OtherDroid(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): QueryFragmentImpl.Data.Droid {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Droid" -> droidDroidAdapter.fromResponse(reader, typename)
        else -> otherDroidAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Droid) {
      when(value) {
        is QueryFragmentImpl.Data.Droid.DroidDroid -> droidDroidAdapter.toResponse(writer, value)
        is QueryFragmentImpl.Data.Droid.OtherDroid -> otherDroidAdapter.toResponse(writer, value)
      }
    }

    class DroidDroid(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val primaryFunctionAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(stringResponseAdapter)

      fun fromResponse(reader: JsonReader, __typename: String?):
          QueryFragmentImpl.Data.Droid.DroidDroid {
        var __typename: String? = __typename
        var name: String? = null
        var primaryFunction: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> primaryFunction = primaryFunctionAdapter.fromResponse(reader)
            else -> break
          }
        }
        return QueryFragmentImpl.Data.Droid.DroidDroid(
          __typename = __typename!!,
          name = name!!,
          primaryFunction = primaryFunction
        )
      }

      fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Droid.DroidDroid) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        primaryFunctionAdapter.toResponse(writer, value.primaryFunction)
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
            responseName = "name",
            fieldName = "name",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.Named.Other("String"),
            responseName = "primaryFunction",
            fieldName = "primaryFunction",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class OtherDroid(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          QueryFragmentImpl.Data.Droid.OtherDroid {
        var __typename: String? = __typename
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            else -> break
          }
        }
        return QueryFragmentImpl.Data.Droid.OtherDroid(
          __typename = __typename!!
        )
      }

      fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Droid.OtherDroid) {
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

  class Human(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<QueryFragmentImpl.Data.Human> {
    val humanHumanAdapter: HumanHuman =
        com.example.root_query_fragment_with_nested_fragments.fragment.adapter.QueryFragmentImpl_ResponseAdapter.Human.HumanHuman(customScalarAdapters)

    val otherHumanAdapter: OtherHuman =
        com.example.root_query_fragment_with_nested_fragments.fragment.adapter.QueryFragmentImpl_ResponseAdapter.Human.OtherHuman(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): QueryFragmentImpl.Data.Human {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> humanHumanAdapter.fromResponse(reader, typename)
        else -> otherHumanAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Human) {
      when(value) {
        is QueryFragmentImpl.Data.Human.HumanHuman -> humanHumanAdapter.toResponse(writer, value)
        is QueryFragmentImpl.Data.Human.OtherHuman -> otherHumanAdapter.toResponse(writer, value)
      }
    }

    class HumanHuman(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val homePlanetAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(stringResponseAdapter)

      fun fromResponse(reader: JsonReader, __typename: String?):
          QueryFragmentImpl.Data.Human.HumanHuman {
        var __typename: String? = __typename
        var name: String? = null
        var homePlanet: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> homePlanet = homePlanetAdapter.fromResponse(reader)
            else -> break
          }
        }
        return QueryFragmentImpl.Data.Human.HumanHuman(
          __typename = __typename!!,
          name = name!!,
          homePlanet = homePlanet
        )
      }

      fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Human.HumanHuman) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        homePlanetAdapter.toResponse(writer, value.homePlanet)
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
            responseName = "name",
            fieldName = "name",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.Named.Other("String"),
            responseName = "homePlanet",
            fieldName = "homePlanet",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class OtherHuman(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          QueryFragmentImpl.Data.Human.OtherHuman {
        var __typename: String? = __typename
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            else -> break
          }
        }
        return QueryFragmentImpl.Data.Human.OtherHuman(
          __typename = __typename!!
        )
      }

      fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Human.OtherHuman) {
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
