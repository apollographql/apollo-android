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
import com.example.fragment_used_twice.fragment.CharacterDetails
import com.example.fragment_used_twice.fragment.HeroDetails
import com.example.fragment_used_twice.fragment.HumanDetails
import com.example.fragment_used_twice.type.CustomType
import kotlin.Any
import kotlin.Array
import kotlin.Boolean
import kotlin.String
import kotlin.Suppress
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName", "NOTHING_TO_INLINE")
class TestQuery : Query<TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
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

  data class HeroDetailsCharacterDetailsImpl(
    override val __typename: String,
    /**
     * The name of the character
     */
    override val name: String,
    /**
     * The date character was born.
     */
    override val birthDate: Any
  ) : HeroDetails, CharacterDetails, Hero {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@HeroDetailsCharacterDetailsImpl.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@HeroDetailsCharacterDetailsImpl.name)
        writer.writeCustom(RESPONSE_FIELDS[2] as ResponseField.CustomTypeField,
            this@HeroDetailsCharacterDetailsImpl.birthDate)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forCustomType("birthDate", "birthDate", null, false, CustomType.DATE, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null):
          HeroDetailsCharacterDetailsImpl {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var birthDate: Any? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> birthDate = readCustomType<Any>(RESPONSE_FIELDS[2] as
                  ResponseField.CustomTypeField)
              else -> break
            }
          }
          HeroDetailsCharacterDetailsImpl(
            __typename = __typename!!,
            name = name!!,
            birthDate = birthDate!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<HeroDetailsCharacterDetailsImpl> = ResponseFieldMapper {
          invoke(it) }
    }
  }

  data class HeroDetailsHumanDetailsCharacterDetailsImpl(
    override val __typename: String,
    /**
     * The name of the character
     */
    override val name: String,
    /**
     * The date character was born.
     */
    override val birthDate: Any
  ) : HeroDetails, HumanDetails, CharacterDetails, Hero {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0],
            this@HeroDetailsHumanDetailsCharacterDetailsImpl.__typename)
        writer.writeString(RESPONSE_FIELDS[1],
            this@HeroDetailsHumanDetailsCharacterDetailsImpl.name)
        writer.writeCustom(RESPONSE_FIELDS[2] as ResponseField.CustomTypeField,
            this@HeroDetailsHumanDetailsCharacterDetailsImpl.birthDate)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forCustomType("birthDate", "birthDate", null, false, CustomType.DATE, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null):
          HeroDetailsHumanDetailsCharacterDetailsImpl {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var birthDate: Any? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> birthDate = readCustomType<Any>(RESPONSE_FIELDS[2] as
                  ResponseField.CustomTypeField)
              else -> break
            }
          }
          HeroDetailsHumanDetailsCharacterDetailsImpl(
            __typename = __typename!!,
            name = name!!,
            birthDate = birthDate!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<HeroDetailsHumanDetailsCharacterDetailsImpl> =
          ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class OtherHero(
    override val __typename: String = "Character"
  ) : Hero {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@OtherHero.__typename)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): OtherHero {
        return reader.run {
          var __typename: String? = __typename
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              else -> break
            }
          }
          OtherHero(
            __typename = __typename!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<OtherHero> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  interface Hero {
    val __typename: String

    fun asHeroDetails(): HeroDetails? = this as? HeroDetails

    fun asCharacterDetails(): CharacterDetails? = this as? CharacterDetails

    fun asHumanDetails(): HumanDetails? = this as? HumanDetails

    fun marshaller(): ResponseFieldMarshaller

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): Hero {
        val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
        return when(typename) {
          "Droid" -> HeroDetailsCharacterDetailsImpl(reader, typename)
          "Human" -> HeroDetailsHumanDetailsCharacterDetailsImpl(reader, typename)
          else -> OtherHero(reader, typename)
        }
      }
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeObject(RESPONSE_FIELDS[0], this@Data.hero?.marshaller())
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forObject("hero", "hero", null, true, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): Data {
        return reader.run {
          var hero: Hero? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> hero = readObject<Hero>(RESPONSE_FIELDS[0]) { reader ->
                Hero(reader)
              }
              else -> break
            }
          }
          Data(
            hero = hero
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Data> = ResponseFieldMapper { invoke(it) }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "0717d3202204df80ffc6546a0b8dd179f40c29c183ebbea21e7c16ae27e0d056"

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
          |fragment HumanDetails on Human {
          |  __typename
          |  name
          |  ...CharacterDetails
          |}
          |fragment CharacterDetails on Character {
          |  __typename
          |  name
          |  birthDate
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String = "TestQuery"
    }
  }
}
