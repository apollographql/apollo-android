package com.apollographql.apollo3.api.internal

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Error
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.CustomScalarAdpaters
import com.apollographql.apollo3.api.internal.json.BufferedSourceJsonReader
import com.apollographql.apollo3.api.internal.json.MapJsonReader
import com.apollographql.apollo3.api.internal.json.Utils.readRecursively
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.use
import com.apollographql.apollo3.api.nullable
import com.benasher44.uuid.uuid4
import okio.BufferedSource

/**
 * [ResponseBodyParser] parses network responses, including data, errors and extensions from a [JsonReader]
 *
 * That will avoid the cost of having to create an entire Map in memory
 */
object ResponseBodyParser {
  fun <D : Operation.Data> parse(
      jsonReader: JsonReader,
      operation: Operation<D>,
      responseAdapterCache: CustomScalarAdpaters
  ): ApolloResponse<D> {
    jsonReader.beginObject()

    var data: D? = null
    var errors: List<Error>? = null
    var extensions: Map<String, Any?>? = null
    while (jsonReader.hasNext()) {
      when (jsonReader.nextName()) {
        "data" -> data = operation.adapter().nullable().fromJson(jsonReader, responseAdapterCache)
        "errors" -> errors = jsonReader.readErrors()
        "extensions" -> extensions = jsonReader.readRecursively() as Map<String, Any?>
        else -> jsonReader.skipValue()
      }
    }

    jsonReader.endObject()

    return ApolloResponse(
        requestUuid = uuid4(),
        operation = operation,
        data = data,
        errors = errors,
        extensions = extensions.orEmpty()
    )
  }

  fun <D : Operation.Data> parse(
      source: BufferedSource,
      operation: Operation<D>,
      responseAdapterCache: CustomScalarAdpaters
  ): ApolloResponse<D> {
    return BufferedSourceJsonReader(source).use { jsonReader ->
      parse(jsonReader, operation, responseAdapterCache)
    }
  }

  fun <D : Operation.Data> parse(
      payload: Map<String, Any?>,
      operation: Operation<D>,
      responseAdapterCache: CustomScalarAdpaters,
  ): ApolloResponse<D> {
    return parse(
        MapJsonReader(payload),
        operation = operation,
        responseAdapterCache = responseAdapterCache
    )
  }

  fun parseError(
      payload: Map<String, Any?>,
  ) = payload.readError()

  @Suppress("UNCHECKED_CAST")
  private fun JsonReader.readErrors(): List<Error> {
    val responseErrors = readRecursively() as? List<Map<String, Any?>>
    return responseErrors?.let {
      it.map { errorPayload -> errorPayload.readError() }
    }.orEmpty()
  }

  @Suppress("UNCHECKED_CAST")
  private fun Map<String, Any?>.readError(): Error {
    var message = ""
    var locations = emptyList<Error.Location>()
    val customAttributes = mutableMapOf<String, Any?>()
    for ((key, value) in this) {
      when (key) {
        "message" -> message = value?.toString() ?: ""
        "locations" -> {
          val locationItems = value as? List<Map<String, Any?>>
          locations = locationItems?.map { it.readErrorLocation() } ?: emptyList()
        }
        else -> customAttributes[key] = value
      }
    }
    return Error(message, locations, customAttributes)
  }

  private fun Map<String, Any?>?.readErrorLocation(): Error.Location {
    var line: Long = -1
    var column: Long = -1
    if (this != null) {
      for ((key, value) in this) {
        when (key) {
          "line" -> line = (value as Number).toLong()
          "column" -> column = (value as Number).toLong()
        }
      }
    }
    return Error.Location(line, column)
  }
}
