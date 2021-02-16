package com.apollographql.apollo3.interceptor

import com.apollographql.apollo3.ApolloRequest
import com.apollographql.apollo3.api.ApolloExperimental
import com.apollographql.apollo3.api.ResponseAdapterCache
import com.apollographql.apollo3.api.Operation
import kotlinx.coroutines.flow.Flow

@ApolloExperimental
interface ApolloInterceptorChain {
  val responseAdapterCache: ResponseAdapterCache

  fun <D : Operation.Data> proceed(request: ApolloRequest<D>): Flow<ApolloResponse<D>>

  fun canProceed(): Boolean

}

@ApolloExperimental
interface ApolloRequestInterceptor {
  fun <D : Operation.Data> intercept(request: ApolloRequest<D>, chain: ApolloInterceptorChain): Flow<ApolloResponse<D>>
}

@ApolloExperimental
internal class RealInterceptorChain(
    private val interceptors: List<ApolloRequestInterceptor>,
    private val index: Int,
    override val responseAdapterCache: ResponseAdapterCache,
) : ApolloInterceptorChain {

  override fun <D : Operation.Data> proceed(request: ApolloRequest<D>): Flow<ApolloResponse<D>> {
    check(index < interceptors.size)
    return interceptors[index].intercept(
        request,
        RealInterceptorChain(
            interceptors = interceptors,
            index = index + 1,
            responseAdapterCache = responseAdapterCache
        )
    )
  }

  override fun canProceed(): Boolean = index < interceptors.size
}
