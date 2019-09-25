// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_name

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.hero_name.type.CustomType
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
class TestQuery : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
    Data(it)
  }

  data class Hero(
    /**
     * The name of the character
     */
    val name: String,
    /**
     * The ID of the character
     */
    val id: String
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeString(RESPONSE_FIELDS[0], name)
      it.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, id)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null)
          )

      operator fun invoke(reader: ResponseReader): Hero {
        val name = reader.readString(RESPONSE_FIELDS[0])
        val id = reader.readCustomType<String>(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField)
        return Hero(
          name = name,
          id = id
        )
      }
    }
  }

  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeObject(RESPONSE_FIELDS[0], hero?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("hero", "hero", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): Data {
        val hero = reader.readObject<Hero>(RESPONSE_FIELDS[0]) { reader ->
          Hero(reader)
        }

        return Data(
          hero = hero
        )
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "08f5d0fb36a51ebcc35da125cd709eaf6ee37ffda8587d55bca742c65818a4ab"

    val QUERY_DOCUMENT: String = """
        |query TestQuery {
        |  hero {
        |    name
        |  }
        |  hero {
        |    id
        |    name
        |  }
        |}
        """.trimMargin()

    val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
  }
}
