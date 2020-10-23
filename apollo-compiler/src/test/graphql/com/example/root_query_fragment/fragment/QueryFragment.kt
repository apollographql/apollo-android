// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import kotlin.String
import kotlin.Suppress

/**
 * The query type, represents all of the entry points into our object graph
 */
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
    val __typename: String

    /**
     * The name of the character
     */
    val name: String

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A character from the Star Wars universe
   */
  data class Hero1(
    override val __typename: String = "Character",
    /**
     * The name of the character
     */
    override val name: String
  ) : Hero {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        QueryFragment_ResponseAdapter.Hero1_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class DefaultImpl(
    override val __typename: String = "Query",
    override val hero: Hero1?
  ) : QueryFragment {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        QueryFragment_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment QueryFragment on Query {
        |  __typename
        |  hero {
        |    __typename
        |    name
        |  }
        |}
        """.trimMargin()

    operator fun invoke(reader: ResponseReader): QueryFragment {
      return QueryFragment_ResponseAdapter.fromResponse(reader)
    }

    fun Mapper(): ResponseFieldMapper<QueryFragment> {
      return ResponseFieldMapper { reader ->
        QueryFragment_ResponseAdapter.fromResponse(reader)
      }
    }
  }
}
