// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.union_fragment.type.CustomType
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class Character(
  /**
   * The ID of the character
   */
  val id: String,
  /**
   * The name of the character
   */
  val name: String
) : GraphqlFragment {
  override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
    it.writeCustom(RESPONSE_FIELDS[0] as ResponseField.CustomTypeField, id)
    it.writeString(RESPONSE_FIELDS[1], name)
  }

  companion object {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null),
        ResponseField.forString("name", "name", null, false, null)
        )

    val FRAGMENT_DEFINITION: String = """
        |fragment Character on Character {
        |  id
        |  name
        |}
        """.trimMargin()

    val POSSIBLE_TYPES: Array<String> = arrayOf("Human", "Droid")

    operator fun invoke(reader: ResponseReader): Character {
      val id = reader.readCustomType<String>(RESPONSE_FIELDS[0] as ResponseField.CustomTypeField)
      val name = reader.readString(RESPONSE_FIELDS[1])
      return Character(
        id = id,
        name = name
      )
    }
  }
}
