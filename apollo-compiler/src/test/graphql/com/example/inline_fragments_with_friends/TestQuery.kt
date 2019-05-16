// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_fragments_with_friends

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.inline_fragments_with_friends.type.CustomType
import com.example.inline_fragments_with_friends.type.Episode
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "LocalVariableName", "RemoveExplicitTypeArguments")
class TestQuery : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
    override fun operationId(): String = OPERATION_ID
    override fun queryDocument(): String = QUERY_DOCUMENT
    override fun wrapData(data: Data?): Data? = data
    override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES
    override fun name(): OperationName = OPERATION_NAME
    override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
        Data(it)
    }

    interface HeroCharacter {
        fun marshaller(): ResponseFieldMarshaller
    }

    data class Friend(
        val __typename: String,
        /**
         * The movies this character appears in
         */
        val appearsIn: List<Episode?>
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeList(RESPONSE_FIELDS[1], appearsIn) { value, listItemWriter ->
                value?.forEach { value ->
                    listItemWriter.writeString(value?.rawValue)
                }
            }
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forList("appearsIn", "appearsIn", null, false, null)
                    )

            operator fun invoke(reader: ResponseReader): Friend {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val appearsIn = reader.readList<Episode>(RESPONSE_FIELDS[1]) {
                    Episode.safeValueOf(it.readString())
                }
                return Friend(
                    __typename = __typename,
                    appearsIn = appearsIn
                )
            }
        }
    }

    data class AsHuman(
        val __typename: String,
        /**
         * What this human calls themselves
         */
        val name: String,
        /**
         * Height in the preferred unit, default is meters
         */
        val height: Double?,
        /**
         * This human's friends, or an empty list if they have none
         */
        val friends: List<Friend?>?
    ) : HeroCharacter {
        override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeString(RESPONSE_FIELDS[1], name)
            it.writeDouble(RESPONSE_FIELDS[2], height)
            it.writeList(RESPONSE_FIELDS[3], friends) { value, listItemWriter ->
                value?.forEach { value ->
                    listItemWriter.writeObject(value?.marshaller())
                }
            }
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forString("name", "name", null, false, null),
                    ResponseField.forDouble("height", "height", null, true, null),
                    ResponseField.forList("friends", "friends", null, true, null)
                    )

            val POSSIBLE_TYPES: Array<String> = arrayOf("Human")

            operator fun invoke(reader: ResponseReader): AsHuman {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                val height = reader.readDouble(RESPONSE_FIELDS[2])
                val friends = reader.readList<Friend>(RESPONSE_FIELDS[3]) {
                    it.readObject<Friend> { reader ->
                        Friend(reader)
                    }

                }
                return AsHuman(
                    __typename = __typename,
                    name = name,
                    height = height,
                    friends = friends
                )
            }
        }
    }

    data class Friend1(
        val __typename: String,
        /**
         * The ID of the character
         */
        val id: String
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, id)
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null)
                    )

            operator fun invoke(reader: ResponseReader): Friend1 {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val id = reader.readCustomType<String>(RESPONSE_FIELDS[1] as
                        ResponseField.CustomTypeField)
                return Friend1(
                    __typename = __typename,
                    id = id
                )
            }
        }
    }

    data class AsDroid(
        val __typename: String,
        /**
         * What others call this droid
         */
        val name: String,
        /**
         * This droid's primary function
         */
        val primaryFunction: String?,
        /**
         * This droid's friends, or an empty list if they have none
         */
        val friends: List<Friend1?>?
    ) : HeroCharacter {
        override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeString(RESPONSE_FIELDS[1], name)
            it.writeString(RESPONSE_FIELDS[2], primaryFunction)
            it.writeList(RESPONSE_FIELDS[3], friends) { value, listItemWriter ->
                value?.forEach { value ->
                    listItemWriter.writeObject(value?.marshaller())
                }
            }
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forString("name", "name", null, false, null),
                    ResponseField.forString("primaryFunction", "primaryFunction", null, true, null),
                    ResponseField.forList("friends", "friends", null, true, null)
                    )

            val POSSIBLE_TYPES: Array<String> = arrayOf("Droid")

            operator fun invoke(reader: ResponseReader): AsDroid {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                val primaryFunction = reader.readString(RESPONSE_FIELDS[2])
                val friends = reader.readList<Friend1>(RESPONSE_FIELDS[3]) {
                    it.readObject<Friend1> { reader ->
                        Friend1(reader)
                    }

                }
                return AsDroid(
                    __typename = __typename,
                    name = name,
                    primaryFunction = primaryFunction,
                    friends = friends
                )
            }
        }
    }

    data class Hero(
        val __typename: String,
        /**
         * The name of the character
         */
        val name: String,
        val inlineFragment: HeroCharacter?
    ) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeString(RESPONSE_FIELDS[1], name)
            it.writeObject(RESPONSE_FIELDS[2], inlineFragment?.marshaller())
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forString("name", "name", null, false, null),
                    ResponseField.forInlineFragment("__typename", "__typename", listOf("Human",
                            "Droid"))
                    )

            operator fun invoke(reader: ResponseReader): Hero {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                val inlineFragment = reader.readConditional(RESPONSE_FIELDS[2]) { conditionalType,
                        reader ->
                    when(conditionalType) {
                        in AsHuman.POSSIBLE_TYPES -> AsHuman(reader)
                        in AsDroid.POSSIBLE_TYPES -> AsDroid(reader)
                        else -> null
                    }
                }

                return Hero(
                    __typename = __typename,
                    name = name,
                    inlineFragment = inlineFragment
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
                "14adf3d0e99c01ba1b6ddef42021f6167d761619e1d15a617d573ea5e82fc0a5"

        val QUERY_DOCUMENT: String = """
                |query TestQuery {
                |  hero {
                |    __typename
                |    name
                |    ... on Human {
                |      height
                |      friends {
                |        __typename
                |        appearsIn
                |      }
                |    }
                |    ... on Droid {
                |      primaryFunction
                |      friends {
                |        __typename
                |        id
                |      }
                |    }
                |  }
                |}
                """.trimMargin()

        val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
    }
}
