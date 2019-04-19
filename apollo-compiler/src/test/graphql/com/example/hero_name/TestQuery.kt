package com.example.hero_name

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import javax.annotation.Generated
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Generated("Apollo GraphQL")
@Suppress("NAME_SHADOWING", "LocalVariableName")
class TestQuery : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
    override fun operationId(): String = OPERATION_ID
    override fun queryDocument(): String = QUERY_DOCUMENT
    override fun wrapData(data: Data): Data = data
    override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES
    override fun name(): OperationName = OPERATION_NAME
    override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
        Data(it)
    }

    data class Hero(
        val __typename: String,
        /**
         * The name of the character
         */
        val name: String
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeString(RESPONSE_FIELDS[1], name)
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forString("name", "name", null, false, null)
                    )

            operator fun invoke(reader: ResponseReader): Hero {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                return Hero(
                    __typename = __typename,
                    name = name
                )
            }
        }
    }

    data class Data(val hero: Hero?) : Operation.Data {
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
                "8b2daaf4bcd7b88157fe5d1ac7fb10e91a375f4d13ce1d049ed2bbc6cecf7431"

        val QUERY_DOCUMENT: String = """
                |query TestQuery {
                |  hero {
                |    __typename
                |    name
                |  }
                |}
                """.trimMargin()

        val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
    }
}
