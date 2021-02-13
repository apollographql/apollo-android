// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_frgament_intersection.type

import com.apollographql.apollo.api.EnumValue
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import kotlin.String

enum class Race(
  override val rawValue: String
) : EnumValue {
  WOOKIE("WOOKIE"),

  GUMGAN("GUMGAN"),

  EWOK("EWOK"),

  /**
   * Auto generated constant for unknown enum values
   */
  UNKNOWN__("UNKNOWN__");
}

object Race_ResponseAdapter : ResponseAdapter<Race> {
  override fun fromResponse(reader: JsonReader): Race {
    val rawValue = reader.nextString()!!
    return when(rawValue) {
      "WOOKIE" -> Race.WOOKIE
      "GUMGAN" -> Race.GUMGAN
      "EWOK" -> Race.EWOK
      else -> Race.UNKNOWN__
    }
  }

  override fun toResponse(writer: JsonWriter, value: Race) {
    writer.value(value.rawValue)
  }
}
