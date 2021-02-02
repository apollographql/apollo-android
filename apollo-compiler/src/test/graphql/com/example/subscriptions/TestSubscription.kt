// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.subscriptions

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.Subscription
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.subscriptions.adapter.TestSubscription_ResponseAdapter
import kotlin.Any
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Map
import kotlin.jvm.Transient

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
data class TestSubscription(
  val repo: String
) : Subscription<TestSubscription.Data> {
  @Transient
  private val variables: Operation.Variables = object : Operation.Variables() {
    override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
      this["repo"] = this@TestSubscription.repo
    }

    override fun marshaller(): InputFieldMarshaller {
      return InputFieldMarshaller.invoke { writer ->
        writer.writeString("repo", this@TestSubscription.repo)
      }
    }
  }

  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = variables

  override fun name(): String = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = TestSubscription_ResponseAdapter
  override fun responseFields(): Map<String, Array<ResponseField>> = mapOf(
    "" to TestSubscription_ResponseAdapter.RESPONSE_FIELDS
  )
  data class Data(
    /**
     * Subscription fires on every comment added
     */
    val commentAdded: CommentAdded?
  ) : Operation.Data {
    /**
     * A comment about an entry, submitted by a user
     */
    data class CommentAdded(
      /**
       * The SQL ID of this entry
       */
      val id: Int,
      /**
       * The text of the comment
       */
      val content: String
    )
  }

  companion object {
    const val OPERATION_ID: String =
        "f053ee1afe42260f1511e417b6133f1cb8507c185e2e7b4e1e579696dbc8f2af"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |subscription TestSubscription(${'$'}repo: String!) {
          |  commentAdded(repoFullName: ${'$'}repo) {
          |    id
          |    content
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestSubscription"
  }
}
