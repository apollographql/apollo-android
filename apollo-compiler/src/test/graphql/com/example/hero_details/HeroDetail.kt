// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_details

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import com.example.hero_details.adapter.HeroDetail_ResponseAdapter
import com.example.hero_details.type.Hero_type
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HeroDetail : Query<HeroDetail.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): OperationName = OPERATION_NAME

  override fun responseFieldMapper(): ResponseFieldMapper<Data> {
    return ResponseFieldMapper { reader ->
      HeroDetail_ResponseAdapter.fromResponse(reader)
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
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        HeroDetail_ResponseAdapter.Data.toResponse(writer, this)
      }
    }

    /**
     * A character from the Star Wars universe
     */
    data class Hero(
      /**
       * Hero type
       */
      val type: Hero_type,
      /**
       * The name of the character
       */
      val name: String,
      /**
       * The friends of the character exposed as a connection with edges
       */
      val friendsConnection: FriendsConnection
    ) {
      fun marshaller(): ResponseFieldMarshaller {
        return ResponseFieldMarshaller { writer ->
          HeroDetail_ResponseAdapter.Data.Hero.toResponse(writer, this)
        }
      }

      /**
       * A connection object for a character's friends
       */
      data class FriendsConnection(
        /**
         * The total number of friends
         */
        val totalCount: Int?,
        /**
         * The edges for each of the character's friends.
         */
        val edges: List<Edge?>?
      ) {
        fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            HeroDetail_ResponseAdapter.Data.Hero.FriendsConnection.toResponse(writer, this)
          }
        }

        fun edgesFilterNotNull(): List<Edge>? = edges?.filterNotNull()

        /**
         * An edge object for a character's friends
         */
        data class Edge(
          /**
           * The character represented by this friendship edge
           */
          val node: Node?
        ) {
          fun marshaller(): ResponseFieldMarshaller {
            return ResponseFieldMarshaller { writer ->
              HeroDetail_ResponseAdapter.Data.Hero.FriendsConnection.Edge.toResponse(writer, this)
            }
          }

          /**
           * A character from the Star Wars universe
           */
          data class Node(
            /**
             * The name of the character
             */
            val name: String
          ) {
            fun marshaller(): ResponseFieldMarshaller {
              return ResponseFieldMarshaller { writer ->
                HeroDetail_ResponseAdapter.Data.Hero.FriendsConnection.Edge.Node.toResponse(writer, this)
              }
            }
          }
        }
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "8f6f2c3852a89d45ea7eaa08a249906a1fb8f1733c4f2326d0fb8fb75c4af1e3"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query HeroDetails {
          |  hero {
          |    type
          |    name
          |    friendsConnection {
          |      totalCount
          |      edges {
          |        node {
          |          name
          |        }
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String {
        return "HeroDetails"
      }
    }
  }
}
