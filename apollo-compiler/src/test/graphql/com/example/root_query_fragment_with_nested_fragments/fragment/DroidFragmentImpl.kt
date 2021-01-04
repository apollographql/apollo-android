// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_fragment_with_nested_fragments.fragment

import com.apollographql.apollo.api.Adaptable
import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.root_query_fragment_with_nested_fragments.fragment.adapter.DroidFragmentImpl_ResponseAdapter
import kotlin.String

/**
 * An autonomous mechanical character in the Star Wars universe
 */
data class DroidFragmentImpl(
  override val __typename: String = "Droid",
  /**
   * What others call this droid
   */
  override val name: String,
  /**
   * This droid's primary function
   */
  override val primaryFunction: String?
) : DroidFragment, GraphqlFragment, Adaptable<DroidFragmentImpl> {
  override fun adapter(): ResponseAdapter<DroidFragmentImpl> {
    return DroidFragmentImpl_ResponseAdapter
  }
}
