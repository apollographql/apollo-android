// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.unique_type_name

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.example.unique_type_name.fragment.HeroDetails
import com.example.unique_type_name.type.Episode
import java.io.IOException
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.jvm.Throws
import okio.BufferedSource

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
class HeroDetailQuery : Query<HeroDetailQuery.Data, HeroDetailQuery.Data, Operation.Variables> {
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
  override fun parse(source: BufferedSource): Response<Data> = parse(source, DEFAULT)

  interface HeroDetailQueryCharacter {
    fun marshaller(): ResponseFieldMarshaller
  }

  data class Friend1(
    val __typename: String = "Character",
    val fragments: Fragments
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Friend1.__typename)
      this@Friend1.fragments.marshaller().marshal(writer)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("__typename", "__typename", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): Friend1 = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val fragments = Fragments(reader)
        Friend1(
          __typename = __typename,
          fragments = fragments
        )
      }
    }

    data class Fragments(
      val heroDetails: HeroDetails
    ) {
      fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
        writer.writeFragment(this@Fragments.heroDetails.marshaller())
      }

      companion object {
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField.forFragment("__typename", "__typename", null)
            )

        operator fun invoke(reader: ResponseReader): Fragments = reader.run {
          val heroDetails = readFragment<HeroDetails>(RESPONSE_FIELDS[0]) { reader ->
            HeroDetails(reader)
          }!!
          Fragments(
            heroDetails = heroDetails
          )
        }
      }
    }
  }

  data class Friend(
    val __typename: String = "Character",
    /**
     * The name of the character
     */
    val name: String,
    /**
     * The movies this character appears in
     */
    val appearsIn: List<Episode?>,
    /**
     * The friends of the character, or an empty list if they have none
     */
    val friends: List<Friend1?>?
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Friend.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@Friend.name)
      writer.writeList(RESPONSE_FIELDS[2], this@Friend.appearsIn) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeString(value?.rawValue)}
      }
      writer.writeList(RESPONSE_FIELDS[3], this@Friend.friends) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())}
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forList("appearsIn", "appearsIn", null, false, null),
          ResponseField.forList("friends", "friends", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): Friend = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        val appearsIn = readList<Episode>(RESPONSE_FIELDS[2]) { reader ->
          reader.readString()?.let{ Episode.safeValueOf(it) }
        }!!
        val friends = readList<Friend1>(RESPONSE_FIELDS[3]) { reader ->
          reader.readObject<Friend1> { reader ->
            Friend1(reader)
          }
        }
        Friend(
          __typename = __typename,
          name = name,
          appearsIn = appearsIn,
          friends = friends
        )
      }
    }
  }

  data class AsHuman(
    val __typename: String = "Human",
    /**
     * What this human calls themselves
     */
    val name: String,
    /**
     * This human's friends, or an empty list if they have none
     */
    val friends: List<Friend?>?,
    /**
     * Height in the preferred unit, default is meters
     */
    val height: Double?
  ) : HeroDetailQueryCharacter {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@AsHuman.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@AsHuman.name)
      writer.writeList(RESPONSE_FIELDS[2], this@AsHuman.friends) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())}
      }
      writer.writeDouble(RESPONSE_FIELDS[3], this@AsHuman.height)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forList("friends", "friends", null, true, null),
          ResponseField.forDouble("height", "height", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): AsHuman = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        val friends = readList<Friend>(RESPONSE_FIELDS[2]) { reader ->
          reader.readObject<Friend> { reader ->
            Friend(reader)
          }
        }
        val height = readDouble(RESPONSE_FIELDS[3])
        AsHuman(
          __typename = __typename,
          name = name,
          friends = friends,
          height = height
        )
      }
    }
  }

  data class Friend2(
    val __typename: String = "Character",
    /**
     * The name of the character
     */
    val name: String
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Friend2.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@Friend2.name)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): Friend2 = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        Friend2(
          __typename = __typename,
          name = name
        )
      }
    }
  }

  data class HeroDetailQuery(
    val __typename: String = "Character",
    /**
     * The name of the character
     */
    val name: String,
    /**
     * The friends of the character, or an empty list if they have none
     */
    val friends: List<Friend2?>?,
    val asHuman: AsHuman?
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@HeroDetailQuery.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@HeroDetailQuery.name)
      writer.writeList(RESPONSE_FIELDS[2], this@HeroDetailQuery.friends) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())}
      }
      writer.writeFragment(this@HeroDetailQuery.asHuman?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forList("friends", "friends", null, true, null),
          ResponseField.forFragment("__typename", "__typename", listOf(
            ResponseField.Condition.typeCondition(arrayOf("Human"))
          ))
          )

      operator fun invoke(reader: ResponseReader): HeroDetailQuery = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val name = readString(RESPONSE_FIELDS[1])!!
        val friends = readList<Friend2>(RESPONSE_FIELDS[2]) { reader ->
          reader.readObject<Friend2> { reader ->
            Friend2(reader)
          }
        }
        val asHuman = readFragment<AsHuman>(RESPONSE_FIELDS[3]) { reader ->
          AsHuman(reader)
        }
        HeroDetailQuery(
          __typename = __typename,
          name = name,
          friends = friends,
          asHuman = asHuman
        )
      }
    }
  }

  data class Data(
    val heroDetailQuery: HeroDetailQuery?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller.invoke { writer ->
      writer.writeObject(RESPONSE_FIELDS[0], this@Data.heroDetailQuery?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("heroDetailQuery", "heroDetailQuery", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): Data = reader.run {
        val heroDetailQuery = readObject<HeroDetailQuery>(RESPONSE_FIELDS[0]) { reader ->
          HeroDetailQuery(reader)
        }
        Data(
          heroDetailQuery = heroDetailQuery
        )
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "11473383397766137d7923128dd8cd6f27fcab32df9d9c091f08cf12a893a556"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query HeroDetailQuery {
          |  heroDetailQuery {
          |    __typename
          |    name
          |    friends {
          |      __typename
          |      name
          |    }
          |    ... on Human {
          |      height
          |      friends {
          |        __typename
          |        appearsIn
          |        friends {
          |          __typename
          |          ...HeroDetails
          |        }
          |      }
          |    }
          |  }
          |}
          |fragment HeroDetails on Character {
          |  __typename
          |  name
          |  friendsConnection {
          |    __typename
          |    totalCount
          |    edges {
          |      __typename
          |      node {
          |        __typename
          |        name
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String = "HeroDetailQuery"
    }
  }
}
