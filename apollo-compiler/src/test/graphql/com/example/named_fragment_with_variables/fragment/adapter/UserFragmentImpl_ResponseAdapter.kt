// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.named_fragment_with_variables.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.named_fragment_with_variables.fragment.UserFragmentImpl
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class UserFragmentImpl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<UserFragmentImpl.Data> {
  val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val firstNameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val lastNameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val avatarAdapter: ResponseAdapter<String> = stringResponseAdapter

  override fun fromResponse(reader: JsonReader): UserFragmentImpl.Data {
    var __typename: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var avatar: String? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("__typename")
        1 -> firstName = firstNameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("firstName")
        2 -> lastName = lastNameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("lastName")
        3 -> avatar = avatarAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("avatar")
        else -> break
      }
    }
    reader.endObject()
    return UserFragmentImpl.Data(
      __typename = __typename!!,
      firstName = firstName!!,
      lastName = lastName!!,
      avatar = avatar!!
    )
  }

  override fun toResponse(writer: JsonWriter, value: UserFragmentImpl.Data) {
    writer.beginObject()
    writer.name("__typename")
    __typenameAdapter.toResponse(writer, value.__typename)
    writer.name("firstName")
    firstNameAdapter.toResponse(writer, value.firstName)
    writer.name("lastName")
    lastNameAdapter.toResponse(writer, value.lastName)
    writer.name("avatar")
    avatarAdapter.toResponse(writer, value.avatar)
    writer.endObject()
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
        responseName = "firstName",
        fieldName = "firstName",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = emptyList(),
      ),
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "lastName",
        fieldName = "lastName",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = emptyList(),
      ),
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "avatar",
        fieldName = "avatar",
        arguments = mapOf<String, Any?>(
          "size" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "size")),
        conditions = emptyList(),
        fieldSets = emptyList(),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }
}
