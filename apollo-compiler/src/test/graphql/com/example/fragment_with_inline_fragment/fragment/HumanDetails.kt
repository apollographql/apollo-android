// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_with_inline_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import kotlin.Array
import kotlin.String
import kotlin.Suppress

/**
 * A humanoid creature from the Star Wars universe
 */
@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName", "NOTHING_TO_INLINE")
interface HumanDetails : GraphqlFragment {
  val __typename: String

  /**
   * What this human calls themselves
   */
  val name: String

  /**
   * A humanoid creature from the Star Wars universe
   */
  data class DefaultImpl(
    override val __typename: String = "Human",
    /**
     * What this human calls themselves
     */
    override val name: String
  ) : HumanDetails {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@DefaultImpl.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@DefaultImpl.name)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): DefaultImpl {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              else -> break
            }
          }
          DefaultImpl(
            __typename = __typename!!,
            name = name!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<DefaultImpl> = ResponseFieldMapper { invoke(it) }
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment HumanDetails on Human {
        |  __typename
        |  name
        |}
        """.trimMargin()

    inline operator fun invoke(reader: ResponseReader, __typename: String? = null): HumanDetails =
        DefaultImpl(reader, __typename)
  }
}
