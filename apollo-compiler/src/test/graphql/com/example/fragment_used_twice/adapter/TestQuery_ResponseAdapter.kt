// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.fragment_used_twice.TestQuery
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.Named.Object("Character"),
      responseName = "hero",
      fieldName = "hero",
      arguments = emptyMap(),
      conditions = emptyList(),
      fieldSets = listOf(
        ResponseField.FieldSet("Droid", Hero.CharacterHero.RESPONSE_FIELDS),
        ResponseField.FieldSet("Human", Hero.CharacterHumanHero.RESPONSE_FIELDS),
        ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
      ),
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return reader.run {
      var hero: TestQuery.Data.Hero? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> hero = readObject<TestQuery.Data.Hero>(RESPONSE_FIELDS[0]) { reader ->
            Hero.fromResponse(reader)
          }
          else -> break
        }
      }
      TestQuery.Data(
        hero = hero
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: TestQuery.Data) {
    if(value.hero == null) {
      writer.writeObject(RESPONSE_FIELDS[0], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
        Hero.toResponse(writer, value.hero)
      }
    }
  }

  object Hero : ResponseAdapter<TestQuery.Data.Hero> {
    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data.Hero {
      val typename = __typename ?: reader.readString(ResponseField.Typename)
      return when(typename) {
        "Droid" -> CharacterHero.fromResponse(reader, typename)
        "Human" -> CharacterHumanHero.fromResponse(reader, typename)
        else -> OtherHero.fromResponse(reader, typename)
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.CharacterHero -> CharacterHero.toResponse(writer, value)
        is TestQuery.Data.Hero.CharacterHumanHero -> CharacterHumanHero.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> OtherHero.toResponse(writer, value)
      }
    }

    object CharacterHero : ResponseAdapter<TestQuery.Data.Hero.CharacterHero> {
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
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
          responseName = "birthDate",
          fieldName = "birthDate",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Hero.CharacterHero {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var birthDate: Any? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> birthDate = readCustomScalar<Any>(RESPONSE_FIELDS[2])
              else -> break
            }
          }
          TestQuery.Data.Hero.CharacterHero(
            __typename = __typename!!,
            name = name!!,
            birthDate = birthDate!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero.CharacterHero) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.name)
        writer.writeCustom(RESPONSE_FIELDS[2], value.birthDate)
      }
    }

    object CharacterHumanHero : ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero> {
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
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
          responseName = "birthDate",
          fieldName = "birthDate",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Hero.CharacterHumanHero {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var birthDate: Any? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> birthDate = readCustomScalar<Any>(RESPONSE_FIELDS[2])
              else -> break
            }
          }
          TestQuery.Data.Hero.CharacterHumanHero(
            __typename = __typename!!,
            name = name!!,
            birthDate = birthDate!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter,
          value: TestQuery.Data.Hero.CharacterHumanHero) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.name)
        writer.writeCustom(RESPONSE_FIELDS[2], value.birthDate)
      }
    }

    object OtherHero : ResponseAdapter<TestQuery.Data.Hero.OtherHero> {
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

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Hero.OtherHero {
        return reader.run {
          var __typename: String? = __typename
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              else -> break
            }
          }
          TestQuery.Data.Hero.OtherHero(
            __typename = __typename!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero.OtherHero) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      }
    }
  }
}
