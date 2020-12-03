// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import com.example.fragment_used_twice.adapter.TestQuery_ResponseAdapter
import com.example.fragment_used_twice.fragment.CharacterDetail
import com.example.fragment_used_twice.fragment.HeroDetail
import com.example.fragment_used_twice.fragment.HumanDetail
import kotlin.Any
import kotlin.Boolean
import kotlin.String
import kotlin.Suppress
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): OperationName = OPERATION_NAME

  override fun responseFieldMapper(): ResponseFieldMapper<Data> {
    return ResponseFieldMapper { reader ->
      TestQuery_ResponseAdapter.fromResponse(reader)
    }
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters):
      Response<Data> {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)
  }

  @Throws(IOException::class)
  override fun parse(byteString: ByteString, scalarTypeAdapters: ScalarTypeAdapters):
      Response<Data> {
    return parse(Buffer().write(byteString), scalarTypeAdapters)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> {
    return parse(source, DEFAULT)
  }

  @Throws(IOException::class)
  override fun parse(byteString: ByteString): Response<Data> {
    return parse(byteString, DEFAULT)
  }

  override fun composeRequestBody(scalarTypeAdapters: ScalarTypeAdapters): ByteString {
    return OperationRequestBodyComposer.compose(
      operation = this,
      autoPersistQueries = false,
      withQueryDocument = true,
      scalarTypeAdapters = scalarTypeAdapters
    )
  }

  override fun composeRequestBody(): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = DEFAULT
  )

  override fun composeRequestBody(
    autoPersistQueries: Boolean,
    withQueryDocument: Boolean,
    scalarTypeAdapters: ScalarTypeAdapters
  ): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = autoPersistQueries,
    withQueryDocument = withQueryDocument,
    scalarTypeAdapters = scalarTypeAdapters
  )

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

      fun asCharacter(): Character? = this as? Character

      fun asHuman(): Human? = this as? Human

      fun asHeroDetail(): HeroDetail? = this as? HeroDetail

      fun asCharacterDetail(): CharacterDetail? = this as? CharacterDetail

      fun asHumanDetail(): HumanDetail? = this as? HumanDetail

      fun marshaller(): ResponseFieldMarshaller

      interface Character : Hero, HeroDetail, HeroDetail.Character, CharacterDetail,
          HumanDetail.Character {
        override val __typename: String

        /**
         * The name of the character
         */
        override val name: String

        /**
         * The date character was born.
         */
        override val birthDate: Any

        override fun marshaller(): ResponseFieldMarshaller
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
        override val __typename: String = "Droid",
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * The date character was born.
         */
        override val birthDate: Any
      ) : Hero, Character, HeroDetail, HeroDetail.Character, CharacterDetail, HumanDetail.Character
          {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.CharacterHero.toResponse(writer, this)
          }
        }
      }

      data class CharacterHumanHero(
        override val __typename: String = "Human",
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * The date character was born.
         */
        override val birthDate: Any
      ) : Hero, Character, HeroDetail, HeroDetail.Character, CharacterDetail, Human, HumanDetail,
          HumanDetail.Character {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.CharacterHumanHero.toResponse(writer, this)
          }
        }
      }

      /**
       * A character from the Star Wars universe
       */
      data class OtherHero(
        override val __typename: String = "Character"
      ) : Hero {
        override fun marshaller(): ResponseFieldMarshaller {
          return ResponseFieldMarshaller { writer ->
            TestQuery_ResponseAdapter.Data.Hero.OtherHero.toResponse(writer, this)
          }
        }
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "ef8624a3948f8b26cb3d63332d7d35e84a12896b20d91ecfcdb29c4825e8ff82"

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
          |  name
          |  ...CharacterDetails
          |}
          |fragment CharacterDetails on Character {
          |  __typename
          |  name
          |  birthDate
          |}
          |fragment HumanDetails on Human {
          |  __typename
          |  name
          |  ...CharacterDetails
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
