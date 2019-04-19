package com.example.starships

import com.apollographql.apollo.api.InputFieldMarshaller
import com.apollographql.apollo.api.InputFieldWriter
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.starships.type.CustomType
import java.io.IOException
import javax.annotation.Generated
import kotlin.Any
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.jvm.Throws
import kotlin.jvm.Transient

@Generated("Apollo GraphQL")
@Suppress("NAME_SHADOWING", "LocalVariableName")
data class TestQuery(val id: String) : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
    @Transient
    private val variables: Operation.Variables = object : Operation.Variables() {
        override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
            this["id"] = id
        }

        override fun marshaller(): InputFieldMarshaller = object : InputFieldMarshaller {
            @Throws(IOException::class)
            override fun marshal(writer: InputFieldWriter) {
                writer.writeCustom("id", CustomType.ID, id)
            }
        }
    }

    override fun operationId(): String = OPERATION_ID
    override fun queryDocument(): String = QUERY_DOCUMENT
    override fun wrapData(data: Data): Data = data
    override fun variables(): Operation.Variables = variables
    override fun name(): OperationName = OPERATION_NAME
    override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
        Data(it)
    }

    data class Starship(
        val __typename: String,
        /**
         * The ID of the starship
         */
        val id: String,
        /**
         * The name of the starship
         */
        val name: String,
        val coordinates: List<List<Double?>?>?
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, id)
            it.writeString(RESPONSE_FIELDS[2], name)
            it.writeList(RESPONSE_FIELDS[3], coordinates) { value, listItemWriter ->
                value?.forEach { value ->
                    listItemWriter.writeList(value) { value, listItemWriter ->
                        value?.forEach { value ->
                            listItemWriter.writeDouble(value)
                        }
                    }
                }
            }
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null),
                    ResponseField.forString("name", "name", null, false, null),
                    ResponseField.forList("coordinates", "coordinates", null, true, null)
                    )

            operator fun invoke(reader: ResponseReader): Starship {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val id = reader.readCustomType<String>(RESPONSE_FIELDS[1] as
                        ResponseField.CustomTypeField)
                val name = reader.readString(RESPONSE_FIELDS[2])
                val coordinates = reader.readList<List<Double?>>(RESPONSE_FIELDS[3]) {
                    it.readList<Double> {
                        it.readDouble()
                    }
                }
                return Starship(
                    __typename = __typename,
                    id = id,
                    name = name,
                    coordinates = coordinates
                )
            }
        }
    }

    data class Data(val starship: Starship?) : Operation.Data {
        override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeObject(RESPONSE_FIELDS[0], starship?.marshaller())
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forObject("starship", "starship", mapOf<String, Any>(
                        "id" to mapOf<String, Any>(
                            "kind" to "Variable",
                            "variableName" to "id")), true, null)
                    )

            operator fun invoke(reader: ResponseReader): Data {
                val starship = reader.readObject<Starship>(RESPONSE_FIELDS[0]) { reader ->
                    Starship(reader)
                }

                return Data(
                    starship = starship
                )
            }
        }
    }

    companion object {
        const val OPERATION_ID: String =
                "b1a5bbb02a3a876846b727d73a26bf205341d6e7b7181f17e93c8f3b0a5d4b3e"

        val QUERY_DOCUMENT: String = """
                |query TestQuery(${'$'}id: ID!) {
                |  starship(id: ${'$'}id) {
                |    __typename
                |    id
                |    name
                |    coordinates
                |  }
                |}
                """.trimMargin()

        val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
    }
}
