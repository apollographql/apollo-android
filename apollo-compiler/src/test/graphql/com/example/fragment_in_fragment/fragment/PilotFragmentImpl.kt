// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_in_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.example.fragment_in_fragment.fragment.adapter.PilotFragmentImpl_ResponseAdapter
import kotlin.String

/**
 * An individual person or character within the Star Wars universe.
 */
data class PilotFragmentImpl(
  override val __typename: String = "Person",
  /**
   * The name of this person.
   */
  override val name: String?,
  /**
   * A planet that this person was born on or inhabits.
   */
  override val homeworld: Homeworld?
) : PilotFragment, GraphqlFragment {
  override fun marshaller(): ResponseFieldMarshaller {
    return ResponseFieldMarshaller { writer ->
      PilotFragmentImpl_ResponseAdapter.toResponse(writer, this)
    }
  }

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  interface Homeworld : PilotFragment.Homeworld {
    override val __typename: String

    fun asPlanet(): Planet? = this as? Planet

    fun asPlanetFragment(): PlanetFragment? = this as? PlanetFragment

    override fun marshaller(): ResponseFieldMarshaller

    interface Planet : PilotFragment.Homeworld, PilotFragment.Homeworld.Planet, PlanetFragment,
        Homeworld {
      override val __typename: String

      /**
       * The name of this planet.
       */
      override val name: String?

      override fun marshaller(): ResponseFieldMarshaller
    }

    data class PlanetHomeworld(
      override val __typename: String = "Planet",
      /**
       * The name of this planet.
       */
      override val name: String?
    ) : PilotFragment.Homeworld, PilotFragment.Homeworld.Planet, PlanetFragment, Homeworld, Planet {
      override fun marshaller(): ResponseFieldMarshaller {
        return ResponseFieldMarshaller { writer ->
          PilotFragmentImpl_ResponseAdapter.Homeworld.PlanetHomeworld.toResponse(writer, this)
        }
      }
    }

    /**
     * A large mass, planet or planetoid in the Star Wars Universe, at the time of
     * 0 ABY.
     */
    data class OtherHomeworld(
      override val __typename: String = "Planet"
    ) : PilotFragment.Homeworld, Homeworld {
      override fun marshaller(): ResponseFieldMarshaller {
        return ResponseFieldMarshaller { writer ->
          PilotFragmentImpl_ResponseAdapter.Homeworld.OtherHomeworld.toResponse(writer, this)
        }
      }
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment pilotFragment on Person {
        |  __typename
        |  name
        |  homeworld {
        |    __typename
        |    ...planetFragment
        |  }
        |}
        """.trimMargin()
  }
}
