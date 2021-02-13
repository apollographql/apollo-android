// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_with_review.type

import com.apollographql.apollo.api.EnumValue
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import kotlin.Deprecated
import kotlin.String

/**
 * The episodes in the Star Wars trilogy
 */
enum class Episode(
  override val rawValue: String
) : EnumValue {
  /**
   * Star Wars Episode IV: A New Hope, released in 1977.
   */
  NEWHOPE("NEWHOPE"),

  /**
   * Star Wars Episode V: The Empire Strikes Back, released in 1980.
   */
  EMPIRE("EMPIRE"),

  /**
   * Star Wars Episode VI: Return of the Jedi, released in 1983.
   */
  JEDI("JEDI"),

  /**
   * Test deprecated enum value
   */
  @Deprecated(message = "For test purpose only")
  DEPRECATED("DEPRECATED"),

  /**
   * Test java reserved word
   */
  @Deprecated(message = "For test purpose only")
  NEW("new"),

  /**
   * Auto generated constant for unknown enum values
   */
  UNKNOWN__("UNKNOWN__");
}

object Episode_ResponseAdapter : ResponseAdapter<Episode> {
  override fun fromResponse(reader: JsonReader): Episode {
    val rawValue = reader.nextString()!!
    return when(rawValue) {
      "NEWHOPE" -> Episode.NEWHOPE
      "EMPIRE" -> Episode.EMPIRE
      "JEDI" -> Episode.JEDI
      "DEPRECATED" -> Episode.DEPRECATED
      "new" -> Episode.NEW
      else -> Episode.UNKNOWN__
    }
  }

  override fun toResponse(writer: JsonWriter, value: Episode) {
    writer.value(value.rawValue)
  }
}
