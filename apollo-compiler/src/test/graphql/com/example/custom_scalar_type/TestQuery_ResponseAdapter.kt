// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.custom_scalar_type

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.custom_scalar_type.type.CustomType
import java.util.Date
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
internal object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField.forObject("hero", "hero", null, true, null)
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return reader.run {
      var hero: TestQuery.Hero? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> hero = readObject<TestQuery.Hero>(RESPONSE_FIELDS[0]) { reader ->
            TestQuery_ResponseAdapter.Hero_ResponseAdapter.fromResponse(reader)
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
      writer.writeObject(RESPONSE_FIELDS[0]) {
        TestQuery_ResponseAdapter.Hero_ResponseAdapter.toResponse(writer, value.hero)
      }
    }
  }

  object Hero_ResponseAdapter : ResponseAdapter<TestQuery.Hero> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("__typename", "__typename", null, false, null),
      ResponseField.forString("name", "name", null, false, null),
      ResponseField.forCustomType("birthDate", "birthDate", null, false, CustomType.DATE, null),
      ResponseField.forList("appearanceDates", "appearanceDates", null, false, null),
      ResponseField.forCustomType("fieldWithUnsupportedType", "fieldWithUnsupportedType", null, false, CustomType.UNSUPPORTEDTYPE, null),
      ResponseField.forCustomType("profileLink", "profileLink", null, false, CustomType.URL, null),
      ResponseField.forList("links", "links", null, false, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Hero {
      return reader.run {
        var __typename: String? = __typename
        var name: String? = null
        var birthDate: Date? = null
        var appearanceDates: List<Date>? = null
        var fieldWithUnsupportedType: Any? = null
        var profileLink: java.lang.String? = null
        var links: List<java.lang.String>? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> __typename = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            2 -> birthDate = readCustomType<Date>(RESPONSE_FIELDS[2] as ResponseField.CustomTypeField)
            3 -> appearanceDates = readList<Date>(RESPONSE_FIELDS[3]) { reader ->
              reader.readCustomType<Date>(CustomType.DATE)
            }?.map { it!! }
            4 -> fieldWithUnsupportedType = readCustomType<Any>(RESPONSE_FIELDS[4] as ResponseField.CustomTypeField)
            5 -> profileLink = readCustomType<java.lang.String>(RESPONSE_FIELDS[5] as ResponseField.CustomTypeField)
            6 -> links = readList<java.lang.String>(RESPONSE_FIELDS[6]) { reader ->
              reader.readCustomType<java.lang.String>(CustomType.URL)
            }?.map { it!! }
            else -> break
          }
        }
        TestQuery.Hero(
          __typename = __typename!!,
          name = name!!,
          birthDate = birthDate!!,
          appearanceDates = appearanceDates!!,
          fieldWithUnsupportedType = fieldWithUnsupportedType!!,
          profileLink = profileLink!!,
          links = links!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Hero) {
      writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
      writer.writeCustom(RESPONSE_FIELDS[2] as ResponseField.CustomTypeField, value.birthDate)
      writer.writeList(RESPONSE_FIELDS[3], value.appearanceDates) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeCustom(CustomType.DATE, value)}
      }
      writer.writeCustom(RESPONSE_FIELDS[4] as ResponseField.CustomTypeField, value.fieldWithUnsupportedType)
      writer.writeCustom(RESPONSE_FIELDS[5] as ResponseField.CustomTypeField, value.profileLink)
      writer.writeList(RESPONSE_FIELDS[6], value.links) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeCustom(CustomType.URL, value)}
      }
    }
  }
}
