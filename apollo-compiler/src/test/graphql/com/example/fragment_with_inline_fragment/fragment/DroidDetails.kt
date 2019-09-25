// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_with_inline_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class DroidDetails(
  /**
   * What others call this droid
   */
  val name: String,
  /**
   * This droid's primary function
   */
  val primaryFunction: String?
) : GraphqlFragment {
  override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
    it.writeString(RESPONSE_FIELDS[0], name)
    it.writeString(RESPONSE_FIELDS[1], primaryFunction)
  }

  companion object {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forString("primaryFunction", "primaryFunction", null, true, null)
        )

    val FRAGMENT_DEFINITION: String = """
        |fragment DroidDetails on Droid {
        |  name
        |  primaryFunction
        |}
        """.trimMargin()

    val POSSIBLE_TYPES: Array<String> = arrayOf("Droid")

    operator fun invoke(reader: ResponseReader): DroidDetails {
      val name = reader.readString(RESPONSE_FIELDS[0])
      val primaryFunction = reader.readString(RESPONSE_FIELDS[1])
      return DroidDetails(
        name = name,
        primaryFunction = primaryFunction
      )
    }
  }
}
