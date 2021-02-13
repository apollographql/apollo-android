// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.inline_fragment_type_coercion.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.inline_fragment_type_coercion.TestQuery
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<TestQuery.Data> {
  val fooAdapter: ResponseAdapter<TestQuery.Data.Foo?> =
      NullableResponseAdapter(Foo(customScalarAdapters))

  override fun fromResponse(reader: JsonReader): TestQuery.Data {
    var foo: TestQuery.Data.Foo? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> foo = fooAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      foo = foo
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    fooAdapter.toResponse(writer, value.foo)
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Foo"),
        responseName = "foo",
        fieldName = "foo",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet("FooBar", Foo.BarFoo.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Foo.OtherFoo.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Foo(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Foo> {
    val barFooAdapter: BarFoo =
        com.example.inline_fragment_type_coercion.adapter.TestQuery_ResponseAdapter.Foo.BarFoo(customScalarAdapters)

    val otherFooAdapter: OtherFoo =
        com.example.inline_fragment_type_coercion.adapter.TestQuery_ResponseAdapter.Foo.OtherFoo(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): TestQuery.Data.Foo {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "FooBar" -> barFooAdapter.fromResponse(reader, typename)
        else -> otherFooAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Foo) {
      when(value) {
        is TestQuery.Data.Foo.BarFoo -> barFooAdapter.toResponse(writer, value)
        is TestQuery.Data.Foo.OtherFoo -> otherFooAdapter.toResponse(writer, value)
      }
    }

    class BarFoo(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val fooAdapter: ResponseAdapter<String> = stringResponseAdapter

      val barAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Foo.BarFoo {
        var __typename: String? = __typename
        var foo: String? = null
        var bar: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> foo = fooAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("foo")
            2 -> bar = barAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("bar")
            else -> break
          }
        }
        return TestQuery.Data.Foo.BarFoo(
          __typename = __typename!!,
          foo = foo!!,
          bar = bar!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Foo.BarFoo) {
        __typenameAdapter.toResponse(writer, value.__typename)
        fooAdapter.toResponse(writer, value.foo)
        barAdapter.toResponse(writer, value.bar)
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "__typename",
            fieldName = "__typename",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "foo",
            fieldName = "foo",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "bar",
            fieldName = "bar",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class OtherFoo(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val fooAdapter: ResponseAdapter<String> = stringResponseAdapter

      fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Foo.OtherFoo {
        var __typename: String? = __typename
        var foo: String? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> foo = fooAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("foo")
            else -> break
          }
        }
        return TestQuery.Data.Foo.OtherFoo(
          __typename = __typename!!,
          foo = foo!!
        )
      }

      fun toResponse(writer: JsonWriter, value: TestQuery.Data.Foo.OtherFoo) {
        __typenameAdapter.toResponse(writer, value.__typename)
        fooAdapter.toResponse(writer, value.foo)
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "__typename",
            fieldName = "__typename",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "foo",
            fieldName = "foo",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }
  }
}
