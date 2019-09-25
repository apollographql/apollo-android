// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.fragment_used_twice.type.CustomType
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class CharacterDetails(
  /**
   * The name of the character
   */
  val name: String,
  /**
   * The date character was born.
   */
  val birthDate: Any
) : GraphqlFragment {
  override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
    it.writeString(RESPONSE_FIELDS[0], name)
    it.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, birthDate)
  }

  companion object {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forCustomType("birthDate", "birthDate", null, false, CustomType.DATE, null)
        )

    val FRAGMENT_DEFINITION: String = """
        |fragment CharacterDetails on Character {
        |  name
        |  birthDate
        |}
        """.trimMargin()

    val POSSIBLE_TYPES: Array<String> = arrayOf("Human", "Droid")

    operator fun invoke(reader: ResponseReader): CharacterDetails {
      val name = reader.readString(RESPONSE_FIELDS[0])
      val birthDate = reader.readCustomType<Any>(RESPONSE_FIELDS[1] as
          ResponseField.CustomTypeField)
      return CharacterDetails(
        name = name,
        birthDate = birthDate
      )
    }
  }
}
