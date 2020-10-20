// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_inline_fragments

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
import com.example.union_inline_fragments.type.Episode
import kotlin.Boolean
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
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
    return ResponseFieldMapper.invoke {
      TestQuery_ResponseAdapter.fromResponse(it)
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
   * A character from the Star Wars universe
   */
  data class Friend1(
    val __typename: String = "Character",
    /**
     * The movie this character first appears in
     */
    val firstAppearsIn: Episode
  ) {
    fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Friend1_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

  /**
   * A humanoid creature from the Star Wars universe
   */
  data class Human(
    override val __typename: String = "Human",
    /**
     * The home planet of the human, or null if unknown
     */
    val homePlanet: String?,
    /**
     * This human's friends, or an empty list if they have none
     */
    val friends: List<Friend1?>?,
    /**
     * The name of the character
     */
    override val name: String
  ) : Friend {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Human_ResponseAdapter.toResponse(writer, this)
      }
    }

    fun friendsFilterNotNull(): List<Friend1>? = friends?.filterNotNull()
  }

  /**
   * A character from the Star Wars universe
   */
  data class Friend2(
    val __typename: String = "Character",
    /**
     * The ID of the character
     */
    val id: String
  ) {
    fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Friend2_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  data class Droid(
    override val __typename: String = "Droid",
    /**
     * This droid's primary function
     */
    val primaryFunction: String?,
    /**
     * This droid's friends, or an empty list if they have none
     */
    val friends: List<Friend2?>?,
    /**
     * The name of the character
     */
    override val name: String
  ) : Friend {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Droid_ResponseAdapter.toResponse(writer, this)
      }
    }

    fun friendsFilterNotNull(): List<Friend2>? = friends?.filterNotNull()
  }

  /**
   * A character from the Star Wars universe
   */
  data class OtherFriend(
    override val __typename: String = "Character",
    /**
     * The name of the character
     */
    override val name: String
  ) : Friend {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.OtherFriend_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  interface Friend {
    val __typename: String

    /**
     * The name of the character
     */
    val name: String

    fun asHuman(): Human? = this as? Human

    fun asDroid(): Droid? = this as? Droid

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A character from the Star Wars universe
   */
  data class Character(
    override val __typename: String = "Character",
    /**
     * The ID of the character
     */
    val id: String,
    /**
     * The name of the character
     */
    val name: String,
    /**
     * The friends of the character, or an empty list if they have none
     */
    val friends: List<Friend?>?
  ) : Search {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Character_ResponseAdapter.toResponse(writer, this)
      }
    }

    fun friendsFilterNotNull(): List<Friend>? = friends?.filterNotNull()
  }

  data class Starship(
    override val __typename: String = "Starship",
    /**
     * The name of the starship
     */
    val name: String
  ) : Search {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.Starship_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

  data class OtherSearch(
    override val __typename: String = "SearchResult"
  ) : Search {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.OtherSearch_ResponseAdapter.toResponse(writer, this)
      }
    }
  }

  interface Search {
    val __typename: String

    fun asCharacter(): Character? = this as? Character

    fun asStarship(): Starship? = this as? Starship

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  data class Data(
    val search: List<Search?>?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller { writer ->
        TestQuery_ResponseAdapter.toResponse(writer, this)
      }
    }

    fun searchFilterNotNull(): List<Search>? = search?.filterNotNull()
  }

  companion object {
    const val OPERATION_ID: String =
        "5450032fd838d0216d8b419846d25e09f98228b403e520aacd7eb68cd838f4da"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  search(text: "test") {
          |    __typename
          |    ... on Character {
          |      id
          |      name
          |      friends {
          |        __typename
          |        ... on Character {
          |          name
          |        }
          |        ... on Human {
          |          homePlanet
          |          friends {
          |            __typename
          |            ... on Character {
          |              firstAppearsIn
          |            }
          |          }
          |        }
          |        ... on Droid {
          |          primaryFunction
          |          friends {
          |            __typename
          |            id
          |          }
          |        }
          |      }
          |    }
          |    ... on Starship {
          |      name
          |    }
          |  }
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
