// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.simple_fragment_with_inline_fragments.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.doubleResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.example.simple_fragment_with_inline_fragments.fragment.HeroDetailsImpl
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HeroDetailsImpl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<HeroDetailsImpl.Data> {
  val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

  val nullableListOfNullableFriendAdapter: ResponseAdapter<List<HeroDetailsImpl.Data.Friend?>?> =
      NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friend(customScalarAdapters))))

  override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data {
    var __typename: String? = null
    var name: String? = null
    var friends: List<HeroDetailsImpl.Data.Friend?>? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = stringAdapter.fromResponse(reader)
        1 -> name = stringAdapter.fromResponse(reader)
        2 -> friends = nullableListOfNullableFriendAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return HeroDetailsImpl.Data(
      __typename = __typename!!,
      name = name!!,
      friends = friends
    )
  }

  override fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data) {
    writer.beginObject()
    writer.name("__typename")
    stringAdapter.toResponse(writer, value.__typename)
    writer.name("name")
    stringAdapter.toResponse(writer, value.name)
    writer.name("friends")
    nullableListOfNullableFriendAdapter.toResponse(writer, value.friends)
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
        type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
        fieldName = "friends",
        fieldSets = listOf(
          ResponseField.FieldSet("Human", Friend.HumanFriend.RESPONSE_FIELDS),
          ResponseField.FieldSet("Droid", Friend.DroidFriend.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Friend.OtherFriend.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Friend(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<HeroDetailsImpl.Data.Friend> {
    val HumanFriendAdapter: HumanFriend =
        com.example.simple_fragment_with_inline_fragments.fragment.adapter.HeroDetailsImpl_ResponseAdapter.Friend.HumanFriend(customScalarAdapters)

    val DroidFriendAdapter: DroidFriend =
        com.example.simple_fragment_with_inline_fragments.fragment.adapter.HeroDetailsImpl_ResponseAdapter.Friend.DroidFriend(customScalarAdapters)

    val OtherFriendAdapter: OtherFriend =
        com.example.simple_fragment_with_inline_fragments.fragment.adapter.HeroDetailsImpl_ResponseAdapter.Friend.OtherFriend(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data.Friend {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> HumanFriendAdapter.fromResponse(reader, typename)
        "Droid" -> DroidFriendAdapter.fromResponse(reader, typename)
        else -> OtherFriendAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.Friend) {
      when(value) {
        is HeroDetailsImpl.Data.Friend.HumanFriend -> HumanFriendAdapter.toResponse(writer, value)
        is HeroDetailsImpl.Data.Friend.DroidFriend -> DroidFriendAdapter.toResponse(writer, value)
        is HeroDetailsImpl.Data.Friend.OtherFriend -> OtherFriendAdapter.toResponse(writer, value)
      }
    }

    class HumanFriend(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nullableFloatAdapter: ResponseAdapter<Double?> =
          NullableResponseAdapter(doubleResponseAdapter)

      fun fromResponse(reader: JsonReader, __typename: String?):
          HeroDetailsImpl.Data.Friend.HumanFriend {
        var __typename: String? = __typename
        var name: String? = null
        var height: Double? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            2 -> height = nullableFloatAdapter.fromResponse(reader)
            else -> break
          }
        }
        return HeroDetailsImpl.Data.Friend.HumanFriend(
          __typename = __typename!!,
          name = name!!,
          height = height
        )
      }

      fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.Friend.HumanFriend) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("height")
        nullableFloatAdapter.toResponse(writer, value.height)
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
            type = ResponseField.Type.Named.Other("Float"),
            fieldName = "height",
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class DroidFriend(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nullableStringAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(stringResponseAdapter)

      fun fromResponse(reader: JsonReader, __typename: String?):
          HeroDetailsImpl.Data.Friend.DroidFriend {
        var __typename: String? = __typename
        var name: String? = null
        var primaryFunction: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            2 -> primaryFunction = nullableStringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return HeroDetailsImpl.Data.Friend.DroidFriend(
          __typename = __typename!!,
          name = name!!,
          primaryFunction = primaryFunction
        )
      }

      fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.Friend.DroidFriend) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.name("primaryFunction")
        nullableStringAdapter.toResponse(writer, value.primaryFunction)
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
            type = ResponseField.Type.Named.Other("String"),
            fieldName = "primaryFunction",
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class OtherFriend(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          HeroDetailsImpl.Data.Friend.OtherFriend {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return HeroDetailsImpl.Data.Friend.OtherFriend(
          __typename = __typename!!,
          name = name!!
        )
      }

      fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.Friend.OtherFriend) {
        writer.beginObject()
        writer.name("__typename")
        stringAdapter.toResponse(writer, value.__typename)
        writer.name("name")
        stringAdapter.toResponse(writer, value.name)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.Typename,
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
