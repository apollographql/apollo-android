// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_in_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import kotlin.String
import kotlin.Suppress

/**
 * An individual person or character within the Star Wars universe.
 */
@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
interface PilotFragment : GraphqlFragment {
  val __typename: String

  /**
   * The name of this person.
   */
  val name: String?

  /**
   * A planet that this person was born on or inhabits.
   */
  val homeworld: Homeworld?

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  interface Homeworld {
    val __typename: String

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  data class Homeworld1(
    override val __typename: String = "Planet",
    /**
     * The name of this planet.
     */
    override val name: String?
  ) : PlanetFragment, Homeworld {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        PilotFragment_ResponseAdapter.Homeworld1_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

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
    override val homeworld: Homeworld1?
  ) : PilotFragment {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        PilotFragment_ResponseAdapter.toResponse(writer, this)
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

    operator fun invoke(reader: ResponseReader): PilotFragment {
      return PilotFragment_ResponseAdapter.fromResponse(reader)
    }

    fun Mapper(): ResponseFieldMapper<PilotFragment> {
      return ResponseFieldMapper { reader ->
        PilotFragment_ResponseAdapter.fromResponse(reader)
      }
    }
  }
}
