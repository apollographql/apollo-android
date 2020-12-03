// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_fragment_with_nested_fragments.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.example.root_query_fragment_with_nested_fragments.fragment.adapter.HeroFragmentImpl_ResponseAdapter
import kotlin.String

/**
 * A character from the Star Wars universe
 */
data class HeroFragmentImpl(
  override val __typename: String = "Character",
  /**
   * The name of the character
   */
  override val name: String
) : HeroFragment, GraphqlFragment {
  override fun marshaller(): ResponseFieldMarshaller {
    return ResponseFieldMarshaller { writer ->
      HeroFragmentImpl_ResponseAdapter.toResponse(writer, this)
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment heroFragment on Character {
        |  __typename
        |  name
        |}
        """.trimMargin()
  }
}
