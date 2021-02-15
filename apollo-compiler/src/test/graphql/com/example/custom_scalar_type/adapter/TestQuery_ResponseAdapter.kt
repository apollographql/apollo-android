// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.custom_scalar_type.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.example.custom_scalar_type.TestQuery
import com.example.custom_scalar_type.type.CustomScalars
import java.util.Date
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<TestQuery.Data> {
  val nullableHeroAdapter: ResponseAdapter<TestQuery.Data.Hero?> =
      NullableResponseAdapter(Hero(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var hero: TestQuery.Data.Hero? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> hero = nullableHeroAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      hero = hero
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    writer.beginObject()
    writer.name("hero")
    nullableHeroAdapter.toResponse(writer, value.hero)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        fieldName = "hero",
        fieldSets = listOf(
          ResponseField.FieldSet(null, Hero.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val stringAdapter: ResponseAdapter<String> = stringResponseAdapter

    val dateAdapter: ResponseAdapter<Date> =
        customScalarAdapters.responseAdapterFor<Date>(CustomScalars.Date)

    val listOfDateAdapter: ResponseAdapter<List<Date>> =
        ListResponseAdapter(customScalarAdapters.responseAdapterFor<Date>(CustomScalars.Date))

    val unsupportedTypeAdapter: ResponseAdapter<Any> =
        customScalarAdapters.responseAdapterFor<Any>(CustomScalars.UnsupportedType)

    val uRLAdapter: ResponseAdapter<java.lang.String> =
        customScalarAdapters.responseAdapterFor<java.lang.String>(CustomScalars.URL)

    val listOfURLAdapter: ResponseAdapter<List<java.lang.String>> =
        ListResponseAdapter(customScalarAdapters.responseAdapterFor<java.lang.String>(CustomScalars.URL))

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Hero {
      var name: String? = null
      var birthDate: Date? = null
      var appearanceDates: List<Date>? = null
      var fieldWithUnsupportedType: Any? = null
      var profileLink: java.lang.String? = null
      var links: List<java.lang.String>? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> name = stringAdapter.fromResponse(reader)
          1 -> birthDate = dateAdapter.fromResponse(reader)
          2 -> appearanceDates = listOfDateAdapter.fromResponse(reader)
          3 -> fieldWithUnsupportedType = unsupportedTypeAdapter.fromResponse(reader)
          4 -> profileLink = uRLAdapter.fromResponse(reader)
          5 -> links = listOfURLAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return TestQuery.Data.Hero(
        name = name!!,
        birthDate = birthDate!!,
        appearanceDates = appearanceDates!!,
        fieldWithUnsupportedType = fieldWithUnsupportedType!!,
        profileLink = profileLink!!,
        links = links!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      writer.beginObject()
      writer.name("name")
      stringAdapter.toResponse(writer, value.name)
      writer.name("birthDate")
      dateAdapter.toResponse(writer, value.birthDate)
      writer.name("appearanceDates")
      listOfDateAdapter.toResponse(writer, value.appearanceDates)
      writer.name("fieldWithUnsupportedType")
      unsupportedTypeAdapter.toResponse(writer, value.fieldWithUnsupportedType)
      writer.name("profileLink")
      uRLAdapter.toResponse(writer, value.profileLink)
      writer.name("links")
      listOfURLAdapter.toResponse(writer, value.links)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          fieldName = "name",
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
          fieldName = "birthDate",
        ),
        ResponseField(
          type =
              ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")))),
          fieldName = "appearanceDates",
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("UnsupportedType")),
          fieldName = "fieldWithUnsupportedType",
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("URL")),
          fieldName = "profileLink",
        ),
        ResponseField(
          type =
              ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Other("URL")))),
          fieldName = "links",
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }
  }
}
