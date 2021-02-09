// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_frgament_intersection.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.inline_frgament_intersection.TestOperation
import com.example.inline_frgament_intersection.type.Race
import kotlin.Array
import kotlin.Boolean
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestOperation_ResponseAdapter : ResponseAdapter<TestOperation.Data> {
  val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("Anything")),
      responseName = "random",
      fieldName = "random",
      arguments = emptyMap(),
      conditions = emptyList(),
      fieldSets = listOf(
        ResponseField.FieldSet("Human", Random.BeingHumanRandom.RESPONSE_FIELDS),
        ResponseField.FieldSet("Wookie", Random.BeingWookieRandom.RESPONSE_FIELDS),
        ResponseField.FieldSet(null, Random.OtherRandom.RESPONSE_FIELDS),
      ),
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestOperation.Data {
    return reader.run {
      var random: TestOperation.Data.Random? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> random = readObject<TestOperation.Data.Random>(RESPONSE_FIELDS[0]) { reader ->
            Random.fromResponse(reader)
          }
          else -> break
        }
      }
      TestOperation.Data(
        random = random!!
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: TestOperation.Data) {
    writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
      Random.toResponse(writer, value.random)
    }
  }

  object Random : ResponseAdapter<TestOperation.Data.Random> {
    override fun fromResponse(reader: ResponseReader, __typename: String?):
        TestOperation.Data.Random {
      val typename = __typename ?: reader.readString(ResponseField.Typename)
      return when(typename) {
        "Human" -> BeingHumanRandom.fromResponse(reader, typename)
        "Wookie" -> BeingWookieRandom.fromResponse(reader, typename)
        else -> OtherRandom.fromResponse(reader, typename)
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestOperation.Data.Random) {
      when(value) {
        is TestOperation.Data.Random.BeingHumanRandom -> BeingHumanRandom.toResponse(writer, value)
        is TestOperation.Data.Random.BeingWookieRandom -> BeingWookieRandom.toResponse(writer, value)
        is TestOperation.Data.Random.OtherRandom -> OtherRandom.toResponse(writer, value)
      }
    }

    object BeingHumanRandom : ResponseAdapter<TestOperation.Data.Random.BeingHumanRandom> {
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
          type =
              ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Object("Being")))),
          responseName = "friends",
          fieldName = "friends",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = listOf(
            ResponseField.FieldSet("Wookie", Friend.WookieFriend.RESPONSE_FIELDS),
            ResponseField.FieldSet(null, Friend.OtherFriend.RESPONSE_FIELDS),
          ),
        ),
        ResponseField(
          type = ResponseField.Type.Named.Other("String"),
          responseName = "profilePictureUrl",
          fieldName = "profilePictureUrl",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestOperation.Data.Random.BeingHumanRandom {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var friends: List<TestOperation.Data.Random.BeingHumanRandom.Friend>? = null
          var profilePictureUrl: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> friends = readList<TestOperation.Data.Random.BeingHumanRandom.Friend>(RESPONSE_FIELDS[2]) { reader ->
                reader.readObject<TestOperation.Data.Random.BeingHumanRandom.Friend> { reader ->
                  Friend.fromResponse(reader)
                }
              }?.map { it!! }
              3 -> profilePictureUrl = readString(RESPONSE_FIELDS[3])
              else -> break
            }
          }
          TestOperation.Data.Random.BeingHumanRandom(
            __typename = __typename!!,
            name = name!!,
            friends = friends!!,
            profilePictureUrl = profilePictureUrl
          )
        }
      }

      override fun toResponse(writer: ResponseWriter,
          value: TestOperation.Data.Random.BeingHumanRandom) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.name)
        writer.writeList(RESPONSE_FIELDS[2], value.friends) { value, listItemWriter ->
          listItemWriter.writeObject { writer ->
            Friend.toResponse(writer, value)
          }
        }
        writer.writeString(RESPONSE_FIELDS[3], value.profilePictureUrl)
      }

      object Friend : ResponseAdapter<TestOperation.Data.Random.BeingHumanRandom.Friend> {
        override fun fromResponse(reader: ResponseReader, __typename: String?):
            TestOperation.Data.Random.BeingHumanRandom.Friend {
          val typename = __typename ?: reader.readString(ResponseField.Typename)
          return when(typename) {
            "Wookie" -> WookieFriend.fromResponse(reader, typename)
            else -> OtherFriend.fromResponse(reader, typename)
          }
        }

        override fun toResponse(writer: ResponseWriter,
            value: TestOperation.Data.Random.BeingHumanRandom.Friend) {
          when(value) {
            is TestOperation.Data.Random.BeingHumanRandom.Friend.WookieFriend -> WookieFriend.toResponse(writer, value)
            is TestOperation.Data.Random.BeingHumanRandom.Friend.OtherFriend -> OtherFriend.toResponse(writer, value)
          }
        }

        object WookieFriend :
            ResponseAdapter<TestOperation.Data.Random.BeingHumanRandom.Friend.WookieFriend> {
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
              type = ResponseField.Type.Named.Other("Boolean"),
              responseName = "isFamous",
              fieldName = "isFamous",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            ),
            ResponseField(
              type = ResponseField.Type.Named.Other("Float"),
              responseName = "lifeExpectancy",
              fieldName = "lifeExpectancy",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            ),
            ResponseField(
              type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Race")),
              responseName = "race",
              fieldName = "race",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            )
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestOperation.Data.Random.BeingHumanRandom.Friend.WookieFriend {
            return reader.run {
              var __typename: String? = __typename
              var name: String? = null
              var isFamous: Boolean? = null
              var lifeExpectancy: Double? = null
              var race: Race? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> __typename = readString(RESPONSE_FIELDS[0])
                  1 -> name = readString(RESPONSE_FIELDS[1])
                  2 -> isFamous = readBoolean(RESPONSE_FIELDS[2])
                  3 -> lifeExpectancy = readDouble(RESPONSE_FIELDS[3])
                  4 -> race = readString(RESPONSE_FIELDS[4])?.let { Race.safeValueOf(it) }
                  else -> break
                }
              }
              TestOperation.Data.Random.BeingHumanRandom.Friend.WookieFriend(
                __typename = __typename!!,
                name = name!!,
                isFamous = isFamous,
                lifeExpectancy = lifeExpectancy,
                race = race!!
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestOperation.Data.Random.BeingHumanRandom.Friend.WookieFriend) {
            writer.writeString(RESPONSE_FIELDS[0], value.__typename)
            writer.writeString(RESPONSE_FIELDS[1], value.name)
            writer.writeBoolean(RESPONSE_FIELDS[2], value.isFamous)
            writer.writeDouble(RESPONSE_FIELDS[3], value.lifeExpectancy)
            writer.writeString(RESPONSE_FIELDS[4], value.race.rawValue)
          }
        }

        object OtherFriend :
            ResponseAdapter<TestOperation.Data.Random.BeingHumanRandom.Friend.OtherFriend> {
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
              type = ResponseField.Type.Named.Other("Boolean"),
              responseName = "isFamous",
              fieldName = "isFamous",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            )
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestOperation.Data.Random.BeingHumanRandom.Friend.OtherFriend {
            return reader.run {
              var __typename: String? = __typename
              var name: String? = null
              var isFamous: Boolean? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> __typename = readString(RESPONSE_FIELDS[0])
                  1 -> name = readString(RESPONSE_FIELDS[1])
                  2 -> isFamous = readBoolean(RESPONSE_FIELDS[2])
                  else -> break
                }
              }
              TestOperation.Data.Random.BeingHumanRandom.Friend.OtherFriend(
                __typename = __typename!!,
                name = name!!,
                isFamous = isFamous
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestOperation.Data.Random.BeingHumanRandom.Friend.OtherFriend) {
            writer.writeString(RESPONSE_FIELDS[0], value.__typename)
            writer.writeString(RESPONSE_FIELDS[1], value.name)
            writer.writeBoolean(RESPONSE_FIELDS[2], value.isFamous)
          }
        }
      }
    }

    object BeingWookieRandom : ResponseAdapter<TestOperation.Data.Random.BeingWookieRandom> {
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
          type =
              ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Object("Being")))),
          responseName = "friends",
          fieldName = "friends",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = listOf(
            ResponseField.FieldSet("Wookie", Friend.WookieFriend.RESPONSE_FIELDS),
            ResponseField.FieldSet(null, Friend.OtherFriend.RESPONSE_FIELDS),
          ),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Race")),
          responseName = "race",
          fieldName = "race",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestOperation.Data.Random.BeingWookieRandom {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var friends: List<TestOperation.Data.Random.BeingWookieRandom.Friend>? = null
          var race: Race? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> friends = readList<TestOperation.Data.Random.BeingWookieRandom.Friend>(RESPONSE_FIELDS[2]) { reader ->
                reader.readObject<TestOperation.Data.Random.BeingWookieRandom.Friend> { reader ->
                  Friend.fromResponse(reader)
                }
              }?.map { it!! }
              3 -> race = readString(RESPONSE_FIELDS[3])?.let { Race.safeValueOf(it) }
              else -> break
            }
          }
          TestOperation.Data.Random.BeingWookieRandom(
            __typename = __typename!!,
            name = name!!,
            friends = friends!!,
            race = race!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter,
          value: TestOperation.Data.Random.BeingWookieRandom) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.name)
        writer.writeList(RESPONSE_FIELDS[2], value.friends) { value, listItemWriter ->
          listItemWriter.writeObject { writer ->
            Friend.toResponse(writer, value)
          }
        }
        writer.writeString(RESPONSE_FIELDS[3], value.race.rawValue)
      }

      object Friend : ResponseAdapter<TestOperation.Data.Random.BeingWookieRandom.Friend> {
        override fun fromResponse(reader: ResponseReader, __typename: String?):
            TestOperation.Data.Random.BeingWookieRandom.Friend {
          val typename = __typename ?: reader.readString(ResponseField.Typename)
          return when(typename) {
            "Wookie" -> WookieFriend.fromResponse(reader, typename)
            else -> OtherFriend.fromResponse(reader, typename)
          }
        }

        override fun toResponse(writer: ResponseWriter,
            value: TestOperation.Data.Random.BeingWookieRandom.Friend) {
          when(value) {
            is TestOperation.Data.Random.BeingWookieRandom.Friend.WookieFriend -> WookieFriend.toResponse(writer, value)
            is TestOperation.Data.Random.BeingWookieRandom.Friend.OtherFriend -> OtherFriend.toResponse(writer, value)
          }
        }

        object WookieFriend :
            ResponseAdapter<TestOperation.Data.Random.BeingWookieRandom.Friend.WookieFriend> {
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
              type = ResponseField.Type.Named.Other("Float"),
              responseName = "lifeExpectancy",
              fieldName = "lifeExpectancy",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            )
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestOperation.Data.Random.BeingWookieRandom.Friend.WookieFriend {
            return reader.run {
              var __typename: String? = __typename
              var name: String? = null
              var lifeExpectancy: Double? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> __typename = readString(RESPONSE_FIELDS[0])
                  1 -> name = readString(RESPONSE_FIELDS[1])
                  2 -> lifeExpectancy = readDouble(RESPONSE_FIELDS[2])
                  else -> break
                }
              }
              TestOperation.Data.Random.BeingWookieRandom.Friend.WookieFriend(
                __typename = __typename!!,
                name = name!!,
                lifeExpectancy = lifeExpectancy
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestOperation.Data.Random.BeingWookieRandom.Friend.WookieFriend) {
            writer.writeString(RESPONSE_FIELDS[0], value.__typename)
            writer.writeString(RESPONSE_FIELDS[1], value.name)
            writer.writeDouble(RESPONSE_FIELDS[2], value.lifeExpectancy)
          }
        }

        object OtherFriend :
            ResponseAdapter<TestOperation.Data.Random.BeingWookieRandom.Friend.OtherFriend> {
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
              type = ResponseField.Type.Named.Other("Float"),
              responseName = "lifeExpectancy",
              fieldName = "lifeExpectancy",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            )
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestOperation.Data.Random.BeingWookieRandom.Friend.OtherFriend {
            return reader.run {
              var __typename: String? = __typename
              var name: String? = null
              var lifeExpectancy: Double? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> __typename = readString(RESPONSE_FIELDS[0])
                  1 -> name = readString(RESPONSE_FIELDS[1])
                  2 -> lifeExpectancy = readDouble(RESPONSE_FIELDS[2])
                  else -> break
                }
              }
              TestOperation.Data.Random.BeingWookieRandom.Friend.OtherFriend(
                __typename = __typename!!,
                name = name!!,
                lifeExpectancy = lifeExpectancy
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestOperation.Data.Random.BeingWookieRandom.Friend.OtherFriend) {
            writer.writeString(RESPONSE_FIELDS[0], value.__typename)
            writer.writeString(RESPONSE_FIELDS[1], value.name)
            writer.writeDouble(RESPONSE_FIELDS[2], value.lifeExpectancy)
          }
        }
      }
    }

    object OtherRandom : ResponseAdapter<TestOperation.Data.Random.OtherRandom> {
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
          TestOperation.Data.Random.OtherRandom {
        return reader.run {
          var __typename: String? = __typename
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              else -> break
            }
          }
          TestOperation.Data.Random.OtherRandom(
            __typename = __typename!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter,
          value: TestOperation.Data.Random.OtherRandom) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      }
    }
  }
}
