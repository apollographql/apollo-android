package com.example.enum_type

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.enum_type.type.Episode
import javax.annotation.Generated
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

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
        val name: String,
        /**
         * The movies this character appears in
         */
        val appearsIn: List<Episode?>,
        /**
         * The movie this character first appears in
         */
        val firstAppearsIn: Episode
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeString(RESPONSE_FIELDS[1], name)
            it.writeList(RESPONSE_FIELDS[2], appearsIn) { value, listItemWriter ->
                value?.forEach { value ->
                    listItemWriter.writeString(value?.rawValue)
                }
            }
            it.writeString(RESPONSE_FIELDS[3], firstAppearsIn.rawValue)
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forString("name", "name", null, false, null),
                    ResponseField.forList("appearsIn", "appearsIn", null, false, null),
                    ResponseField.forEnum("firstAppearsIn", "firstAppearsIn", null, false, null)
                    )

            operator fun invoke(reader: ResponseReader): Hero {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                val appearsIn = reader.readList<Episode>(RESPONSE_FIELDS[2]) {
                    Episode.safeValueOf(it.readString())
                }
                val firstAppearsIn = Episode.safeValueOf(reader.readString(RESPONSE_FIELDS[3]))
                return Hero(
                    __typename = __typename,
                    name = name,
                    appearsIn = appearsIn,
                    firstAppearsIn = firstAppearsIn
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
                "424f5306f4be3f78b90e5c704e1770756e3d874fb1355a809808fa972ddb79ed"

        val QUERY_DOCUMENT: String = """
                |query TestQuery {
                |  hero {
                |    __typename
                |    name
                |    appearsIn
                |    firstAppearsIn
                |  }
                |}
                """.trimMargin()

        val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
    }
}
