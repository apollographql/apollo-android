// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.simple_fragment_with_inline_fragments.fragment.adapter

import com.apollographql.apollo.api.ResponseAdapterCache
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.DoubleResponseAdapter
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.StringResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
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
  responseAdapterCache: ResponseAdapterCache
) : ResponseAdapter<HeroDetailsImpl.Data> {
  private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

  private val nullableListOfNullableFriendsAdapter:
      ResponseAdapter<List<HeroDetailsImpl.Data.Friends?>?> =
      NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friends(responseAdapterCache))))

  override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data {
    var __typename: String? = null
    var name: String? = null
    var friends: List<HeroDetailsImpl.Data.Friends?>? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = stringAdapter.fromResponse(reader)
        1 -> name = stringAdapter.fromResponse(reader)
        2 -> friends = nullableListOfNullableFriendsAdapter.fromResponse(reader)
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
    nullableListOfNullableFriendsAdapter.toResponse(writer, value.friends)
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
          ResponseField.FieldSet("Human", Friends.HumanFriends.RESPONSE_FIELDS),
          ResponseField.FieldSet("Droid", Friends.DroidFriends.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Friends.OtherFriends.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Friends(
    responseAdapterCache: ResponseAdapterCache
  ) : ResponseAdapter<HeroDetailsImpl.Data.Friends> {
    val HumanFriendsAdapter: HumanFriends =
        com.example.simple_fragment_with_inline_fragments.fragment.adapter.HeroDetailsImpl_ResponseAdapter.Friends.HumanFriends(responseAdapterCache)

    val DroidFriendsAdapter: DroidFriends =
        com.example.simple_fragment_with_inline_fragments.fragment.adapter.HeroDetailsImpl_ResponseAdapter.Friends.DroidFriends(responseAdapterCache)

    val OtherFriendsAdapter: OtherFriends =
        com.example.simple_fragment_with_inline_fragments.fragment.adapter.HeroDetailsImpl_ResponseAdapter.Friends.OtherFriends(responseAdapterCache)

    override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data.Friends {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> HumanFriendsAdapter.fromResponse(reader, typename)
        "Droid" -> DroidFriendsAdapter.fromResponse(reader, typename)
        else -> OtherFriendsAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.Friends) {
      when(value) {
        is HeroDetailsImpl.Data.Friends.HumanFriends -> HumanFriendsAdapter.toResponse(writer, value)
        is HeroDetailsImpl.Data.Friends.DroidFriends -> DroidFriendsAdapter.toResponse(writer, value)
        is HeroDetailsImpl.Data.Friends.OtherFriends -> OtherFriendsAdapter.toResponse(writer, value)
      }
    }

    class HumanFriends(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      private val nullableFloatAdapter: ResponseAdapter<Double?> =
          NullableResponseAdapter(DoubleResponseAdapter)

      fun fromResponse(reader: JsonReader, __typename: String?):
          HeroDetailsImpl.Data.Friends.HumanFriends {
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
        return HeroDetailsImpl.Data.Friends.HumanFriends(
          __typename = __typename!!,
          name = name!!,
          height = height
        )
      }

      fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.Friends.HumanFriends) {
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

    class DroidFriends(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      private val nullableStringAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(StringResponseAdapter)

      fun fromResponse(reader: JsonReader, __typename: String?):
          HeroDetailsImpl.Data.Friends.DroidFriends {
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
        return HeroDetailsImpl.Data.Friends.DroidFriends(
          __typename = __typename!!,
          name = name!!,
          primaryFunction = primaryFunction
        )
      }

      fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.Friends.DroidFriends) {
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

    class OtherFriends(
      responseAdapterCache: ResponseAdapterCache
    ) {
      private val stringAdapter: ResponseAdapter<String> = StringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?):
          HeroDetailsImpl.Data.Friends.OtherFriends {
        var __typename: String? = __typename
        var name: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = stringAdapter.fromResponse(reader)
            1 -> name = stringAdapter.fromResponse(reader)
            else -> break
          }
        }
        return HeroDetailsImpl.Data.Friends.OtherFriends(
          __typename = __typename!!,
          name = name!!
        )
      }

      fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.Friends.OtherFriends) {
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
