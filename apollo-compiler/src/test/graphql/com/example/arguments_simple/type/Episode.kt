// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.arguments_simple.type

import kotlin.Deprecated
import kotlin.String

/**
 * The episodes in the Star Wars trilogy
 */
enum class Episode(val rawValue: String) {
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

    companion object {
        fun safeValueOf(rawValue: String): Episode = values().find { it.rawValue == rawValue } ?:
                UNKNOWN__
    }
}
