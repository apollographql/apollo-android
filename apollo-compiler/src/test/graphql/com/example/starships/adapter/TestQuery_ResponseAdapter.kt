// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.starships.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.starships.TestQuery
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField.forObject("starship", "starship", mapOf<String, Any?>(
      "id" to mapOf<String, Any?>(
        "kind" to "Variable",
        "variableName" to "id")), true, null)
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return reader.run {
      var starship: TestQuery.Data.Starship? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> starship = readObject<TestQuery.Data.Starship>(RESPONSE_FIELDS[0]) { reader ->
            Starship.fromResponse(reader)
          }
          else -> break
        }
      }
      TestQuery.Data(
        starship = starship
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: TestQuery.Data) {
    if(value.starship == null) {
      writer.writeObject(RESPONSE_FIELDS[0], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
        Starship.toResponse(writer, value.starship)
      }
    }
  }

  object Starship : ResponseAdapter<TestQuery.Data.Starship> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField.forString("id", "id", null, false, null),
      ResponseField.forString("name", "name", null, false, null),
      ResponseField.forList("coordinates", "coordinates", null, true, null)
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?):
        TestQuery.Data.Starship {
      return reader.run {
        var id: String? = null
        var name: String? = null
        var coordinates: List<List<Double>>? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> id = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            2 -> coordinates = readList<List<Double>>(RESPONSE_FIELDS[2]) { reader ->
              reader.readList<Double> { reader ->
                reader.readDouble()
              }.map { it!! }
            }?.map { it!! }
            else -> break
          }
        }
        TestQuery.Data.Starship(
          id = id!!,
          name = name!!,
          coordinates = coordinates
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Starship) {
      writer.writeString(RESPONSE_FIELDS[0], value.id)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
      writer.writeList(RESPONSE_FIELDS[2], value.coordinates) { value, listItemWriter ->
        listItemWriter.writeList(value) { value, listItemWriter ->
          listItemWriter.writeDouble(value)}
      }
    }
  }
}
