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
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import com.example.union_inline_fragments.type.CustomType
import com.example.union_inline_fragments.type.Episode
import kotlin.Array
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
class TestQuery : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper.invoke {
    Data(it)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(byteString: ByteString, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = parse(Buffer().write(byteString), scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> = parse(source, DEFAULT)

  @Throws(IOException::class)
  override fun parse(byteString: ByteString): Response<Data> = parse(byteString, DEFAULT)

  override fun composeRequestBody(scalarTypeAdapters: ScalarTypeAdapters): ByteString =
      OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = scalarTypeAdapters
  )

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

  interface SearchSearchResult {
    fun marshaller(): ResponseFieldMarshaller
  }

  interface FriendCharacter {
    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A character from the Star Wars universe
   */
  data class Friend(
    val __typename: String = "Character",
    /**
     * The movie this character first appears in
     */
    val firstAppearsIn: Episode
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Friend.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@Friend.firstAppearsIn.rawValue)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forEnum("firstAppearsIn", "firstAppearsIn", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): Friend = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val firstAppearsIn = Episode.safeValueOf(readString(RESPONSE_FIELDS[1])!!)
        Friend(
          __typename = __typename,
          firstAppearsIn = firstAppearsIn
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Friend> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A humanoid creature from the Star Wars universe
   */
  data class AsHuman(
    val __typename: String = "Human",
    /**
     * The home planet of the human, or null if unknown
     */
    val homePlanet: String?,
    /**
     * This human's friends, or an empty list if they have none
     */
    val friends: List<Friend?>?,
    /**
     * The name of the character
     */
    val name: String
  ) : FriendCharacter {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@AsHuman.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@AsHuman.homePlanet)
      writer.writeList(RESPONSE_FIELDS[2], this@AsHuman.friends) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())}
      }
      writer.writeString(RESPONSE_FIELDS[3], this@AsHuman.name)
    }

    fun friendsFilterNotNull(): List<Friend>? = friends?.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("homePlanet", "homePlanet", null, true, null),
          ResponseField.forList("friends", "friends", null, true, null),
          ResponseField.forString("name", "name", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): AsHuman = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val homePlanet = readString(RESPONSE_FIELDS[1])
        val friends = readList<Friend>(RESPONSE_FIELDS[2]) { reader ->
          reader.readObject<Friend> { reader ->
            Friend(reader)
          }
        }
        val name = readString(RESPONSE_FIELDS[3])!!
        AsHuman(
          __typename = __typename,
          homePlanet = homePlanet,
          friends = friends,
          name = name
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<AsHuman> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class Friend1(
    val __typename: String = "Character",
    /**
     * The ID of the character
     */
    val id: String
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Friend1.__typename)
      writer.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, this@Friend1.id)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null)
          )

      operator fun invoke(reader: ResponseReader): Friend1 = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val id = readCustomType<String>(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField)!!
        Friend1(
          __typename = __typename,
          id = id
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Friend1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  data class AsDroid(
    val __typename: String = "Droid",
    /**
     * This droid's primary function
     */
    val primaryFunction: String?,
    /**
     * This droid's friends, or an empty list if they have none
     */
    val friends: List<Friend1?>?,
    /**
     * The name of the character
     */
    val name: String
  ) : FriendCharacter {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@AsDroid.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@AsDroid.primaryFunction)
      writer.writeList(RESPONSE_FIELDS[2], this@AsDroid.friends) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())}
      }
      writer.writeString(RESPONSE_FIELDS[3], this@AsDroid.name)
    }

    fun friendsFilterNotNull(): List<Friend1>? = friends?.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("primaryFunction", "primaryFunction", null, true, null),
          ResponseField.forList("friends", "friends", null, true, null),
          ResponseField.forString("name", "name", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): AsDroid = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val primaryFunction = readString(RESPONSE_FIELDS[1])
        val friends = readList<Friend1>(RESPONSE_FIELDS[2]) { reader ->
          reader.readObject<Friend1> { reader ->
            Friend1(reader)
          }
        }
        val name = readString(RESPONSE_FIELDS[3])!!
        AsDroid(
          __typename = __typename,
          primaryFunction = primaryFunction,
          friends = friends,
          name = name
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<AsDroid> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class Friend2(
    val __typename: String = "Character",
    /**
     * The name of the character
     */
    val name: String,
    val asHuman: AsHuman?,
    val asDroid: AsDroid?
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Friend2.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@Friend2.name)
      writer.writeFragment(this@Friend2.asHuman?.marshaller())
      writer.writeFragment(this@Friend2.asDroid?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forFragment("__typename", "__typename", listOf(
            ResponseField.Condition.typeCondition(arrayOf("Human"))
          )),
          ResponseField.forFragment("__typename", "__typename", listOf(
            ResponseField.Condition.typeCondition(arrayOf("Droid"))
          ))
          )

      operator fun invoke(reader: ResponseReader): Friend2 = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        val asHuman = readFragment<AsHuman>(RESPONSE_FIELDS[2]) { reader ->
          AsHuman(reader)
        }
        val asDroid = readFragment<AsDroid>(RESPONSE_FIELDS[3]) { reader ->
          AsDroid(reader)
        }
        Friend2(
          __typename = __typename,
          name = name,
          asHuman = asHuman,
          asDroid = asDroid
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Friend2> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class AsCharacter(
    val __typename: String = "Character",
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
    val friends: List<Friend2?>?
  ) : SearchSearchResult {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@AsCharacter.__typename)
      writer.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, this@AsCharacter.id)
      writer.writeString(RESPONSE_FIELDS[2], this@AsCharacter.name)
      writer.writeList(RESPONSE_FIELDS[3], this@AsCharacter.friends) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())}
      }
    }

    fun friendsFilterNotNull(): List<Friend2>? = friends?.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forList("friends", "friends", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): AsCharacter = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val id = readCustomType<String>(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField)!!
        val name = readString(RESPONSE_FIELDS[2])!!
        val friends = readList<Friend2>(RESPONSE_FIELDS[3]) { reader ->
          reader.readObject<Friend2> { reader ->
            Friend2(reader)
          }
        }
        AsCharacter(
          __typename = __typename,
          id = id,
          name = name,
          friends = friends
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<AsCharacter> = ResponseFieldMapper { invoke(it) }
    }
  }

  data class AsStarship(
    val __typename: String = "Starship",
    /**
     * The name of the starship
     */
    val name: String
  ) : SearchSearchResult {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@AsStarship.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@AsStarship.name)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): AsStarship = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        AsStarship(
          __typename = __typename,
          name = name
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<AsStarship> = ResponseFieldMapper { invoke(it) }
    }
  }

  data class Search(
    val __typename: String = "SearchResult",
    val asCharacter: AsCharacter?,
    val asStarship: AsStarship?
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Search.__typename)
      writer.writeFragment(this@Search.asCharacter?.marshaller())
      writer.writeFragment(this@Search.asStarship?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forFragment("__typename", "__typename", listOf(
            ResponseField.Condition.typeCondition(arrayOf("Human", "Droid"))
          )),
          ResponseField.forFragment("__typename", "__typename", listOf(
            ResponseField.Condition.typeCondition(arrayOf("Starship"))
          ))
          )

      operator fun invoke(reader: ResponseReader): Search = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val asCharacter = readFragment<AsCharacter>(RESPONSE_FIELDS[1]) { reader ->
          AsCharacter(reader)
        }
        val asStarship = readFragment<AsStarship>(RESPONSE_FIELDS[2]) { reader ->
          AsStarship(reader)
        }
        Search(
          __typename = __typename,
          asCharacter = asCharacter,
          asStarship = asStarship
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Search> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  data class Data(
    val search: List<Search?>?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeList(RESPONSE_FIELDS[0], this@Data.search) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())}
      }
    }

    fun searchFilterNotNull(): List<Search>? = search?.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forList("search", "search", mapOf<String, Any>(
            "text" to "test"), true, null)
          )

      operator fun invoke(reader: ResponseReader): Data = reader.run {
        val search = readList<Search>(RESPONSE_FIELDS[0]) { reader ->
          reader.readObject<Search> { reader ->
            Search(reader)
          }
        }
        Data(
          search = search
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Data> = ResponseFieldMapper { invoke(it) }
    }
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
      override fun name(): String = "TestQuery"
    }
  }
}
