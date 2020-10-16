// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_fragment_with_nested_fragments.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import kotlin.Array
import kotlin.String
import kotlin.Suppress

/**
 * The query type, represents all of the entry points into our object graph
 */
@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName", "NOTHING_TO_INLINE")
interface QueryFragment : GraphqlFragment {
  val __typename: String

  val hero: Hero?

  val droid: Droid?

  val human: Human?

  /**
   * A character from the Star Wars universe
   */
  interface Hero {
    val __typename: String

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  interface Droid {
    val __typename: String

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A humanoid creature from the Star Wars universe
   */
  interface Human {
    val __typename: String

    /**
     * What this human calls themselves
     */
    val name: String

    /**
     * The home planet of the human, or null if unknown
     */
    val homePlanet: String?

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
  ) : HeroFragment, Hero {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@Hero1.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@Hero1.name)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): Hero1 {
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
          Hero1(
            __typename = __typename!!,
            name = name!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Hero1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  data class Droid1(
    override val __typename: String = "Droid",
    /**
     * What others call this droid
     */
    override val name: String,
    /**
     * This droid's primary function
     */
    override val primaryFunction: String?
  ) : DroidFragment, Droid {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@Droid1.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@Droid1.name)
        writer.writeString(RESPONSE_FIELDS[2], this@Droid1.primaryFunction)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forString("primaryFunction", "primaryFunction", null, true, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): Droid1 {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var primaryFunction: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> primaryFunction = readString(RESPONSE_FIELDS[2])
              else -> break
            }
          }
          Droid1(
            __typename = __typename!!,
            name = name!!,
            primaryFunction = primaryFunction
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Droid1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A humanoid creature from the Star Wars universe
   */
  data class Human1(
    override val __typename: String = "Human",
    /**
     * What this human calls themselves
     */
    override val name: String,
    /**
     * The home planet of the human, or null if unknown
     */
    override val homePlanet: String?
  ) : Human {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@Human1.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@Human1.name)
        writer.writeString(RESPONSE_FIELDS[2], this@Human1.homePlanet)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forString("homePlanet", "homePlanet", null, true, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): Human1 {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var homePlanet: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> homePlanet = readString(RESPONSE_FIELDS[2])
              else -> break
            }
          }
          Human1(
            __typename = __typename!!,
            name = name!!,
            homePlanet = homePlanet
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Human1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class DefaultImpl(
    override val __typename: String = "Query",
    override val hero: Hero1?,
    override val droid: Droid1?,
    override val human: Human1?
  ) : QueryFragment {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@DefaultImpl.__typename)
        writer.writeObject(RESPONSE_FIELDS[1], this@DefaultImpl.hero?.marshaller())
        writer.writeObject(RESPONSE_FIELDS[2], this@DefaultImpl.droid?.marshaller())
        writer.writeObject(RESPONSE_FIELDS[3], this@DefaultImpl.human?.marshaller())
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forObject("hero", "hero", null, true, null),
        ResponseField.forObject("droid", "droid", mapOf<String, Any>(
          "id" to "1"), true, null),
        ResponseField.forObject("human", "human", mapOf<String, Any>(
          "id" to "1"), true, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): DefaultImpl {
        return reader.run {
          var __typename: String? = __typename
          var hero: Hero1? = null
          var droid: Droid1? = null
          var human: Human1? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> hero = readObject<Hero1>(RESPONSE_FIELDS[1]) { reader ->
                Hero1(reader)
              }
              2 -> droid = readObject<Droid1>(RESPONSE_FIELDS[2]) { reader ->
                Droid1(reader)
              }
              3 -> human = readObject<Human1>(RESPONSE_FIELDS[3]) { reader ->
                Human1(reader)
              }
              else -> break
            }
          }
          DefaultImpl(
            __typename = __typename!!,
            hero = hero,
            droid = droid,
            human = human
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<DefaultImpl> = ResponseFieldMapper { invoke(it) }
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment QueryFragment on Query {
        |  __typename
        |  hero {
        |    __typename
        |    ...heroFragment
        |  }
        |  droid(id: 1) {
        |    __typename
        |    ...droidFragment
        |  }
        |  human(id: 1) {
        |    __typename
        |    ... on Human {
        |      name
        |      homePlanet
        |    }
        |  }
        |}
        """.trimMargin()

    inline operator fun invoke(reader: ResponseReader, __typename: String? = null): QueryFragment =
        DefaultImpl(reader, __typename)
  }
}
