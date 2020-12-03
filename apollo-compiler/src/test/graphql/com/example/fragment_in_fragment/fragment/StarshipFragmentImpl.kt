// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_in_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.example.fragment_in_fragment.fragment.adapter.StarshipFragmentImpl_ResponseAdapter
import kotlin.String
import kotlin.collections.List

/**
 * A single transport craft that has hyperdrive capability.
 */
data class StarshipFragmentImpl(
  override val __typename: String = "Starship",
  /**
   * The ID of an object
   */
  override val id: String,
  /**
   * The name of this starship. The common name, such as "Death Star".
   */
  override val name: String?,
  override val pilotConnection: PilotConnection?
) : StarshipFragment, GraphqlFragment {
  override fun marshaller(): ResponseFieldMarshaller {
    return ResponseFieldMarshaller { writer ->
      StarshipFragmentImpl_ResponseAdapter.toResponse(writer, this)
    }
  }

  /**
   * A connection to a list of items.
   */
  data class PilotConnection(
    /**
     * A list of edges.
     */
    override val edges: List<Edge?>?
  ) : StarshipFragment.PilotConnection {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        StarshipFragmentImpl_ResponseAdapter.PilotConnection.toResponse(writer, this)
      }
    }

    /**
     * An edge in a connection.
     */
    data class Edge(
      /**
       * The item at the end of the edge
       */
      override val node: Node?
    ) : StarshipFragment.PilotConnection.Edge {
      override fun marshaller(): ResponseFieldMarshaller {
        return ResponseFieldMarshaller { writer ->
          StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.toResponse(writer, this)
        }
      }

      /**
       * An individual person or character within the Star Wars universe.
       */
      interface Node : StarshipFragment.PilotConnection.Edge.Node {
        override val __typename: String

        fun asPerson(): Person? = this as? Person

        fun asPilotFragment(): PilotFragment? = this as? PilotFragment

        override fun marshaller(): ResponseFieldMarshaller

        interface Person : StarshipFragment.PilotConnection.Edge.Node,
            StarshipFragment.PilotConnection.Edge.Node.Person, PilotFragment, Node {
          override val __typename: String

          /**
           * The name of this person.
           */
          override val name: String?

          /**
           * A planet that this person was born on or inhabits.
           */
          override val homeworld: Homeworld?

          override fun marshaller(): ResponseFieldMarshaller

          /**
           * A large mass, planet or planetoid in the Star Wars Universe, at the time of
           * 0 ABY.
           */
          interface Homeworld : StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld,
              PilotFragment.Homeworld {
            override val __typename: String

            override fun marshaller(): ResponseFieldMarshaller

            interface Planet : StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld,
                StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld.Planet, PlanetFragment,
                PilotFragment.Homeworld.Planet, Homeworld {
              override val __typename: String

              /**
               * The name of this planet.
               */
              override val name: String?

              override fun marshaller(): ResponseFieldMarshaller
            }
          }
        }

        data class PersonNode(
          override val __typename: String = "Person",
          /**
           * The name of this person.
           */
          override val name: String?,
          /**
           * A planet that this person was born on or inhabits.
           */
          override val homeworld: Homeworld?
        ) : StarshipFragment.PilotConnection.Edge.Node,
            StarshipFragment.PilotConnection.Edge.Node.Person, PilotFragment, Node, Person {
          override fun marshaller(): ResponseFieldMarshaller {
            return ResponseFieldMarshaller { writer ->
              StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.Node.PersonNode.toResponse(writer, this)
            }
          }

          /**
           * A large mass, planet or planetoid in the Star Wars Universe, at the time of
           * 0 ABY.
           */
          interface Homeworld : StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld,
              PilotFragment.Homeworld, Person.Homeworld {
            override val __typename: String

            fun asPlanet(): Planet? = this as? Planet

            fun asPlanetFragment(): PlanetFragment? = this as? PlanetFragment

            override fun marshaller(): ResponseFieldMarshaller

            interface Planet : StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld,
                StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld.Planet, PlanetFragment,
                PilotFragment.Homeworld.Planet, Person.Homeworld, Person.Homeworld.Planet, Homeworld
                {
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
            ) : StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld,
                StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld.Planet, PlanetFragment,
                Person.Homeworld, Person.Homeworld.Planet, Homeworld {
              override fun marshaller(): ResponseFieldMarshaller {
                return ResponseFieldMarshaller { writer ->
                  StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.Node.PersonNode.Homeworld.PlanetHomeworld.toResponse(writer, this)
                }
              }
            }

            /**
             * A large mass, planet or planetoid in the Star Wars Universe, at the time of
             * 0 ABY.
             */
            data class OtherHomeworld(
              override val __typename: String = "Planet"
            ) : StarshipFragment.PilotConnection.Edge.Node.Person.Homeworld,
                PilotFragment.Homeworld, Person.Homeworld, Homeworld {
              override fun marshaller(): ResponseFieldMarshaller {
                return ResponseFieldMarshaller { writer ->
                  StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.Node.PersonNode.Homeworld.OtherHomeworld.toResponse(writer, this)
                }
              }
            }
          }
        }

        /**
         * An individual person or character within the Star Wars universe.
         */
        data class OtherNode(
          override val __typename: String = "Person"
        ) : StarshipFragment.PilotConnection.Edge.Node, Node {
          override fun marshaller(): ResponseFieldMarshaller {
            return ResponseFieldMarshaller { writer ->
              StarshipFragmentImpl_ResponseAdapter.PilotConnection.Edge.Node.OtherNode.toResponse(writer, this)
            }
          }
        }
      }
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment starshipFragment on Starship {
        |  __typename
        |  id
        |  name
        |  pilotConnection {
        |    edges {
        |      node {
        |        __typename
        |        ...pilotFragment
        |      }
        |    }
        |  }
        |}
        """.trimMargin()
  }
}
