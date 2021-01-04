// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_with_inline_fragment

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.fragment_with_inline_fragment.adapter.TestQuery_ResponseAdapter
import com.example.fragment_with_inline_fragment.fragment.HeroDetail
import com.example.fragment_with_inline_fragment.type.Episode
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): OperationName = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = TestQuery_ResponseAdapter
  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    /**
     * A character from the Star Wars universe
     */
    interface Hero {
      val __typename: String

      /**
       * The name of the character
       */
      val name: String

      /**
       * The movies this character appears in
       */
      val appearsIn: List<Episode?>

      interface Character : Hero, HeroDetail {
        override val __typename: String

        /**
         * The name of the character
         */
        override val name: String

        /**
         * The movies this character appears in
         */
        override val appearsIn: List<Episode?>

        /**
         * The friends of the character exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection

        /**
         * A connection object for a character's friends
         */
        interface FriendsConnection : HeroDetail.FriendsConnection {
          /**
           * The total number of friends
           */
          override val totalCount: Int?

          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edge?>?

          /**
           * An edge object for a character's friends
           */
          interface Edge : HeroDetail.FriendsConnection.Edge {
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?

            /**
             * A character from the Star Wars universe
             */
            interface Node : HeroDetail.FriendsConnection.Edge.Node {
              /**
               * The name of the character
               */
              override val name: String
            }
          }
        }

        interface Droid : Character, HeroDetail.Droid {
          override val __typename: String

          /**
           * The name of the character
           */
          override val name: String

          /**
           * The movies this character appears in
           */
          override val appearsIn: List<Episode?>

          /**
           * The friends of the character exposed as a connection with edges
           */
          override val friendsConnection: FriendsConnection

          /**
           * A connection object for a character's friends
           */
          interface FriendsConnection : Character.FriendsConnection, HeroDetail.FriendsConnection,
              HeroDetail.Droid.FriendsConnection {
            /**
             * The total number of friends
             */
            override val totalCount: Int?

            /**
             * The edges for each of the character's friends.
             */
            override val edges: List<Edge?>?

            /**
             * An edge object for a character's friends
             */
            interface Edge : Character.FriendsConnection.Edge, HeroDetail.FriendsConnection.Edge,
                HeroDetail.Droid.FriendsConnection.Edge {
              /**
               * The character represented by this friendship edge
               */
              override val node: Node?

              /**
               * A character from the Star Wars universe
               */
              interface Node : Character.FriendsConnection.Edge.Node,
                  HeroDetail.FriendsConnection.Edge.Node,
                  HeroDetail.Droid.FriendsConnection.Edge.Node {
                /**
                 * The name of the character
                 */
                override val name: String
              }
            }
          }

          interface Droid : Character.Droid, HeroDetail.Droid.Droid {
            override val __typename: String

            /**
             * The name of the character
             */
            override val name: String

            /**
             * The movies this character appears in
             */
            override val appearsIn: List<Episode?>

            /**
             * The friends of the character exposed as a connection with edges
             */
            override val friendsConnection: FriendsConnection

            /**
             * This droid's primary function
             */
            override val primaryFunction: String?

            /**
             * A connection object for a character's friends
             */
            interface FriendsConnection : Character.FriendsConnection, HeroDetail.FriendsConnection,
                HeroDetail.Droid.FriendsConnection, Character.Droid.FriendsConnection,
                HeroDetail.Droid.Droid.FriendsConnection {
              /**
               * The total number of friends
               */
              override val totalCount: Int?

              /**
               * The edges for each of the character's friends.
               */
              override val edges: List<Edge?>?

              /**
               * An edge object for a character's friends
               */
              interface Edge : Character.FriendsConnection.Edge, HeroDetail.FriendsConnection.Edge,
                  HeroDetail.Droid.FriendsConnection.Edge, Character.Droid.FriendsConnection.Edge,
                  HeroDetail.Droid.Droid.FriendsConnection.Edge {
                /**
                 * The character represented by this friendship edge
                 */
                override val node: Node?

                /**
                 * A character from the Star Wars universe
                 */
                interface Node : Character.FriendsConnection.Edge.Node,
                    HeroDetail.FriendsConnection.Edge.Node,
                    HeroDetail.Droid.FriendsConnection.Edge.Node,
                    Character.Droid.FriendsConnection.Edge.Node,
                    HeroDetail.Droid.Droid.FriendsConnection.Edge.Node {
                  /**
                   * The name of the character
                   */
                  override val name: String
                }
              }
            }
          }
        }

        interface Human : Character, HeroDetail.Human {
          override val __typename: String

          /**
           * The name of the character
           */
          override val name: String

          /**
           * The movies this character appears in
           */
          override val appearsIn: List<Episode?>

          /**
           * The friends of the character exposed as a connection with edges
           */
          override val friendsConnection: FriendsConnection

          /**
           * A connection object for a character's friends
           */
          interface FriendsConnection : Character.FriendsConnection, HeroDetail.FriendsConnection,
              HeroDetail.Human.FriendsConnection {
            /**
             * The total number of friends
             */
            override val totalCount: Int?

            /**
             * The edges for each of the character's friends.
             */
            override val edges: List<Edge?>?

            /**
             * An edge object for a character's friends
             */
            interface Edge : Character.FriendsConnection.Edge, HeroDetail.FriendsConnection.Edge,
                HeroDetail.Human.FriendsConnection.Edge {
              /**
               * The character represented by this friendship edge
               */
              override val node: Node?

              /**
               * A character from the Star Wars universe
               */
              interface Node : Character.FriendsConnection.Edge.Node,
                  HeroDetail.FriendsConnection.Edge.Node,
                  HeroDetail.Human.FriendsConnection.Edge.Node {
                /**
                 * The name of the character
                 */
                override val name: String
              }
            }
          }
        }
      }

      data class CharacterDroidHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * The movies this character appears in
         */
        override val appearsIn: List<Episode?>,
        /**
         * The friends of the character exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection,
        /**
         * This droid's primary function
         */
        override val primaryFunction: String?
      ) : Hero, Character, HeroDetail, Character.Droid, HeroDetail.Droid, Character.Droid.Droid,
          HeroDetail.Droid.Droid {
        /**
         * A connection object for a character's friends
         */
        data class FriendsConnection(
          /**
           * The total number of friends
           */
          override val totalCount: Int?,
          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edge?>?
        ) : Character.FriendsConnection, HeroDetail.FriendsConnection,
            HeroDetail.Droid.FriendsConnection, Character.Droid.FriendsConnection,
            HeroDetail.Droid.Droid.FriendsConnection, Character.Droid.Droid.FriendsConnection {
          /**
           * An edge object for a character's friends
           */
          data class Edge(
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?
          ) : Character.FriendsConnection.Edge, HeroDetail.FriendsConnection.Edge,
              HeroDetail.Droid.FriendsConnection.Edge, Character.Droid.FriendsConnection.Edge,
              HeroDetail.Droid.Droid.FriendsConnection.Edge,
              Character.Droid.Droid.FriendsConnection.Edge {
            /**
             * A character from the Star Wars universe
             */
            data class Node(
              /**
               * The name of the character
               */
              override val name: String
            ) : Character.FriendsConnection.Edge.Node, HeroDetail.FriendsConnection.Edge.Node,
                HeroDetail.Droid.FriendsConnection.Edge.Node,
                Character.Droid.FriendsConnection.Edge.Node,
                HeroDetail.Droid.Droid.FriendsConnection.Edge.Node,
                Character.Droid.Droid.FriendsConnection.Edge.Node
          }
        }
      }

      data class CharacterHumanHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * The movies this character appears in
         */
        override val appearsIn: List<Episode?>,
        /**
         * The friends of the character exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection
      ) : Hero, Character, HeroDetail, Character.Human, HeroDetail.Human {
        /**
         * A connection object for a character's friends
         */
        data class FriendsConnection(
          /**
           * The total number of friends
           */
          override val totalCount: Int?,
          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edge?>?
        ) : Character.FriendsConnection, HeroDetail.FriendsConnection,
            HeroDetail.Human.FriendsConnection, Character.Human.FriendsConnection {
          /**
           * An edge object for a character's friends
           */
          data class Edge(
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?
          ) : Character.FriendsConnection.Edge, HeroDetail.FriendsConnection.Edge,
              HeroDetail.Human.FriendsConnection.Edge, Character.Human.FriendsConnection.Edge {
            /**
             * A character from the Star Wars universe
             */
            data class Node(
              /**
               * The name of the character
               */
              override val name: String
            ) : Character.FriendsConnection.Edge.Node, HeroDetail.FriendsConnection.Edge.Node,
                HeroDetail.Human.FriendsConnection.Edge.Node,
                Character.Human.FriendsConnection.Edge.Node
          }
        }
      }

      data class OtherHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * The movies this character appears in
         */
        override val appearsIn: List<Episode?>
      ) : Hero

      companion object {
        fun Hero.asCharacter(): Character? = this as? Character

        fun Hero.heroDetails(): HeroDetail? = this as? HeroDetail
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "e01fcc7b255552f79419f653def959b6a7ab9bffd519c57e826d91ffc2c7fb1f"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  hero {
          |    __typename
          |    name
          |    ...HeroDetails
          |    appearsIn
          |  }
          |}
          |fragment HeroDetails on Character {
          |  __typename
          |  ...HumanDetails
          |  ... on Droid {
          |    __typename
          |    ...DroidDetails
          |  }
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
          |fragment HumanDetails on Human {
          |  __typename
          |  name
          |}
          |fragment DroidDetails on Droid {
          |  __typename
          |  name
          |  primaryFunction
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String {
        return "TestQuery"
      }
    }
  }
}
