// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_fragment.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.union_fragment.TestQuery
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.List(ResponseField.Type.Named.Object("SearchResult")),
      responseName = "search",
      fieldName = "search",
      arguments = mapOf<String, Any?>(
        "text" to "test"),
      conditions = emptyList(),
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return reader.run {
      var search: List<TestQuery.Data.Search?>? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> search = readList<TestQuery.Data.Search>(RESPONSE_FIELDS[0]) { reader ->
            reader.readObject<TestQuery.Data.Search> { reader ->
              Search.fromResponse(reader)
            }
          }
          else -> break
        }
      }
      TestQuery.Data(
        search = search
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: TestQuery.Data) {
    writer.writeList(RESPONSE_FIELDS[0], value.search) { value, listItemWriter ->
      listItemWriter.writeObject { writer ->
        Search.toResponse(writer, value)
      }
    }
  }

  object Search : ResponseAdapter<TestQuery.Data.Search> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "__typename",
        fieldName = "__typename",
        arguments = emptyMap(),
        conditions = emptyList(),
      )
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data.Search {
      val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
      return when(typename) {
        "Starship" -> StarshipSearch.fromResponse(reader, typename)
        else -> OtherSearch.fromResponse(reader, typename)
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Search) {
      when(value) {
        is TestQuery.Data.Search.StarshipSearch -> StarshipSearch.toResponse(writer, value)
        is TestQuery.Data.Search.OtherSearch -> OtherSearch.toResponse(writer, value)
      }
    }

    object StarshipSearch : ResponseAdapter<TestQuery.Data.Search.StarshipSearch> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "__typename",
          fieldName = "__typename",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "id",
          fieldName = "id",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "name",
          fieldName = "name",
          arguments = emptyMap(),
          conditions = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Search.StarshipSearch {
        return reader.run {
          var __typename: String? = __typename
          var id: String? = null
          var name: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> id = readString(RESPONSE_FIELDS[1])
              2 -> name = readString(RESPONSE_FIELDS[2])
              else -> break
            }
          }
          TestQuery.Data.Search.StarshipSearch(
            __typename = __typename!!,
            id = id!!,
            name = name!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Search.StarshipSearch) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.id)
        writer.writeString(RESPONSE_FIELDS[2], value.name)
      }
    }

    object OtherSearch : ResponseAdapter<TestQuery.Data.Search.OtherSearch> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "__typename",
          fieldName = "__typename",
          arguments = emptyMap(),
          conditions = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Search.OtherSearch {
        return reader.run {
          var __typename: String? = __typename
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              else -> break
            }
          }
          TestQuery.Data.Search.OtherSearch(
            __typename = __typename!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Search.OtherSearch) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      }
    }
  }
}
