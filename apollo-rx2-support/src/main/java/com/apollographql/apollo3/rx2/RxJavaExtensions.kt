@file:Suppress("NOTHING_TO_INLINE")
@file:JvmName("KotlinExtensions")

package com.apollographql.apollo.rx2

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloMutationCall
import com.apollographql.apollo.ApolloPrefetch
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.ApolloQueryWatcher
import com.apollographql.apollo.ApolloSubscriptionCall
import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.Subscription
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue

@JvmSynthetic
@CheckReturnValue
inline fun ApolloPrefetch.rx(): Completable =
    Rx2Apollo.from(this)

@JvmSynthetic
@CheckReturnValue
inline fun <D : Operation.Data> ApolloQueryWatcher<D>.rx(): Observable<Response<D>> =
    Rx2Apollo.from(this)

@JvmSynthetic
@CheckReturnValue
inline fun <D : Operation.Data> ApolloCall<D>.rx(): Observable<Response<D>> =
    Rx2Apollo.from(this)

@JvmSynthetic
@CheckReturnValue
inline fun <D : Operation.Data> ApolloSubscriptionCall<D>.rx(
    backpressureStrategy: BackpressureStrategy = BackpressureStrategy.LATEST
): Flowable<Response<D>> = Rx2Apollo.from(this, backpressureStrategy)

/**
 * Creates a new [ApolloQueryCall] call and then converts it to an [Observable].
 *
 * The number of emissions this Observable will have is based on the
 * [com.apollographql.apollo.fetcher.ResponseFetcher] used with the call.
 */
@JvmSynthetic
@CheckReturnValue
inline fun <D : Operation.Data> ApolloClient.rxQuery(
    query: Query<D>,
    configure: ApolloQueryCall<D>.() -> ApolloQueryCall<D> = { this }
): Observable<Response<D>> = query(query).configure().rx()

/**
 * Creates a new [ApolloMutationCall] call and then converts it to a [Single].
 */
@JvmSynthetic
@CheckReturnValue
inline fun <D : Operation.Data> ApolloClient.rxMutate(
    mutation: Mutation<D>,
    configure: ApolloMutationCall<D>.() -> ApolloMutationCall<D> = { this }
): Single<Response<D>> = mutate(mutation).configure().rx().singleOrError()

/**
 * Creates a new [ApolloMutationCall] call and then converts it to a [Single].
 *
 * Provided optimistic updates will be stored in [com.apollographql.apollo.cache.normalized.ApolloStore]
 * immediately before mutation execution. Any [ApolloQueryWatcher] dependent on the changed cache records will
 * be re-fetched.
 */
@JvmSynthetic
@CheckReturnValue
inline fun <D : Operation.Data> ApolloClient.rxMutate(
    mutation: Mutation<D>,
    withOptimisticUpdates: D,
    configure: ApolloMutationCall<D>.() -> ApolloMutationCall<D> = { this }
): Single<Response<D>> = mutate(mutation, withOptimisticUpdates).configure().rx().singleOrError()

/**
 * Creates the [ApolloPrefetch] by wrapping the operation object inside and then converts it to a [Completable].
 */
@JvmSynthetic
@CheckReturnValue
inline fun <D : Operation.Data> ApolloClient.rxPrefetch(
    operation: Operation<D>
): Completable = prefetch(operation).rx()

/**
 * Creates a new [ApolloSubscriptionCall] call and then converts it to a [Flowable].
 *
 * Back-pressure strategy can be provided via [backpressureStrategy] parameter. The default value is [BackpressureStrategy.LATEST]
 */
@JvmSynthetic
@CheckReturnValue
inline fun <D : Operation.Data> ApolloClient.rxSubscribe(
    subscription: Subscription<D>,
    backpressureStrategy: BackpressureStrategy = BackpressureStrategy.LATEST
): Flowable<Response<D>> = subscribe(subscription).rx(backpressureStrategy)
