package com.apollographql.apollo3.api


import com.apollographql.apollo3.api.internal.json.MapJsonReader.Companion.buffer
import com.apollographql.apollo3.api.internal.json.MapJsonWriter
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.api.internal.json.Utils
import com.apollographql.apollo3.api.internal.json.Utils.readRecursively
import kotlin.native.concurrent.SharedImmutable

/**
 * This file contains a list of [ResponseAdapter] for standard types
 *
 * They are mostly used from the generated code but could be useful in any other situations that requires adapting from
 * GraphQL to Kotlin.
 * In particular, [AnyResponseAdapter] can be used to read/write a Kotlin representation from/to Json.
 */
class ListResponseAdapter<T>(private val wrappedAdapter: ResponseAdapter<T>) : ResponseAdapter<List<T>> {
  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): List<T> {
    reader.beginArray()
    val list = mutableListOf<T>()
    while (reader.hasNext()) {
      list.add(wrappedAdapter.fromResponse(reader, responseAdapterCache))
    }
    reader.endArray()
    return list
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: List<T>) {
    writer.beginArray()
    value.forEach {
      wrappedAdapter.toResponse(writer, responseAdapterCache, it)
    }
    writer.endArray()
  }
}

class NullableResponseAdapter<T : Any>(private val wrappedAdapter: ResponseAdapter<T>) : ResponseAdapter<T?> {
  init {
    check(wrappedAdapter !is NullableResponseAdapter<*>) {
      "The adapter is already nullable"
    }
  }

  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): T? {
    return if (reader.peek() == JsonReader.Token.NULL) {
      reader.skipValue()
      null
    } else {
      wrappedAdapter.fromResponse(reader, responseAdapterCache)
    }
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: T?) {
    if (value == null) {
      writer.nullValue()
    } else {
      wrappedAdapter.toResponse(writer, responseAdapterCache, value)
    }
  }
}

/**
 * ResponseAdapters can only express something that's present. Absent values are handled outside of the adapter
 */
class OptionalResponseAdapter<T>(private val wrappedAdapter: ResponseAdapter<T>) : ResponseAdapter<Optional.Present<T>> {
  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): Optional.Present<T> {
    return Optional.Present(wrappedAdapter.fromResponse(reader, responseAdapterCache))
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: Optional.Present<T>) {
    wrappedAdapter.toResponse(writer, responseAdapterCache, value.value)
  }
}

object StringResponseAdapter : ResponseAdapter<String> {
  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): String {
    return reader.nextString()!!
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: String) {
    writer.value(value)
  }
}

object IntResponseAdapter : ResponseAdapter<Int> {
  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): Int {
    return reader.nextInt()
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: Int) {
    writer.value(value)
  }
}

object DoubleResponseAdapter : ResponseAdapter<Double> {
  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): Double {
    return reader.nextDouble()
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: Double) {
    writer.value(value)
  }
}

object BooleanResponseAdapter : ResponseAdapter<Boolean> {
  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): Boolean {
    return reader.nextBoolean()
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: Boolean) {
    writer.value(value)
  }
}

object AnyResponseAdapter : ResponseAdapter<Any> {
  fun fromResponse(reader: JsonReader): Any {
    return reader.readRecursively()!!
  }

  fun toResponse(writer: JsonWriter, value: Any) {
    Utils.writeToJson(value, writer)
  }

  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): Any {
    return fromResponse(reader)
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: Any) {
    toResponse(writer, value)
  }
}

object UploadResponseAdapter : ResponseAdapter<Upload> {
  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): Upload {
    error("File Upload used in output position")
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: Upload) {
    writer.value(value)
  }
}

class ObjectResponseAdapter<T>(
    private val wrappedAdapter: ResponseAdapter<T>,
    private val buffered: Boolean
) : ResponseAdapter<T> {
  override fun fromResponse(reader: JsonReader, responseAdapterCache: ResponseAdapterCache): T {
    val actualReader = if (buffered) {
      reader.buffer()
    } else {
      reader
    }
    actualReader.beginObject()
    return wrappedAdapter.fromResponse(actualReader, responseAdapterCache).also {
      actualReader.endObject()
    }
  }

  override fun toResponse(writer: JsonWriter, responseAdapterCache: ResponseAdapterCache, value: T) {
    if (buffered && writer !is MapJsonWriter) {
      /**
       * Convert to a Map first
       */
      val mapWriter = MapJsonWriter()
      mapWriter.beginObject()
      wrappedAdapter.toResponse(mapWriter, responseAdapterCache, value)
      mapWriter.endObject()

      /**
       * And write to the original writer
       */
      AnyResponseAdapter.toResponse(writer, mapWriter.root()!!)
    } else {
      writer.beginObject()
      wrappedAdapter.toResponse(writer, responseAdapterCache, value)
      writer.endObject()
    }
  }
}

fun <T : Any> ResponseAdapter<T>.nullable() = NullableResponseAdapter(this)
fun <T> ResponseAdapter<T>.list() = ListResponseAdapter(this)
fun <T> ResponseAdapter<T>.obj(buffered: Boolean = false) = ObjectResponseAdapter(this, buffered)
fun <T> ResponseAdapter<T>.optional() = OptionalResponseAdapter(this)

/**
 * Global instances of nullable adapters for built-in scalar types
 */
@SharedImmutable
val NullableStringResponseAdapter = StringResponseAdapter.nullable()
@SharedImmutable
val NullableDoubleResponseAdapter = DoubleResponseAdapter.nullable()
@SharedImmutable
val NullableIntResponseAdapter = IntResponseAdapter.nullable()
@SharedImmutable
val NullableBooleanResponseAdapter = BooleanResponseAdapter.nullable()
@SharedImmutable
val NullableAnyResponseAdapter = AnyResponseAdapter.nullable()
