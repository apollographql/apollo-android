// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
interface Starship : GraphqlFragment {
  val __typename: String

  /**
   * The name of the starship
   */
  val name: String

  data class StarshipImpl(
    override val __typename: String = "Starship",
    /**
     * The name of the starship
     */
    override val name: String
  ) : Starship {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        Starship_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment Starship on Starship {
        |  __typename
        |  name
        |}
        """.trimMargin()

    operator fun invoke(reader: ResponseReader): Starship {
      return Starship_ResponseAdapter.fromResponse(reader)
    }

    fun Mapper(): ResponseFieldMapper<Starship> {
      return ResponseFieldMapper { reader ->
        Starship_ResponseAdapter.fromResponse(reader)
      }
    }
  }
}
