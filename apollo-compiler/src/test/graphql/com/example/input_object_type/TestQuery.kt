// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.input_object_type

import com.apollographql.apollo.api.InputFieldMarshaller
import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.input_object_type.type.Episode
import com.example.input_object_type.type.ReviewInput
import kotlin.Any
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Map
import kotlin.jvm.Transient

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class TestQuery(
  val ep: Episode,
  val review: ReviewInput
) : Mutation<TestQuery.Data, TestQuery.Data, Operation.Variables> {
  @Transient
  private val variables: Operation.Variables = object : Operation.Variables() {
    override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
      this["ep"] = ep
      this["review"] = review
    }

    override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller { writer ->
      writer.writeString("ep", ep.rawValue)
      writer.writeObject("review", review.marshaller())
    }
  }

  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = variables
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
    Data(it)
  }

  data class CreateReview(
    /**
     * The number of stars this review gave, 1-5
     */
    val stars: Int,
    /**
     * Comment about the movie
     */
    val commentary: String?
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeInt(RESPONSE_FIELDS[0], stars)
      it.writeString(RESPONSE_FIELDS[1], commentary)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forInt("stars", "stars", null, false, null),
          ResponseField.forString("commentary", "commentary", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): CreateReview {
        val stars = reader.readInt(RESPONSE_FIELDS[0])
        val commentary = reader.readString(RESPONSE_FIELDS[1])
        return CreateReview(
          stars = stars,
          commentary = commentary
        )
      }
    }
  }

  data class Data(
    val createReview: CreateReview?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeObject(RESPONSE_FIELDS[0], createReview?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("createReview", "createReview", mapOf<String, Any>(
            "episode" to mapOf<String, Any>(
              "kind" to "Variable",
              "variableName" to "ep"),
            "review" to mapOf<String, Any>(
              "kind" to "Variable",
              "variableName" to "review")), true, null)
          )

      operator fun invoke(reader: ResponseReader): Data {
        val createReview = reader.readObject<CreateReview>(RESPONSE_FIELDS[0]) { reader ->
          CreateReview(reader)
        }

        return Data(
          createReview = createReview
        )
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "6f9c4b93c1cab4409a9a6c102fc3ec58291c5424298e3ee5df2a7b456621fc9e"

    val QUERY_DOCUMENT: String = """
        |mutation TestQuery(${'$'}ep: Episode!, ${'$'}review: ReviewInput!) {
        |  createReview(episode: ${'$'}ep, review: ${'$'}review) {
        |    stars
        |    commentary
        |  }
        |}
        """.trimMargin()

    val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
  }
}
