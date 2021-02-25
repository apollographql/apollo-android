package com.apollographql.apollo3

import com.apollographql.apollo3.api.ExecutionContext
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.Mutation
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.Subscription
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

sealed class ApolloRequest<D : Operation.Data> {
  abstract val requestUuid: Uuid
  abstract val operation: Operation<D>
  abstract val executionContext: ExecutionContext

  abstract fun newBuilder(): Builder<D>

  interface Builder<D : Operation.Data> {
    fun addExecutionContext(executionContext: ExecutionContext): Builder<D>
    fun build(): ApolloRequest<D>
  }
}

class ApolloQueryRequest<D : Operation.Data> internal constructor(
    val query: Query<D>,
    override val executionContext: ExecutionContext
) : ApolloRequest<D>() {
  override val requestUuid = uuid4()
  override val operation = query

  override fun newBuilder() = Builder(query = query, executionContext = executionContext)

  class Builder<D : Operation.Data> constructor(
      internal val query: Query<D>,
      internal var executionContext: ExecutionContext = ExecutionContext.Empty
  ): ApolloRequest.Builder<D> {

    override fun build(): ApolloQueryRequest<D> {
      return ApolloQueryRequest(query, executionContext = executionContext)
    }

    override fun addExecutionContext(executionContext: ExecutionContext) = apply{
      this.executionContext = this.executionContext + executionContext
    }
  }
}

class ApolloMutationRequest<D : Operation.Data> internal constructor(
    val mutation: Mutation<D>,
    override val executionContext: ExecutionContext
) : ApolloRequest<D>() {
  override val requestUuid = uuid4()
  override val operation = mutation

  override fun newBuilder() = Builder(mutation = mutation, executionContext = executionContext)

  class Builder<D : Operation.Data> constructor(
      internal val mutation: Mutation<D>,
      internal var executionContext: ExecutionContext = ExecutionContext.Empty
  ): ApolloRequest.Builder<D> {

    override fun build(): ApolloMutationRequest<D> {
      return ApolloMutationRequest(mutation, executionContext = executionContext)
    }

    override fun addExecutionContext(executionContext: ExecutionContext) = apply{
      this.executionContext = this.executionContext + executionContext
    }
  }
}

class ApolloSubscriptionRequest<D : Operation.Data> internal constructor(
    val subscription: Subscription<D>,
    override val executionContext: ExecutionContext
) : ApolloRequest<D>() {
  override val requestUuid = uuid4()
  override val operation = subscription

  override fun newBuilder() = Builder(subscription = subscription, executionContext = executionContext)

  class Builder<D : Operation.Data> constructor(
      internal val subscription: Subscription<D>,
      internal var executionContext: ExecutionContext = ExecutionContext.Empty
  ): ApolloRequest.Builder<D> {

    override fun build(): ApolloSubscriptionRequest<D> {
      return ApolloSubscriptionRequest(subscription, executionContext = executionContext)
    }

    override fun addExecutionContext(executionContext: ExecutionContext) = apply{
      this.executionContext = this.executionContext + executionContext
    }
  }
}
