package com.apollographql.apollo3

import com.apollographql.apollo3.api.ExecutionContext
import com.apollographql.apollo3.api.Mutation
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.CustomScalar
import com.apollographql.apollo3.api.ResponseAdapter
import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.Subscription
import com.apollographql.apollo3.dispatcher.ApolloCoroutineDispatcher
import com.apollographql.apollo3.interceptor.ApolloRequestInterceptor
import com.apollographql.apollo3.interceptor.NetworkRequestInterceptor
import com.apollographql.apollo3.internal.RealApolloCall
import com.apollographql.apollo3.network.NetworkTransport
import com.apollographql.apollo3.network.http.ApolloHttpNetworkTransport
import kotlinx.coroutines.Dispatchers

/**
 * The main entry point for the Apollo runtime. An [ApolloClient] is responsible for executing queries, mutations and subscriptions
 */
class ApolloClient private constructor(
    private val networkTransport: NetworkTransport,
    private val subscriptionNetworkTransport: NetworkTransport,
    private val responseAdapterCache: ResponseAdapterCache,
    private val interceptors: List<ApolloRequestInterceptor>,
    private val executionContext: ExecutionContext
) {
  private val executionContextWithDefaults: ExecutionContext = if (executionContext[ApolloCoroutineDispatcher] == null) {
    executionContext + ApolloCoroutineDispatcher(Dispatchers.Default)
  } else {
    executionContext
  }

  fun <D : Operation.Data> query(query: Query<D>): ApolloQueryCall<D> {
    return ApolloQueryRequest.Builder(query).build().prepareCall()
  }

  fun <D : Operation.Data> mutate(mutation: Mutation<D>): ApolloMutationCall<D> {
    return ApolloMutationRequest.Builder(mutation).build().prepareCall()
  }


  fun <D : Operation.Data> subscribe(query: Subscription<D>): ApolloQueryCall<D> {
    return ApolloSubscriptionRequest.Builder(query).build().prepareCall()
  }

  fun <D : Operation.Data> query(queryRequest: ApolloQueryRequest<D>): ApolloQueryCall<D> {
    return queryRequest.prepareCall()
  }

  fun <D : Operation.Data> mutate(mutationRequest: ApolloMutationRequest<D>): ApolloMutationCall<D> {
    return mutationRequest.prepareCall()
  }


  fun <D : Operation.Data> subscribe(subscriptionRequest: ApolloSubscriptionRequest<D>): ApolloQueryCall<D> {
    return subscriptionRequest.prepareCall()
  }

  private fun <D : Operation.Data> ApolloRequest<D>.prepareCall(): RealApolloCall<D> {
    return RealApolloCall(
        request = this.newBuilder().addExecutionContext(executionContextWithDefaults).build(),
        interceptors = interceptors + NetworkRequestInterceptor(
            networkTransport = networkTransport,
            subscriptionNetworkTransport = subscriptionNetworkTransport,
        ),
        responseAdapterCache = responseAdapterCache
    )
  }

  fun newBuilder(): Builder {
    return Builder()
        .networkTransport(networkTransport)
        .subscriptionNetworkTransport(subscriptionNetworkTransport)
        .scalarTypeAdapters(responseAdapterCache.customScalarResponseAdapters)
        .interceptors(interceptors)
        .executionContext(executionContext)
  }

  class Builder {
    private var customScalarAdapters = emptyMap<CustomScalar, ResponseAdapter<*>>()

    private var networkTransport: NetworkTransport? = null
    private var subscriptionNetworkTransport: NetworkTransport? = null
    private var interceptors: List<ApolloRequestInterceptor> = emptyList()
    private var executionContext: ExecutionContext = ExecutionContext.Empty

    fun serverUrl(serverUrl: String) = apply {
      networkTransport(ApolloHttpNetworkTransport(serverUrl = serverUrl, headers = emptyMap()))
    }

    fun <T> addScalarTypeAdapter(customScalar: CustomScalar, customScalarAdapter: ResponseAdapter<T>) = apply {
      this.customScalarAdapters = this.customScalarAdapters + (customScalar to customScalarAdapter)
    }

    fun networkTransport(networkTransport: NetworkTransport) = apply {
      check(this.networkTransport == null) {
        "ApolloGraphQL: networkTransport is already set. If you're using serverUrl(), you shouldn't call networkTransport() manually"
      }
      this.networkTransport = networkTransport
    }

    fun subscriptionNetworkTransport(subscriptionNetworkTransport: NetworkTransport) = apply {
      check(this.subscriptionNetworkTransport == null) {
        "ApolloGraphQL: subscriptionNetworkTransport is already set."
      }
      this.subscriptionNetworkTransport = subscriptionNetworkTransport
    }

    fun addInterceptor(interceptor: ApolloRequestInterceptor, executionContext: ExecutionContext = ExecutionContext.Empty) = apply {
      interceptors = interceptors + interceptor
      this.executionContext = this.executionContext + executionContext
    }

    fun build(): ApolloClient {
      val transport = networkTransport
      check(transport != null) {
        "ApolloGraphQL: no networkTransport, either call networkTransport() or serverUrl()"
      }
      val subscriptionTransport = subscriptionNetworkTransport ?: transport

      return ApolloClient(
          networkTransport = transport,
          subscriptionNetworkTransport = subscriptionTransport,
          responseAdapterCache = ResponseAdapterCache(customScalarAdapters),
          interceptors = interceptors,
          executionContext = executionContext
      )
    }

    /**
     * internal because only used from tests
     */
    internal fun interceptors(interceptors: List<ApolloRequestInterceptor>) = apply {
      check(this.interceptors.isEmpty()) {
        "ApolloGraphQL: interceptors is already set"
      }
      this.interceptors = interceptors
    }

    /**
     * Convenience overload of [interceptors] with variadic parameters
     */
    internal fun interceptors(vararg interceptors: ApolloRequestInterceptor) = apply {
      interceptors(interceptors.toList())
    }

    /**
     * internal because only used from tests
     */
    internal fun executionContext(executionContext: ExecutionContext) = apply {
      check(this.executionContext == ExecutionContext.Empty) {
        "ApolloGraphQL: executionContext is already set."
      }
      this.executionContext = executionContext
    }

    /**
     * internal because only used from tests
     */
    fun scalarTypeAdapters(customScalarAdapters: Map<CustomScalar, ResponseAdapter<*>>) = apply {
      this.customScalarAdapters = customScalarAdapters
    }
  }
}
