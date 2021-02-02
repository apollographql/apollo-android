// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_fragment_inside_inline_fragment

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.inline_fragment_inside_inline_fragment.adapter.TestQuery_ResponseAdapter
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): String = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = TestQuery_ResponseAdapter
  override fun responseFields(): Map<String, Array<ResponseField>> = mapOf(
    "" to TestQuery_ResponseAdapter.RESPONSE_FIELDS
  )
  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val search: List<Search?>?
  ) : Operation.Data {
    fun searchFilterNotNull(): List<Search>? = search?.filterNotNull()

    interface Search {
      val __typename: String

      interface Character : Search {
        override val __typename: String

        /**
         * The name of the character
         */
        val name: String

        interface Human : Character {
          override val __typename: String

          /**
           * The name of the character
           */
          override val name: String

          /**
           * The home planet of the human, or null if unknown
           */
          val homePlanet: String?
        }

        interface Droid : Character {
          override val __typename: String

          /**
           * The name of the character
           */
          override val name: String

          /**
           * This droid's primary function
           */
          val primaryFunction: String?
        }
      }

      data class CharacterDroidSearch(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * This droid's primary function
         */
        override val primaryFunction: String?
      ) : Search, Character, Character.Droid

      data class CharacterHumanSearch(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * The home planet of the human, or null if unknown
         */
        override val homePlanet: String?
      ) : Search, Character, Character.Human

      data class OtherSearch(
        override val __typename: String
      ) : Search

      companion object {
        fun Search.asCharacter(): Character? = this as? Character
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "4f32ea4bdd2a95a29bde61273602c22c698cd333f1701001d1a339fb276c6438"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  search(text: "bla-bla") {
          |    __typename
          |    ... on Character {
          |      __typename
          |      name
          |      ... on Human {
          |        homePlanet
          |      }
          |      ... on Droid {
          |        primaryFunction
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestQuery"
  }
}
