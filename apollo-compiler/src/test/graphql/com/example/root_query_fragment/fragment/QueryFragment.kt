// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseReader
import com.example.root_query_fragment.fragment.adapter.QueryFragmentImpl_ResponseAdapter
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
interface QueryFragment : GraphqlFragment {
  val __typename: String

  val hero: Hero?

  /**
   * A character from the Star Wars universe
   */
  interface Hero {
    /**
     * The name of the character
     */
    val name: String
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment QueryFragment on Query {
        |  __typename
        |  hero {
        |    name
        |  }
        |}
        """.trimMargin()

    operator fun invoke(reader: ResponseReader): QueryFragment {
      return QueryFragmentImpl_ResponseAdapter.fromResponse(reader)
    }
  }
}
