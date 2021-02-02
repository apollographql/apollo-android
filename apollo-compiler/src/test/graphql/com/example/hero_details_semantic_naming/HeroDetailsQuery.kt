// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_details_semantic_naming

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.hero_details_semantic_naming.adapter.HeroDetailsQuery_ResponseAdapter
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HeroDetailsQuery : Query<HeroDetailsQuery.Data> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): String = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = HeroDetailsQuery_ResponseAdapter
  override fun responseFields(): Map<String, Array<ResponseField>> = mapOf(
    "" to HeroDetailsQuery_ResponseAdapter.RESPONSE_FIELDS
  )
  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    /**
     * A character from the Star Wars universe
     */
    data class Hero(
      /**
       * The name of the character
       */
      val name: String,
      /**
       * The friends of the character exposed as a connection with edges
       */
      val friendsConnection: FriendsConnection
    ) {
      /**
       * A connection object for a character's friends
       */
      data class FriendsConnection(
        /**
         * The total number of friends
         */
        val totalCount: Int?,
        /**
         * The edges for each of the character's friends.
         */
        val edges: List<Edge?>?
      ) {
        fun edgesFilterNotNull(): List<Edge>? = edges?.filterNotNull()

        /**
         * An edge object for a character's friends
         */
        data class Edge(
          /**
           * The character represented by this friendship edge
           */
          val node: Node?
        ) {
          /**
           * A character from the Star Wars universe
           */
          data class Node(
            /**
             * The name of the character
             */
            val name: String
          )
        }
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "5e11892e17cceb600c00f4565a27951dce5e8aadd95b64b8d13a835ed8fb11e1"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query HeroDetails {
          |  hero {
          |    name
          |    friendsConnection {
          |      totalCount
          |      edges {
          |        node {
          |          name
          |        }
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "HeroDetails"
  }
}
