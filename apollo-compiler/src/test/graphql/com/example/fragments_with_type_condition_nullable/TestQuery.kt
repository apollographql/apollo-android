// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragments_with_type_condition_nullable

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
import com.example.fragments_with_type_condition_nullable.fragment.DroidDetails
import com.example.fragments_with_type_condition_nullable.fragment.HumanDetails
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

  /**
   * A humanoid creature from the Star Wars universe
   */
  data class HumanDetailsImpl(
    val humanDetailsDelegate: HumanDetails
  ) : R2, HumanDetails by humanDetailsDelegate {
    companion object {
      inline operator fun invoke(reader: ResponseReader, __typename: String? = null):
          HumanDetailsImpl {
        return HumanDetailsImpl(HumanDetails(reader, __typename))
      }
    }
  }

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  data class DroidDetailsImpl(
    val droidDetailsDelegate: DroidDetails
  ) : R2, DroidDetails by droidDetailsDelegate {
    companion object {
      inline operator fun invoke(reader: ResponseReader, __typename: String? = null):
          DroidDetailsImpl {
        return DroidDetailsImpl(DroidDetails(reader, __typename))
      }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class OtherR2(
    override val __typename: String = "Character"
  ) : R2 {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@OtherR2.__typename)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): OtherR2 {
        return reader.run {
          var __typename: String? = __typename
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              else -> break
            }
          }
          OtherR2(
            __typename = __typename!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<OtherR2> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  interface R2 {
    val __typename: String

    fun asHumanDetails(): HumanDetails? = this as? HumanDetails

    fun asDroidDetails(): DroidDetails? = this as? DroidDetails

    fun marshaller(): ResponseFieldMarshaller

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): R2 {
        val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
        return when(typename) {
          "Human" -> HumanDetailsImpl(reader, typename)
          "Droid" -> DroidDetailsImpl(reader, typename)
          else -> OtherR2(reader, typename)
        }
      }
    }
  }

  /**
   * A humanoid creature from the Star Wars universe
   */
  data class HumanDetailsImpl1(
    val humanDetailsDelegate: HumanDetails
  ) : Luke, HumanDetails by humanDetailsDelegate {
    companion object {
      inline operator fun invoke(reader: ResponseReader, __typename: String? = null):
          HumanDetailsImpl1 {
        return HumanDetailsImpl1(HumanDetails(reader, __typename))
      }
    }
  }

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  data class DroidDetailsImpl1(
    val droidDetailsDelegate: DroidDetails
  ) : Luke, DroidDetails by droidDetailsDelegate {
    companion object {
      inline operator fun invoke(reader: ResponseReader, __typename: String? = null):
          DroidDetailsImpl1 {
        return DroidDetailsImpl1(DroidDetails(reader, __typename))
      }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class OtherLuke(
    override val __typename: String = "Character"
  ) : Luke {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@OtherLuke.__typename)
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): OtherLuke {
        return reader.run {
          var __typename: String? = __typename
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              else -> break
            }
          }
          OtherLuke(
            __typename = __typename!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<OtherLuke> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  interface Luke {
    val __typename: String

    fun asHumanDetails(): HumanDetails? = this as? HumanDetails

    fun asDroidDetails(): DroidDetails? = this as? DroidDetails

    fun marshaller(): ResponseFieldMarshaller

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): Luke {
        val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
        return when(typename) {
          "Human" -> HumanDetailsImpl1(reader, typename)
          "Droid" -> DroidDetailsImpl1(reader, typename)
          else -> OtherLuke(reader, typename)
        }
      }
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  data class Data(
    val r2: R2?,
    val luke: Luke?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeObject(RESPONSE_FIELDS[0], this@Data.r2?.marshaller())
        writer.writeObject(RESPONSE_FIELDS[1], this@Data.luke?.marshaller())
      }
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forObject("r2", "hero", null, true, null),
        ResponseField.forObject("luke", "hero", null, true, null)
      )

      inline operator fun invoke(reader: ResponseReader, __typename: String? = null): Data {
        return reader.run {
          var r2: R2? = null
          var luke: Luke? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> r2 = readObject<R2>(RESPONSE_FIELDS[0]) { reader ->
                R2(reader)
              }
              1 -> luke = readObject<Luke>(RESPONSE_FIELDS[1]) { reader ->
                Luke(reader)
              }
              else -> break
            }
          }
          Data(
            r2 = r2,
            luke = luke
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Data> = ResponseFieldMapper { invoke(it) }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "919cec7210259fa24fc6026fe680b96f357c14ebf3c8a734979dcfb819685d6a"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  r2: hero {
          |    __typename
          |    ...HumanDetails
          |    ...DroidDetails
          |  }
          |  luke: hero {
          |    __typename
          |    ...HumanDetails
          |    ...DroidDetails
          |  }
          |}
          |fragment HumanDetails on Human {
          |  __typename
          |  name
          |  height
          |}
          |fragment DroidDetails on Droid {
          |  __typename
          |  name
          |  primaryFunction
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String = "TestQuery"
    }
  }
}
