// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.mutation_create_review.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.intResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.mutation_create_review.CreateReviewForEpisode
import com.example.mutation_create_review.type.Episode
import com.example.mutation_create_review.type.Episode_ResponseAdapter
import java.util.Date
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
internal class CreateReviewForEpisode_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<CreateReviewForEpisode.Data> {
  val createReviewAdapter: ResponseAdapter<CreateReviewForEpisode.Data.CreateReview?> =
      NullableResponseAdapter(CreateReview(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): CreateReviewForEpisode.Data {
    var createReview: CreateReviewForEpisode.Data.CreateReview? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> createReview = createReviewAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return CreateReviewForEpisode.Data(
      createReview = createReview
    )
  }

  override fun toResponse(writer: JsonWriter, value: CreateReviewForEpisode.Data) {
    writer.beginObject()
    writer.name("createReview")
    createReviewAdapter.toResponse(writer, value.createReview)
    writer.endObject()
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Review"),
        responseName = "createReview",
        fieldName = "createReview",
        arguments = mapOf<String, Any?>(
          "episode" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "ep"),
          "review" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "review")),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet(null, CreateReview.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class CreateReview(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<CreateReviewForEpisode.Data.CreateReview> {
    val starsAdapter: ResponseAdapter<Int> = intResponseAdapter

    val commentaryAdapter: ResponseAdapter<String?> = NullableResponseAdapter(stringResponseAdapter)

    val listOfListOfStringAdapter: ResponseAdapter<List<List<String>>?> =
        NullableResponseAdapter(ListResponseAdapter(ListResponseAdapter(stringResponseAdapter)))

    val listOfListOfEnumAdapter: ResponseAdapter<List<List<Episode>>?> =
        NullableResponseAdapter(ListResponseAdapter(ListResponseAdapter(Episode_ResponseAdapter)))

    val listOfListOfCustomAdapter: ResponseAdapter<List<List<Date>>?> =
        NullableResponseAdapter(ListResponseAdapter(ListResponseAdapter(customScalarAdapters.responseAdapterFor<Date>("Date"))))

    val listOfListOfObjectAdapter:
        ResponseAdapter<List<List<CreateReviewForEpisode.Data.CreateReview.ListOfListOfObject>>?> =
        NullableResponseAdapter(ListResponseAdapter(ListResponseAdapter(ListOfListOfObject(customScalarAdapters))))

    override fun fromResponse(reader: JsonReader): CreateReviewForEpisode.Data.CreateReview {
      var stars: Int? = null
      var commentary: String? = null
      var listOfListOfString: List<List<String>>? = null
      var listOfListOfEnum: List<List<Episode>>? = null
      var listOfListOfCustom: List<List<Date>>? = null
      var listOfListOfObject: List<List<CreateReviewForEpisode.Data.CreateReview.ListOfListOfObject>>? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> stars = starsAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("stars")
          1 -> commentary = commentaryAdapter.fromResponse(reader)
          2 -> listOfListOfString = listOfListOfStringAdapter.fromResponse(reader)
          3 -> listOfListOfEnum = listOfListOfEnumAdapter.fromResponse(reader)
          4 -> listOfListOfCustom = listOfListOfCustomAdapter.fromResponse(reader)
          5 -> listOfListOfObject = listOfListOfObjectAdapter.fromResponse(reader)
          else -> break
        }
      }
      reader.endObject()
      return CreateReviewForEpisode.Data.CreateReview(
        stars = stars!!,
        commentary = commentary,
        listOfListOfString = listOfListOfString,
        listOfListOfEnum = listOfListOfEnum,
        listOfListOfCustom = listOfListOfCustom,
        listOfListOfObject = listOfListOfObject
      )
    }

    override fun toResponse(writer: JsonWriter, value: CreateReviewForEpisode.Data.CreateReview) {
      writer.beginObject()
      writer.name("stars")
      starsAdapter.toResponse(writer, value.stars)
      writer.name("commentary")
      commentaryAdapter.toResponse(writer, value.commentary)
      writer.name("listOfListOfString")
      listOfListOfStringAdapter.toResponse(writer, value.listOfListOfString)
      writer.name("listOfListOfEnum")
      listOfListOfEnumAdapter.toResponse(writer, value.listOfListOfEnum)
      writer.name("listOfListOfCustom")
      listOfListOfCustomAdapter.toResponse(writer, value.listOfListOfCustom)
      writer.name("listOfListOfObject")
      listOfListOfObjectAdapter.toResponse(writer, value.listOfListOfObject)
      writer.endObject()
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Int")),
          responseName = "stars",
          fieldName = "stars",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.Named.Other("String"),
          responseName = "commentary",
          fieldName = "commentary",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        ),
        ResponseField(
          type =
              ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String"))))),
          responseName = "listOfListOfString",
          fieldName = "listOfListOfString",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        ),
        ResponseField(
          type =
              ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Episode"))))),
          responseName = "listOfListOfEnum",
          fieldName = "listOfListOfEnum",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        ),
        ResponseField(
          type =
              ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date"))))),
          responseName = "listOfListOfCustom",
          fieldName = "listOfListOfCustom",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        ),
        ResponseField(
          type =
              ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Object("Character"))))),
          responseName = "listOfListOfObject",
          fieldName = "listOfListOfObject",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = listOf(
            ResponseField.FieldSet(null, ListOfListOfObject.RESPONSE_FIELDS)
          ),
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }

    class ListOfListOfObject(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<CreateReviewForEpisode.Data.CreateReview.ListOfListOfObject> {
      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      override fun fromResponse(reader: JsonReader):
          CreateReviewForEpisode.Data.CreateReview.ListOfListOfObject {
        var name: String? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            else -> break
          }
        }
        reader.endObject()
        return CreateReviewForEpisode.Data.CreateReview.ListOfListOfObject(
          name = name!!
        )
      }

      override fun toResponse(writer: JsonWriter,
          value: CreateReviewForEpisode.Data.CreateReview.ListOfListOfObject) {
        writer.beginObject()
        writer.name("name")
        nameAdapter.toResponse(writer, value.name)
        writer.endObject()
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
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
}
