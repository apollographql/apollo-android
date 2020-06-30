package com.apollographql.apollo.compiler

import com.apollographql.apollo.compiler.parser.introspection.IntrospectionSchema
import com.apollographql.apollo.compiler.parser.sdl.GraphSdlSchema
import com.apollographql.apollo.compiler.parser.sdl.toIntrospectionSchema
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class GraphSdlParseTest() {

  @Test
  fun `SDL schema parsed successfully and produced the same introspection schema`() {
    val actualSchema = GraphSdlSchema(File("src/test/sdl/schema.graphql")).toIntrospectionSchema().normalize()
    val expectedSchema = IntrospectionSchema(File("src/test/sdl/schema.json")).normalize()
    assertEquals(actualSchema.toString(), expectedSchema.toString())
  }

  private fun IntrospectionSchema.normalize(): IntrospectionSchema {
    return copy(types = toSortedMap().mapValues { (_, type) -> type.normalize() })
  }

  private fun IntrospectionSchema.Type.normalize(): IntrospectionSchema.Type {
    return when (this) {
      is IntrospectionSchema.Type.Scalar -> this
      is IntrospectionSchema.Type.Object -> copy(fields = fields?.sortedBy { field -> field.name })
      is IntrospectionSchema.Type.Interface -> copy(fields = fields?.sortedBy { field -> field.name })
      is IntrospectionSchema.Type.Union -> copy(fields = fields?.sortedBy { field -> field.name })
      is IntrospectionSchema.Type.Enum -> this
      is IntrospectionSchema.Type.InputObject -> copy(inputFields = inputFields.sortedBy { field -> field.name })
    }
  }
}
