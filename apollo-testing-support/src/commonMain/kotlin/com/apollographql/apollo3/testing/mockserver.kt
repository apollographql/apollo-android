package com.apollographql.apollo3.testing

import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.composeResponseBody
import com.apollographql.apollo3.mockserver.MockServer
import com.apollographql.apollo3.mockserver.enqueue

fun <D : Operation.Data> MockServer.enqueue(
    operation: Operation<D>,
    data: D,
    customScalarAdapters: CustomScalarAdapters = CustomScalarAdapters.Empty,
    delayMs: Long = 0
) {
  val json = operation.composeResponseBody(data, customScalarAdapters = customScalarAdapters)
  enqueue(json, delayMs)
}

