// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_in_fragment.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import com.example.fragment_in_fragment.type.CustomType
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

/**
 * A single transport craft that has hyperdrive capability.
 */
@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
interface StarshipFragment : GraphqlFragment {
  val __typename: String

  /**
   * The ID of an object
   */
  val id: String

  /**
   * The name of this starship. The common name, such as "Death Star".
   */
  val name: String?

  val pilotConnection: PilotConnection?

  /**
   * An individual person or character within the Star Wars universe.
   */
  data class NodeImpl(
    override val __typename: String = "Person"
  ) : Node {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@NodeImpl.__typename)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): NodeImpl = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        NodeImpl(
          __typename = __typename
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<NodeImpl> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  data class HomeworldImpl(
    override val __typename: String = "Planet"
  ) : Homeworld {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@HomeworldImpl.__typename)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): HomeworldImpl = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        HomeworldImpl(
          __typename = __typename
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<HomeworldImpl> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  data class PlanetFragmentImpl(
    /**
     * The name of this planet.
     */
    override val name: String?,
    override val __typename: String = "Planet"
  ) : Homeworld, PlanetFragment {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@PlanetFragmentImpl.name)
        writer.writeString(RESPONSE_FIELDS[1], this@PlanetFragmentImpl.__typename)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("name", "name", null, true, null),
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): PlanetFragmentImpl = reader.run {
        val name = readString(RESPONSE_FIELDS[0])
        val __typename = readString(RESPONSE_FIELDS[1])!!
        PlanetFragmentImpl(
          name = name,
          __typename = __typename
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<PlanetFragmentImpl> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  interface Homeworld : PilotFragment.Homeworld {
    override val __typename: String

    override fun marshaller(): ResponseFieldMarshaller

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): Homeworld {
        val typename = reader.readString(RESPONSE_FIELDS[0])
        return when(typename) {
          "Planet" -> PlanetFragmentImpl(reader)
          else -> HomeworldImpl(reader)
        }
      }
    }
  }

  /**
   * An individual person or character within the Star Wars universe.
   */
  data class PilotFragmentImpl(
    /**
     * The name of this person.
     */
    override val name: String?,
    /**
     * A planet that this person was born on or inhabits.
     */
    override val homeworld: Homeworld?,
    override val __typename: String = "Person"
  ) : Node, PilotFragment {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@PilotFragmentImpl.name)
        writer.writeObject(RESPONSE_FIELDS[1], this@PilotFragmentImpl.homeworld?.marshaller())
        writer.writeString(RESPONSE_FIELDS[2], this@PilotFragmentImpl.__typename)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("name", "name", null, true, null),
        ResponseField.forObject("homeworld", "homeworld", null, true, null),
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): PilotFragmentImpl = reader.run {
        val name = readString(RESPONSE_FIELDS[0])
        val homeworld = readObject<Homeworld>(RESPONSE_FIELDS[1]) { reader ->
          Homeworld(reader)
        }
        val __typename = readString(RESPONSE_FIELDS[2])!!
        PilotFragmentImpl(
          name = name,
          homeworld = homeworld,
          __typename = __typename
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<PilotFragmentImpl> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * An individual person or character within the Star Wars universe.
   */
  interface Node {
    val __typename: String

    fun marshaller(): ResponseFieldMarshaller

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): Node {
        val typename = reader.readString(RESPONSE_FIELDS[0])
        return when(typename) {
          "Person" -> PilotFragmentImpl(reader)
          else -> NodeImpl(reader)
        }
      }
    }
  }

  /**
   * An edge in a connection.
   */
  interface Edge {
    val __typename: String

    /**
     * The item at the end of the edge
     */
    val node: Node?

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A connection to a list of items.
   */
  interface PilotConnection {
    val __typename: String

    /**
     * A list of edges.
     */
    val edges: List<Edge?>?

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * An individual person or character within the Star Wars universe.
   */
  data class NodeImpl1(
    override val __typename: String = "Person"
  ) : Node1 {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@NodeImpl1.__typename)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): NodeImpl1 = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        NodeImpl1(
          __typename = __typename
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<NodeImpl1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  data class HomeworldImpl1(
    override val __typename: String = "Planet"
  ) : Homeworld1 {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@HomeworldImpl1.__typename)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): HomeworldImpl1 = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        HomeworldImpl1(
          __typename = __typename
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<HomeworldImpl1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  data class PlanetFragmentImpl1(
    /**
     * The name of this planet.
     */
    override val name: String?,
    override val __typename: String = "Planet"
  ) : Homeworld1, PlanetFragment {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@PlanetFragmentImpl1.name)
        writer.writeString(RESPONSE_FIELDS[1], this@PlanetFragmentImpl1.__typename)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("name", "name", null, true, null),
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): PlanetFragmentImpl1 = reader.run {
        val name = readString(RESPONSE_FIELDS[0])
        val __typename = readString(RESPONSE_FIELDS[1])!!
        PlanetFragmentImpl1(
          name = name,
          __typename = __typename
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<PlanetFragmentImpl1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A large mass, planet or planetoid in the Star Wars Universe, at the time of
   * 0 ABY.
   */
  interface Homeworld1 : PilotFragment.Homeworld {
    override val __typename: String

    override fun marshaller(): ResponseFieldMarshaller

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): Homeworld1 {
        val typename = reader.readString(RESPONSE_FIELDS[0])
        return when(typename) {
          "Planet" -> PlanetFragmentImpl1(reader)
          else -> HomeworldImpl1(reader)
        }
      }
    }
  }

  /**
   * An individual person or character within the Star Wars universe.
   */
  data class PilotFragmentImpl1(
    /**
     * The name of this person.
     */
    override val name: String?,
    /**
     * A planet that this person was born on or inhabits.
     */
    override val homeworld: Homeworld1?,
    override val __typename: String = "Person"
  ) : Node1, PilotFragment {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@PilotFragmentImpl1.name)
        writer.writeObject(RESPONSE_FIELDS[1], this@PilotFragmentImpl1.homeworld?.marshaller())
        writer.writeString(RESPONSE_FIELDS[2], this@PilotFragmentImpl1.__typename)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("name", "name", null, true, null),
        ResponseField.forObject("homeworld", "homeworld", null, true, null),
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): PilotFragmentImpl1 = reader.run {
        val name = readString(RESPONSE_FIELDS[0])
        val homeworld = readObject<Homeworld1>(RESPONSE_FIELDS[1]) { reader ->
          Homeworld1(reader)
        }
        val __typename = readString(RESPONSE_FIELDS[2])!!
        PilotFragmentImpl1(
          name = name,
          homeworld = homeworld,
          __typename = __typename
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<PilotFragmentImpl1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * An individual person or character within the Star Wars universe.
   */
  interface Node1 : Node {
    override val __typename: String

    override fun marshaller(): ResponseFieldMarshaller

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader): Node1 {
        val typename = reader.readString(RESPONSE_FIELDS[0])
        return when(typename) {
          "Person" -> PilotFragmentImpl1(reader)
          else -> NodeImpl1(reader)
        }
      }
    }
  }

  /**
   * An edge in a connection.
   */
  data class Edge1(
    override val __typename: String = "StarshipPilotsEdge",
    /**
     * The item at the end of the edge
     */
    override val node: Node1?
  ) : Edge {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@Edge1.__typename)
        writer.writeObject(RESPONSE_FIELDS[1], this@Edge1.node?.marshaller())
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forObject("node", "node", null, true, null)
      )

      operator fun invoke(reader: ResponseReader): Edge1 = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val node = readObject<Node1>(RESPONSE_FIELDS[1]) { reader ->
          Node1(reader)
        }
        Edge1(
          __typename = __typename,
          node = node
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Edge1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A connection to a list of items.
   */
  data class PilotConnection1(
    override val __typename: String = "StarshipPilotsConnection",
    /**
     * A list of edges.
     */
    override val edges: List<Edge1?>?
  ) : PilotConnection {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@PilotConnection1.__typename)
        writer.writeList(RESPONSE_FIELDS[1], this@PilotConnection1.edges) { value, listItemWriter ->
          value?.forEach { value ->
            listItemWriter.writeObject(value?.marshaller())}
        }
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forList("edges", "edges", null, true, null)
      )

      operator fun invoke(reader: ResponseReader): PilotConnection1 = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val edges = readList<Edge1>(RESPONSE_FIELDS[1]) { reader ->
          reader.readObject<Edge1> { reader ->
            Edge1(reader)
          }
        }
        PilotConnection1(
          __typename = __typename,
          edges = edges
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<PilotConnection1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A single transport craft that has hyperdrive capability.
   */
  data class DefaultImpl(
    override val __typename: String = "Starship",
    /**
     * The ID of an object
     */
    override val id: String,
    /**
     * The name of this starship. The common name, such as "Death Star".
     */
    override val name: String?,
    override val pilotConnection: PilotConnection1?
  ) : StarshipFragment {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@DefaultImpl.__typename)
        writer.writeCustom(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField, this@DefaultImpl.id)
        writer.writeString(RESPONSE_FIELDS[2], this@DefaultImpl.name)
        writer.writeObject(RESPONSE_FIELDS[3], this@DefaultImpl.pilotConnection?.marshaller())
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forCustomType("id", "id", null, false, CustomType.ID, null),
        ResponseField.forString("name", "name", null, true, null),
        ResponseField.forObject("pilotConnection", "pilotConnection", null, true, null)
      )

      operator fun invoke(reader: ResponseReader): DefaultImpl = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])!!
        val id = readCustomType<String>(RESPONSE_FIELDS[1] as ResponseField.CustomTypeField)!!
        val name = readString(RESPONSE_FIELDS[2])
        val pilotConnection = readObject<PilotConnection1>(RESPONSE_FIELDS[3]) { reader ->
          PilotConnection1(reader)
        }
        DefaultImpl(
          __typename = __typename,
          id = id,
          name = name,
          pilotConnection = pilotConnection
        )
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<DefaultImpl> = ResponseFieldMapper { invoke(it) }
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment starshipFragment on Starship {
        |  __typename
        |  id
        |  name
        |  pilotConnection {
        |    __typename
        |    edges {
        |      __typename
        |      node {
        |        __typename
        |        ...pilotFragment
        |      }
        |    }
        |  }
        |}
        """.trimMargin()

    operator fun invoke(
      __typename: String,
      id: String,
      name: String?,
      pilotConnection: PilotConnection1?
    ): StarshipFragment {
      return DefaultImpl(
        __typename = __typename,
        id = id,
        name = name,
        pilotConnection = pilotConnection
      )
    }

    operator fun invoke(reader: ResponseReader): StarshipFragment = DefaultImpl(reader)
  }
}
