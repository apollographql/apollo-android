// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.fragment_used_twice.fragment.HeroDetailsImpl
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HeroDetailsImpl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<HeroDetailsImpl.Data> {
  val characterDataAdapter: CharacterData =
      com.example.fragment_used_twice.fragment.adapter.HeroDetailsImpl_ResponseAdapter.CharacterData(customScalarAdapters)

  val otherDataAdapter: OtherData =
      com.example.fragment_used_twice.fragment.adapter.HeroDetailsImpl_ResponseAdapter.OtherData(customScalarAdapters)

  override fun fromResponse(reader: JsonReader): HeroDetailsImpl.Data {
    reader.beginObject()
    check(reader.nextName() == "__typename")
    val typename = reader.nextString()

    return when(typename) {
      "Droid" -> characterDataAdapter.fromResponse(reader, typename)
      "Human" -> characterDataAdapter.fromResponse(reader, typename)
      else -> otherDataAdapter.fromResponse(reader, typename)
    }
    .also { reader.endObject() }
  }

  override fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data) {
    when(value) {
      is HeroDetailsImpl.Data.CharacterData -> characterDataAdapter.toResponse(writer, value)
      is HeroDetailsImpl.Data.OtherData -> otherDataAdapter.toResponse(writer, value)
    }
  }

  class CharacterData(
    customScalarAdapters: CustomScalarAdapters
  ) {
    val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val birthDateAdapter: ResponseAdapter<Any> =
        customScalarAdapters.responseAdapterFor<Any>("Date")

    fun fromResponse(reader: JsonReader, __typename: String?): HeroDetailsImpl.Data.CharacterData {
      var __typename: String? = __typename
      var name: String? = null
      var birthDate: Any? = null
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
              UnexpectedNullValue("__typename")
          1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
          2 -> birthDate = birthDateAdapter.fromResponse(reader) ?: throw
              UnexpectedNullValue("birthDate")
          else -> break
        }
      }
      return HeroDetailsImpl.Data.CharacterData(
        __typename = __typename!!,
        name = name!!,
        birthDate = birthDate!!
      )
    }

    fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.CharacterData) {
      __typenameAdapter.toResponse(writer, value.__typename)
      nameAdapter.toResponse(writer, value.name)
      birthDateAdapter.toResponse(writer, value.birthDate)
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
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
          responseName = "birthDate",
          fieldName = "birthDate",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }
  }

  class OtherData(
    customScalarAdapters: CustomScalarAdapters
  ) {
    val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    fun fromResponse(reader: JsonReader, __typename: String?): HeroDetailsImpl.Data.OtherData {
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
      return HeroDetailsImpl.Data.OtherData(
        __typename = __typename!!,
        name = name!!
      )
    }

    fun toResponse(writer: JsonWriter, value: HeroDetailsImpl.Data.OtherData) {
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
}
