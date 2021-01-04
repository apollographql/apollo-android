// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.simple_fragment

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.example.simple_fragment.adapter.TestQuery_ResponseAdapter
import com.example.simple_fragment.fragment.HeroDetail
import com.example.simple_fragment.fragment.HumanDetail
import kotlin.String
import kotlin.Suppress

/**
 * Demonstration of Java / Kotlin docs generation
 * for both query and fragments
 */
@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
internal class TestQuery : Query<TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): OperationName = OPERATION_NAME

  override fun responseFieldMapper(): ResponseFieldMapper<Data> {
    return ResponseFieldMapper { reader ->
      TestQuery_ResponseAdapter.fromResponse(reader)
    }
  }

  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Data.toResponse(writer, this)
      }
    }

    /**
     * A character from the Star Wars universe
     */
    interface Hero {
      val __typename: String

      fun marshaller(): ResponseFieldMarshaller

      interface Character : Hero, HeroDetail {
        override val __typename: String

        override fun marshaller(): ResponseFieldMarshaller

        interface Human : Character, HeroDetail.Human {
          override val __typename: String

          /**
           * What this human calls themselves
           */
          override val name: String

          override fun marshaller(): ResponseFieldMarshaller
        }
      }

      interface Human : Hero, HumanDetail {
        override val __typename: String

        /**
         * What this human calls themselves
         */
        override val name: String

        override fun marshaller(): ResponseFieldMarshaller
      }

      data class CharacterHero(
        override val __typename: String
      ) : Hero, Character, HeroDetail {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.CharacterHero.toResponse(writer, this)
          }
        }
      }

      interface CharacterHumanHero : Hero, Character, HeroDetail, Human, HumanDetail {
        override val __typename: String

        /**
         * What this human calls themselves
         */
        override val name: String

        override fun marshaller(): ResponseFieldMarshaller

        interface Human : Character, Character.Human, HeroDetail.Human, CharacterHumanHero {
          override val __typename: String

          /**
           * What this human calls themselves
           */
          override val name: String

          override fun marshaller(): ResponseFieldMarshaller
        }

        data class HumanCharacterHumanHero(
          override val __typename: String,
          /**
           * What this human calls themselves
           */
          override val name: String
        ) : Character, Character.Human, HeroDetail.Human, CharacterHumanHero, Human {
          override fun marshaller(): ResponseFieldMarshaller {
            return ResponseFieldMarshaller { writer ->
              TestQuery_ResponseAdapter.Data.Hero.CharacterHumanHero.HumanCharacterHumanHero.toResponse(writer, this)
            }
          }
        }

        data class OtherCharacterHumanHero(
          override val __typename: String,
          /**
           * What this human calls themselves
           */
          override val name: String
        ) : Hero, Character, HeroDetail, Hero.Human, HumanDetail, CharacterHumanHero {
          override fun marshaller(): ResponseFieldMarshaller {
            return ResponseFieldMarshaller { writer ->
              TestQuery_ResponseAdapter.Data.Hero.CharacterHumanHero.OtherCharacterHumanHero.toResponse(writer, this)
            }
          }
        }

        companion object {
          fun CharacterHumanHero.asCharacter(): Character? = this as? Character

          fun CharacterHumanHero.asHuman(): Human? = this as? Human
        }
      }

      data class OtherHero(
        override val __typename: String
      ) : Hero {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.OtherHero.toResponse(writer, this)
          }
        }
      }

      companion object {
        fun Hero.asCharacter(): Character? = this as? Character

        fun Hero.heroDetails(): HeroDetail? = this as? HeroDetail

        fun Hero.asHuman(): Human? = this as? Human

        fun Hero.humanDetails(): HumanDetail? = this as? HumanDetail
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "6664b1abfa01e39c63ec2cbb5c6a25012b3fdf7b1064a1ff1172018c0e309828"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  hero {
          |    __typename
          |    ...HeroDetails
          |    ...HumanDetails
          |  }
          |}
          |fragment HeroDetails on Character {
          |  __typename
          |  ...HumanDetails
          |}
          |fragment HumanDetails on Human {
          |  __typename
          |  name
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String {
        return "TestQuery"
      }
    }
  }
}
