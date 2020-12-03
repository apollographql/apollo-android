// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.unique_type_name.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import com.example.unique_type_name.fragment.adapter.HeroDetailsImpl_ResponseAdapter
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
interface HeroDetail : GraphqlFragment {
  val __typename: String

  /**
   * The name of the character
   */
  val name: String

  /**
   * The friends of the character exposed as a connection with edges
   */
  val friendsConnection: FriendsConnection

  /**
   * A connection object for a character's friends
   */
  interface FriendsConnection {
    /**
     * The total number of friends
     */
    val totalCount: Int?

    /**
     * The edges for each of the character's friends.
     */
    val edges: List<Edge?>?

    fun marshaller(): ResponseFieldMarshaller

    /**
     * An edge object for a character's friends
     */
    interface Edge {
      /**
       * The character represented by this friendship edge
       */
      val node: Node?

      fun marshaller(): ResponseFieldMarshaller

      /**
       * A character from the Star Wars universe
       */
      interface Node {
        /**
         * The name of the character
         */
        val name: String

        fun marshaller(): ResponseFieldMarshaller
      }
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment HeroDetails on Character {
        |  __typename
        |  name
        |  friendsConnection {
        |    totalCount
        |    edges {
        |      node {
        |        name
        |      }
        |    }
        |  }
        |}
        """.trimMargin()

    operator fun invoke(reader: ResponseReader): HeroDetail {
      return HeroDetailsImpl_ResponseAdapter.fromResponse(reader)
    }

    fun Mapper(): ResponseFieldMapper<HeroDetail> {
      return ResponseFieldMapper { reader ->
        HeroDetailsImpl_ResponseAdapter.fromResponse(reader)
      }
    }
  }
}
