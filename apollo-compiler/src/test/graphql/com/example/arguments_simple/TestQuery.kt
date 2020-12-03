// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.arguments_simple

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import com.example.arguments_simple.adapter.TestQuery_ResponseAdapter
import com.example.arguments_simple.fragment.HeroDetail
import com.example.arguments_simple.type.Episode
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.jvm.Transient
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
data class TestQuery(
  val episode: Input<Episode> = Input.absent(),
  val includeName: Boolean,
  val friendsCount: Int,
  val listOfListOfStringArgs: List<List<String?>>
) : Query<TestQuery.Data, Operation.Variables> {
  @Transient
  private val variables: Operation.Variables = object : Operation.Variables() {
    override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
      if (this@TestQuery.episode.defined) {
        this["episode"] = this@TestQuery.episode.value
      }
      this["IncludeName"] = this@TestQuery.includeName
      this["friendsCount"] = this@TestQuery.friendsCount
      this["listOfListOfStringArgs"] = this@TestQuery.listOfListOfStringArgs
    }

    override fun marshaller(): InputFieldMarshaller {
      return InputFieldMarshaller.invoke { writer ->
        if (this@TestQuery.episode.defined) {
          writer.writeString("episode", this@TestQuery.episode.value?.rawValue)
        }
        writer.writeBoolean("IncludeName", this@TestQuery.includeName)
        writer.writeInt("friendsCount", this@TestQuery.friendsCount)
        writer.writeList("listOfListOfStringArgs") { listItemWriter ->
          this@TestQuery.listOfListOfStringArgs.forEach { value ->
            listItemWriter.writeList { listItemWriter ->
              value.forEach { value ->
                listItemWriter.writeString(value)
              }
            }
          }
        }
      }
    }
  }

  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = variables

  override fun name(): OperationName = OPERATION_NAME

  override fun responseFieldMapper(): ResponseFieldMapper<Data> {
    return ResponseFieldMapper { reader ->
      TestQuery_ResponseAdapter.fromResponse(reader)
    }
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters):
      Response<Data> {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)
  }

  @Throws(IOException::class)
  override fun parse(byteString: ByteString, scalarTypeAdapters: ScalarTypeAdapters):
      Response<Data> {
    return parse(Buffer().write(byteString), scalarTypeAdapters)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> {
    return parse(source, DEFAULT)
  }

  @Throws(IOException::class)
  override fun parse(byteString: ByteString): Response<Data> {
    return parse(byteString, DEFAULT)
  }

  override fun composeRequestBody(scalarTypeAdapters: ScalarTypeAdapters): ByteString {
    return OperationRequestBodyComposer.compose(
      operation = this,
      autoPersistQueries = false,
      withQueryDocument = true,
      scalarTypeAdapters = scalarTypeAdapters
    )
  }

  override fun composeRequestBody(): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = DEFAULT
  )

  override fun composeRequestBody(
    autoPersistQueries: Boolean,
    withQueryDocument: Boolean,
    scalarTypeAdapters: ScalarTypeAdapters
  ): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = autoPersistQueries,
    withQueryDocument = withQueryDocument,
    scalarTypeAdapters = scalarTypeAdapters
  )

  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?,
    val heroWithReview: HeroWithReview?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Data.toResponse(writer, this)
      }
    }

    /**
     * A character from the Star Wars universe
     */
    interface Hero {
      val __typename: String

      /**
       * The name of the character
       */
      val name: String?

      fun asCharacter(): Character? = this as? Character

      fun asHeroDetail(): HeroDetail? = this as? HeroDetail

      fun marshaller(): ResponseFieldMarshaller

      interface Character : Hero, HeroDetail {
        override val __typename: String

        /**
         * The name of the character
         */
        override val name: String?

        /**
         * The friends of the character exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection

        override fun marshaller(): ResponseFieldMarshaller

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

          override fun marshaller(): ResponseFieldMarshaller

          /**
           * An edge object for a character's friends
           */
          interface Edge : HeroDetail.FriendsConnection.Edge {
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?

            override fun marshaller(): ResponseFieldMarshaller

            /**
             * A character from the Star Wars universe
             */
            interface Node : HeroDetail.FriendsConnection.Edge.Node {
              /**
               * The name of the character
               */
              override val name: String?

              override fun marshaller(): ResponseFieldMarshaller
            }
          }
        }
      }

      data class CharacterHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String?,
        /**
         * The friends of the character exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection
      ) : Hero, Character, HeroDetail {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.CharacterHero.toResponse(writer, this)
          }
        }

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
        ) : Character.FriendsConnection, HeroDetail.FriendsConnection {
          override fun marshaller(): ResponseFieldMarshaller {
            return ResponseFieldMarshaller { writer ->
              TestQuery_ResponseAdapter.Data.Hero.CharacterHero.FriendsConnection.toResponse(writer, this)
            }
          }

          /**
           * An edge object for a character's friends
           */
          data class Edge(
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?
          ) : Character.FriendsConnection.Edge, HeroDetail.FriendsConnection.Edge {
            override fun marshaller(): ResponseFieldMarshaller {
              return ResponseFieldMarshaller { writer ->
                TestQuery_ResponseAdapter.Data.Hero.CharacterHero.FriendsConnection.Edge.toResponse(writer, this)
              }
            }

            /**
             * A character from the Star Wars universe
             */
            data class Node(
              /**
               * The name of the character
               */
              override val name: String?
            ) : Character.FriendsConnection.Edge.Node, HeroDetail.FriendsConnection.Edge.Node {
              override fun marshaller(): ResponseFieldMarshaller {
                return ResponseFieldMarshaller { writer ->
                  TestQuery_ResponseAdapter.Data.Hero.CharacterHero.FriendsConnection.Edge.Node.toResponse(writer, this)
                }
              }
            }
          }
        }
      }

      /**
       * A character from the Star Wars universe
       */
      data class OtherHero(
        override val __typename: String = "Character",
        /**
         * The name of the character
         */
        override val name: String?
      ) : Hero {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.OtherHero.toResponse(writer, this)
          }
        }
      }
    }

    /**
     * A humanoid creature from the Star Wars universe
     */
    data class HeroWithReview(
      /**
       * What this human calls themselves
       */
      val name: String
    ) {
      fun marshaller(): ResponseFieldMarshaller {
        return ResponseFieldMarshaller { writer ->
          TestQuery_ResponseAdapter.Data.HeroWithReview.toResponse(writer, this)
        }
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "b4e56c478d8dd79496d9d5e474e09c5f71a0deda5180d8e28be697c37605ce9d"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery(${'$'}episode: Episode, ${'$'}IncludeName: Boolean!, ${'$'}friendsCount: Int!, ${'$'}listOfListOfStringArgs: [[String]!]!) {
          |  hero(episode: ${'$'}episode, listOfListOfStringArgs: ${'$'}listOfListOfStringArgs) {
          |    __typename
          |    name @include(if: ${'$'}IncludeName)
          |    ...HeroDetails
          |  }
          |  heroWithReview(episode: ${'$'}episode, review: {
          |    stars: 5
          |    favoriteColor: {
          |      red: 1
          |      blue: 1
          |    }
          |    
          |    listOfStringNonOptional: []
          |  }
          |  ) {
          |    name
          |  }
          |}
          |fragment HeroDetails on Character {
          |  __typename
          |  friendsConnection(first: ${'$'}friendsCount) {
          |    totalCount
          |    edges {
          |      node {
          |        name @include(if: ${'$'}IncludeName)
          |      }
          |    }
          |  }
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
