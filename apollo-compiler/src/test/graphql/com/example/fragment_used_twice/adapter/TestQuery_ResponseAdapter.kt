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
import com.example.fragment_used_twice.type.CustomScalar
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField.forObject("hero", "hero", null, true, null)
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return Data.fromResponse(reader, __typename)
  }

  override fun toResponse(writer: ResponseWriter, value: TestQuery.Data) {
    Data.toResponse(writer, value)
  }

  object Data : ResponseAdapter<TestQuery.Data> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forObject("hero", "hero", null, true, null)
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
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data.Hero {
        val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
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
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null)
        )

        override fun fromResponse(reader: ResponseReader, __typename: String?):
            TestQuery.Data.Hero.CharacterHero {
          val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
          return when(typename) {
            "Droid" -> CharacterCharacterHero.fromResponse(reader, typename)
            else -> OtherCharacterHero.fromResponse(reader, typename)
          }
        }

        override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero.CharacterHero) {
          when(value) {
            is TestQuery.Data.Hero.CharacterHero.CharacterCharacterHero -> CharacterCharacterHero.toResponse(writer, value)
            is TestQuery.Data.Hero.CharacterHero.OtherCharacterHero -> OtherCharacterHero.toResponse(writer, value)
          }
        }

        object CharacterCharacterHero :
            ResponseAdapter<TestQuery.Data.Hero.CharacterHero.CharacterCharacterHero> {
          private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField.forString("__typename", "__typename", null, false, null),
            ResponseField.forString("name", "name", null, false, null),
            ResponseField.forCustomScalar("birthDate", "birthDate", null, false, CustomScalar.Date, null)
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestQuery.Data.Hero.CharacterHero.CharacterCharacterHero {
            return reader.run {
              var __typename: String? = __typename
              var name: String? = null
              var birthDate: Any? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> __typename = readString(RESPONSE_FIELDS[0])
                  1 -> name = readString(RESPONSE_FIELDS[1])
                  2 -> birthDate = readCustomScalar<Any>(RESPONSE_FIELDS[2] as ResponseField.CustomScalarField)
                  else -> break
                }
              }
              TestQuery.Data.Hero.CharacterHero.CharacterCharacterHero(
                __typename = __typename!!,
                name = name!!,
                birthDate = birthDate!!
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestQuery.Data.Hero.CharacterHero.CharacterCharacterHero) {
            writer.writeString(RESPONSE_FIELDS[0], value.__typename)
            writer.writeString(RESPONSE_FIELDS[1], value.name)
            writer.writeCustom(RESPONSE_FIELDS[2] as ResponseField.CustomScalarField, value.birthDate)
          }
        }

        object OtherCharacterHero :
            ResponseAdapter<TestQuery.Data.Hero.CharacterHero.OtherCharacterHero> {
          private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField.forString("__typename", "__typename", null, false, null),
            ResponseField.forString("name", "name", null, false, null)
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestQuery.Data.Hero.CharacterHero.OtherCharacterHero {
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
              TestQuery.Data.Hero.CharacterHero.OtherCharacterHero(
                __typename = __typename!!,
                name = name!!
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestQuery.Data.Hero.CharacterHero.OtherCharacterHero) {
            writer.writeString(RESPONSE_FIELDS[0], value.__typename)
            writer.writeString(RESPONSE_FIELDS[1], value.name)
          }
        }
      }

      object CharacterHumanHero : ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero> {
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null)
        )

        override fun fromResponse(reader: ResponseReader, __typename: String?):
            TestQuery.Data.Hero.CharacterHumanHero {
          val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
          return when(typename) {
            "Human" -> CharacterCharacterHumanHero.fromResponse(reader, typename)
            else -> OtherCharacterHumanHero.fromResponse(reader, typename)
          }
        }

        override fun toResponse(writer: ResponseWriter,
            value: TestQuery.Data.Hero.CharacterHumanHero) {
          when(value) {
            is TestQuery.Data.Hero.CharacterHumanHero.CharacterCharacterHumanHero -> CharacterCharacterHumanHero.toResponse(writer, value)
            is TestQuery.Data.Hero.CharacterHumanHero.OtherCharacterHumanHero -> OtherCharacterHumanHero.toResponse(writer, value)
          }
        }

        object CharacterCharacterHumanHero :
            ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero.CharacterCharacterHumanHero> {
          private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField.forString("__typename", "__typename", null, false, null),
            ResponseField.forString("name", "name", null, false, null),
            ResponseField.forCustomScalar("birthDate", "birthDate", null, false, CustomScalar.Date, null)
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestQuery.Data.Hero.CharacterHumanHero.CharacterCharacterHumanHero {
            return reader.run {
              var __typename: String? = __typename
              var name: String? = null
              var birthDate: Any? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> __typename = readString(RESPONSE_FIELDS[0])
                  1 -> name = readString(RESPONSE_FIELDS[1])
                  2 -> birthDate = readCustomScalar<Any>(RESPONSE_FIELDS[2] as ResponseField.CustomScalarField)
                  else -> break
                }
              }
              TestQuery.Data.Hero.CharacterHumanHero.CharacterCharacterHumanHero(
                __typename = __typename!!,
                name = name!!,
                birthDate = birthDate!!
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestQuery.Data.Hero.CharacterHumanHero.CharacterCharacterHumanHero) {
            writer.writeString(RESPONSE_FIELDS[0], value.__typename)
            writer.writeString(RESPONSE_FIELDS[1], value.name)
            writer.writeCustom(RESPONSE_FIELDS[2] as ResponseField.CustomScalarField, value.birthDate)
          }
        }

        object OtherCharacterHumanHero :
            ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero.OtherCharacterHumanHero> {
          private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField.forString("__typename", "__typename", null, false, null),
            ResponseField.forString("name", "name", null, false, null)
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestQuery.Data.Hero.CharacterHumanHero.OtherCharacterHumanHero {
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
              TestQuery.Data.Hero.CharacterHumanHero.OtherCharacterHumanHero(
                __typename = __typename!!,
                name = name!!
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestQuery.Data.Hero.CharacterHumanHero.OtherCharacterHumanHero) {
            writer.writeString(RESPONSE_FIELDS[0], value.__typename)
            writer.writeString(RESPONSE_FIELDS[1], value.name)
          }
        }
      }

      object OtherHero : ResponseAdapter<TestQuery.Data.Hero.OtherHero> {
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null)
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
}
