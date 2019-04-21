// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_with_inline_fragment

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.fragment_with_inline_fragment.fragment.HeroDetails
import com.example.fragment_with_inline_fragment.type.Episode
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "LocalVariableName", "RemoveExplicitTypeArguments")
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
        val fragments: Fragments
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeString(RESPONSE_FIELDS[1], name)
            it.writeList(RESPONSE_FIELDS[2], appearsIn) { value, listItemWriter ->
                value?.forEach { value ->
                    listItemWriter.writeString(value?.rawValue)
                }
            }
            fragments.marshaller().marshal(it)
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forString("name", "name", null, false, null),
                    ResponseField.forList("appearsIn", "appearsIn", null, false, null),
                    ResponseField.forString("__typename", "__typename", null, false, null)
                    )

            operator fun invoke(reader: ResponseReader): Hero {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                val appearsIn = reader.readList<Episode>(RESPONSE_FIELDS[2]) {
                    Episode.safeValueOf(it.readString())
                }
                val fragments = reader.readConditional(RESPONSE_FIELDS[3]) { conditionalType,
                        reader ->
                    val heroDetails = if (HeroDetails.POSSIBLE_TYPES.contains(conditionalType))
                            HeroDetails(reader) else null
                    Fragments(
                        heroDetails = heroDetails!!
                    )
                }

                return Hero(
                    __typename = __typename,
                    name = name,
                    appearsIn = appearsIn,
                    fragments = fragments
                )
            }
        }

        data class Fragments(val heroDetails: HeroDetails) {
            fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
                heroDetails.marshaller().marshal(it)
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
                "79e2a0e5ebf670253687cf4be332e00f5495448f49669ad69931b83a46aed917"

        val QUERY_DOCUMENT: String = """
                |query TestQuery {
                |  hero {
                |    __typename
                |    name
                |    ...HeroDetails
                |    appearsIn
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
                |  ... on Droid {
                |    name
                |    primaryFunction
                |  }
                |}
                """.trimMargin()

        val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
    }
}
