package com.example.two_heroes_unique

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.two_heroes_unique.type.CustomType
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

    data class R2(
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

            operator fun invoke(reader: ResponseReader): R2 {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                return R2(
                    __typename = __typename,
                    name = name
                )
            }
        }
    }

    data class Luke(
        val __typename: String,
        /**
         * The ID of the character
         */
        val id: String,
        /**
         * The name of the character
         */
        val name: String
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, id)
            it.writeString(RESPONSE_FIELDS[2], name)
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null),
                    ResponseField.forString("name", "name", null, false, null)
                    )

            operator fun invoke(reader: ResponseReader): Luke {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val id = reader.readCustomType<String>(RESPONSE_FIELDS[1] as
                        ResponseField.CustomTypeField)
                val name = reader.readString(RESPONSE_FIELDS[2])
                return Luke(
                    __typename = __typename,
                    id = id,
                    name = name
                )
            }
        }
    }

    data class Data(val r2: R2?, val luke: Luke?) : Operation.Data {
        override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeObject(RESPONSE_FIELDS[0], r2?.marshaller())
            it.writeObject(RESPONSE_FIELDS[1], luke?.marshaller())
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forObject("r2", "hero", null, true, null),
                    ResponseField.forObject("luke", "hero", mapOf<String, Any>(
                        "episode" to "EMPIRE"), true, null)
                    )

            operator fun invoke(reader: ResponseReader): Data {
                val r2 = reader.readObject<R2>(RESPONSE_FIELDS[0]) { reader ->
                    R2(reader)
                }

                val luke = reader.readObject<Luke>(RESPONSE_FIELDS[1]) { reader ->
                    Luke(reader)
                }

                return Data(
                    r2 = r2,
                    luke = luke
                )
            }
        }
    }

    companion object {
        const val OPERATION_ID: String =
                "c4890d84f04de970692ee9da3521a903297dea7e613ece3ff1caac59f4016191"

        val QUERY_DOCUMENT: String = """
                |query TestQuery {
                |  r2: hero {
                |    __typename
                |    name
                |  }
                |  luke: hero(episode: EMPIRE) {
                |    __typename
                |    id
                |    name
                |  }
                |}
                """.trimMargin()

        val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
    }
}
