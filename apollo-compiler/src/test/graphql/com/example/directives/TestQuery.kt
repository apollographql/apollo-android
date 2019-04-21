// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.directives

import com.apollographql.apollo.api.InputFieldMarshaller
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import kotlin.Any
import kotlin.Array
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Map
import kotlin.jvm.Transient

@Suppress("NAME_SHADOWING", "LocalVariableName", "RemoveExplicitTypeArguments")
data class TestQuery(val includeName: Boolean, val skipFriends: Boolean) : Query<TestQuery.Data,
        TestQuery.Data, Operation.Variables> {
    @Transient
    private val variables: Operation.Variables = object : Operation.Variables() {
        override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
            this["includeName"] = includeName
            this["skipFriends"] = skipFriends
        }

        override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller { writer ->
            writer.writeBoolean("includeName", includeName)
            writer.writeBoolean("skipFriends", skipFriends)
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

    data class FriendsConnection(
        val __typename: String,
        /**
         * The total number of friends
         */
        val totalCount: Int?
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeInt(RESPONSE_FIELDS[1], totalCount)
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forInt("totalCount", "totalCount", null, true, null)
                    )

            operator fun invoke(reader: ResponseReader): FriendsConnection {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val totalCount = reader.readInt(RESPONSE_FIELDS[1])
                return FriendsConnection(
                    __typename = __typename,
                    totalCount = totalCount
                )
            }
        }
    }

    data class Hero(
        val __typename: String,
        /**
         * The name of the character
         */
        val name: String?,
        /**
         * The friends of the character exposed as a connection with edges
         */
        val friendsConnection: FriendsConnection?
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeString(RESPONSE_FIELDS[1], name)
            it.writeObject(RESPONSE_FIELDS[2], friendsConnection?.marshaller())
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forString("name", "name", null, true,
                            listOf(ResponseField.Condition.booleanCondition("includeName", false))),
                    ResponseField.forObject("friendsConnection", "friendsConnection", null, true,
                            listOf(ResponseField.Condition.booleanCondition("skipFriends", true)))
                    )

            operator fun invoke(reader: ResponseReader): Hero {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                val friendsConnection = reader.readObject<FriendsConnection>(RESPONSE_FIELDS[2]) {
                        reader ->
                    FriendsConnection(reader)
                }

                return Hero(
                    __typename = __typename,
                    name = name,
                    friendsConnection = friendsConnection
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
                "e442e7d1da90271c3ea02f2b50cdc9fb858fc830cce998243e0ce085595f3ec2"

        val QUERY_DOCUMENT: String = """
                |query TestQuery(${'$'}includeName: Boolean!, ${'$'}skipFriends: Boolean!) {
                |  hero {
                |    __typename
                |    name @include(if: ${'$'}includeName)
                |    friendsConnection @skip(if: ${'$'}skipFriends) {
                |      __typename
                |      totalCount
                |    }
                |  }
                |}
                """.trimMargin()

        val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
    }
}
