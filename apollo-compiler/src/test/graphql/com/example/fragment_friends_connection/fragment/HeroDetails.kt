// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_friends_connection.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class HeroDetails(
  /**
   * The name of the character
   */
  val name: String,
  /**
   * The friends of the character exposed as a connection with edges
   */
  val friendsConnection: FriendsConnection
) : GraphqlFragment {
  override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
    it.writeString(RESPONSE_FIELDS[0], name)
    it.writeObject(RESPONSE_FIELDS[1], friendsConnection.marshaller())
  }

  companion object {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forObject("friendsConnection", "friendsConnection", null, false, null)
        )

    val FRAGMENT_DEFINITION: String = """
        |fragment HeroDetails on Character {
        |  name
        |  friendsConnection {
        |    totalCount
        |    edges {
        |      node {
        |        name
        |      }
        |    }
        |  }
        |}
        """.trimMargin()

    val POSSIBLE_TYPES: Array<String> = arrayOf("Human", "Droid")

    operator fun invoke(reader: ResponseReader): HeroDetails {
      val name = reader.readString(RESPONSE_FIELDS[0])
      val friendsConnection = reader.readObject<FriendsConnection>(RESPONSE_FIELDS[1]) { reader ->
        FriendsConnection(reader)
      }

      return HeroDetails(
        name = name,
        friendsConnection = friendsConnection
      )
    }
  }

  data class Node(
    /**
     * The name of the character
     */
    val name: String
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeString(RESPONSE_FIELDS[0], name)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("name", "name", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): Node {
        val name = reader.readString(RESPONSE_FIELDS[0])
        return Node(
          name = name
        )
      }
    }
  }

  data class Edge(
    /**
     * The character represented by this friendship edge
     */
    val node: Node?
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeObject(RESPONSE_FIELDS[0], node?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("node", "node", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): Edge {
        val node = reader.readObject<Node>(RESPONSE_FIELDS[0]) { reader ->
          Node(reader)
        }

        return Edge(
          node = node
        )
      }
    }
  }

  data class FriendsConnection(
    /**
     * The total number of friends
     */
    val totalCount: Int?,
    /**
     * The edges for each of the character's friends.
     */
    val edges: List<Edge?>?
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeInt(RESPONSE_FIELDS[0], totalCount)
      it.writeList(RESPONSE_FIELDS[1], edges) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeObject(value?.marshaller())
        }
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forInt("totalCount", "totalCount", null, true, null),
          ResponseField.forList("edges", "edges", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): FriendsConnection {
        val totalCount = reader.readInt(RESPONSE_FIELDS[0])
        val edges = reader.readList<Edge>(RESPONSE_FIELDS[1]) {
          it.readObject<Edge> { reader ->
            Edge(reader)
          }

        }
        return FriendsConnection(
          totalCount = totalCount,
          edges = edges
        )
      }
    }
  }
}
