// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_fragment_type_coercion.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.inline_fragment_type_coercion.TestQuery
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.Named.Object("Foo"),
      responseName = "foo",
      fieldName = "foo",
      arguments = emptyMap(),
      conditions = emptyList(),
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return reader.run {
      var foo: TestQuery.Data.Foo? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> foo = readObject<TestQuery.Data.Foo>(RESPONSE_FIELDS[0]) { reader ->
            Foo.fromResponse(reader)
          }
          else -> break
        }
      }
      TestQuery.Data(
        foo = foo
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: TestQuery.Data) {
    if(value.foo == null) {
      writer.writeObject(RESPONSE_FIELDS[0], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
        Foo.toResponse(writer, value.foo)
      }
    }
  }

  object Foo : ResponseAdapter<TestQuery.Data.Foo> {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "__typename",
        fieldName = "__typename",
        arguments = emptyMap(),
        conditions = emptyList(),
      ),
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "foo",
        fieldName = "foo",
        arguments = emptyMap(),
        conditions = emptyList(),
      )
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data.Foo {
      val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
      return when(typename) {
        "FooBar" -> BarFoo.fromResponse(reader, typename)
        else -> OtherFoo.fromResponse(reader, typename)
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Foo) {
      when(value) {
        is TestQuery.Data.Foo.BarFoo -> BarFoo.toResponse(writer, value)
        is TestQuery.Data.Foo.OtherFoo -> OtherFoo.toResponse(writer, value)
      }
    }

    object BarFoo : ResponseAdapter<TestQuery.Data.Foo.BarFoo> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "__typename",
          fieldName = "__typename",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "foo",
          fieldName = "foo",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "bar",
          fieldName = "bar",
          arguments = emptyMap(),
          conditions = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Foo.BarFoo {
        return reader.run {
          var __typename: String? = __typename
          var foo: String? = null
          var bar: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> foo = readString(RESPONSE_FIELDS[1])
              2 -> bar = readString(RESPONSE_FIELDS[2])
              else -> break
            }
          }
          TestQuery.Data.Foo.BarFoo(
            __typename = __typename!!,
            foo = foo!!,
            bar = bar!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Foo.BarFoo) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.foo)
        writer.writeString(RESPONSE_FIELDS[2], value.bar)
      }
    }

    object OtherFoo : ResponseAdapter<TestQuery.Data.Foo.OtherFoo> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "__typename",
          fieldName = "__typename",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "foo",
          fieldName = "foo",
          arguments = emptyMap(),
          conditions = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Foo.OtherFoo {
        return reader.run {
          var __typename: String? = __typename
          var foo: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> foo = readString(RESPONSE_FIELDS[1])
              else -> break
            }
          }
          TestQuery.Data.Foo.OtherFoo(
            __typename = __typename!!,
            foo = foo!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Foo.OtherFoo) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.foo)
      }
    }
  }
}
