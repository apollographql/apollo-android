package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.internal.cache.normalized.NoOpApolloStore;
import com.apollographql.apollo.internal.cache.normalized.ReadableStore;
import com.apollographql.apollo.internal.cache.normalized.ResponseNormalizer;
import com.apollographql.apollo.internal.cache.normalized.Transaction;
import com.apollographql.apollo.internal.cache.normalized.WriteableStore;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;

/**
 * ApolloStore exposes a thread-safe api to access a {@link com.apollographql.apollo.cache.normalized.NormalizedCache}.
 * It also maintains a list of {@link RecordChangeSubscriber} that will be notified with changed records.
 *
 * Most clients should have no need to directly interface with an {@link ApolloStore}.
 */
public interface ApolloStore {

  /**
   * Listens to changed record keys dispatched via {@link #publish(Set)}.
   */
  interface RecordChangeSubscriber {

    /**
     * @param changedRecordKeys A set of record keys which correspond to records which have had content changes.
     */
    void onCacheRecordsChanged(Set<String> changedRecordKeys);
  }

  void subscribe(RecordChangeSubscriber subscriber);

  void unsubscribe(RecordChangeSubscriber subscriber);

  /**
   * @param keys A set of keys of {@link Record} which have changed.
   */
  void publish(Set<String> keys);

  /**
   * Clear all records from this {@link ApolloStore}.
   *
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with {@code true} if all records was
   * successfully removed, {@code false} otherwise
   */
  @Nonnull ApolloStoreOperation<Boolean> clearAll();

  /**
   * Remove cache record by the key
   *
   * @param cacheKey of record to be removed
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with {@code true} if record with such key
   * was successfully removed, {@code false} otherwise
   */
  @Nonnull ApolloStoreOperation<Boolean> remove(@Nonnull CacheKey cacheKey);

  /**
   * Remove cache records by the list of keys
   *
   * @param cacheKeys keys of records to be removed
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with the count of records been removed
   */
  @Nonnull ApolloStoreOperation<Integer> remove(@Nonnull List<CacheKey> cacheKeys);

  /**
   * @return The {@link ResponseNormalizer} used to generate normalized records from the network.
   */
  ResponseNormalizer<Map<String, Object>> networkResponseNormalizer();

  /**
   * @return The {@link ResponseNormalizer} used to generate normalized records from the cache.
   */
  ResponseNormalizer<Record> cacheResponseNormalizer();

  /**
   * Run a operation inside a read-lock. Blocks until read-lock is acquired.
   *
   * @param transaction A code block to run once the read lock is acquired.
   * @param <R>         The result type of this read operation.
   * @return A result from the read operation.
   */
  <R> R readTransaction(Transaction<ReadableStore, R> transaction);

  /**
   * Run a operation inside a write-lock. Blocks until write-lock is acquired.
   *
   * @param transaction A code block to run once the write lock is acquired.
   * @param <R>         The result type of this write operation.
   * @return A result from the write operation.
   */
  <R> R writeTransaction(Transaction<WriteableStore, R> transaction);

  /**
   * @return The {@link NormalizedCache} which backs this ApolloStore.
   */
  NormalizedCache normalizedCache();

  /**
   * @return the {@link CacheKeyResolver} used for resolving field cache keys
   */
  CacheKeyResolver cacheKeyResolver();

  /**
   * Read GraphQL query from store.
   *
   * @param query to be read
   * @param <D>   type of GraphQL query data
   * @param <T>   type query cached data will be wrapped with
   * @param <V>   type of query variables
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with cached data for specified query
   */
  @Nonnull <D extends Query.Data, T, V extends Query.Variables> ApolloStoreOperation<T> read(
      @Nonnull Query<D, T, V> query);

  /**
   * Read GraphQL query response from store.
   *
   * @param query               response of which should be read
   * @param responseFieldMapper {@link ResponseFieldMapper} to be used for field mapping
   * @param responseNormalizer  {@link ResponseNormalizer} to be used when reading cached response
   * @param cacheHeaders        {@link CacheHeaders} to be used when reading cached response
   * @param <D>                 type of GraphQL query data
   * @param <T>                 type query cached data will be wrapped with
   * @param <V>                 type of query variables
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with cached response for specified query
   */
  @Nonnull <D extends Query.Data, T, V extends Query.Variables> ApolloStoreOperation<Response<T>> read(
      @Nonnull Query<D, T, V> query, @Nonnull ResponseFieldMapper<D> responseFieldMapper,
      @Nonnull ResponseNormalizer<Record> responseNormalizer, @Nonnull CacheHeaders cacheHeaders);

  /**
   * Read GraphQL fragment from store.
   *
   * @param fieldMapper {@link ResponseFieldMapper} to be used for field mapping
   * @param cacheKey    {@link CacheKey} to be used to find cache record for the fragment
   * @param variables   {@link Query.Variables} required for fragment arguments resolving
   * @param <F>         type of fragment to be read
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with cached fragment data
   */
  @Nonnull <F extends GraphqlFragment> ApolloStoreOperation<F> read(@Nonnull ResponseFieldMapper<F> fieldMapper,
      @Nonnull CacheKey cacheKey, @Nonnull Query.Variables variables);

  /**
   * Write query to the store.
   *
   * @param query {@link Query} response data of which should be written to the store
   * @param data  {@link Query.Data} query response data to be written to the store
   * @param <D>   type of GraphQL query data
   * @param <T>   type query cached data will be wrapped with
   * @param <V>   type of query variables
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with set of keys of {@link Record} which
   * have changed
   */
  @Nonnull <D extends Query.Data, T, V extends Query.Variables> ApolloStoreOperation<Set<String>> write(
      @Nonnull Query<D, T, V> query, @Nonnull D data);

  /**
   * Write query to the store and publish changes of {@link Record} which have changed, that will notify any {@link
   * com.apollographql.apollo.ApolloQueryWatcher} that depends on these {@link Record} to re-fetch.
   *
   * @param query {@link Query} response data of which should be written to the store
   * @param data  {@link Query.Data} query response data to be written to the store
   * @param <D>   type of GraphQL query data
   * @param <T>   type query cached data will be wrapped with
   * @param <V>   type of query variables
   * @return {@ApolloStoreOperation} to be performed
   */
  @Nonnull <D extends Query.Data, T, V extends Query.Variables> ApolloStoreOperation<Boolean> writeAndPublish(
      @Nonnull Query<D, T, V> query, @Nonnull D data);

  /**
   * Write fragment to the store.
   *
   * @param fragment data to be written to the store
   * @param cacheKey {@link CacheKey} to be used as root record key
   * @param {@link   Query.Variables} required for fragment arguments resolving
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with set of keys of {@link Record} which
   * have changed
   */
  @Nonnull ApolloStoreOperation<Set<String>> write(@Nonnull GraphqlFragment fragment, @Nonnull CacheKey cacheKey,
      @Nonnull Query.Variables variables);

  /**
   * Write fragment to the store and publish changes of {@link Record} which have changed, that will notify any {@link
   * com.apollographql.apollo.ApolloQueryWatcher} that depends on these {@link Record} to re-fetch.
   *
   * @param fragment data to be written to the store
   * @param cacheKey {@link CacheKey} to be used as root record key
   * @param {@link   Query.Variables} required for fragment arguments resolving
   * @return {@ApolloStoreOperation} to be performed
   */
  @Nonnull ApolloStoreOperation<Boolean> writeAndPublish(@Nonnull GraphqlFragment fragment, @Nonnull CacheKey cacheKey,
      @Nonnull Query.Variables variables);

  /**
   * Write query data to the optimistic store.
   *
   * @param query      {@link Query} response data of which should be written to the store
   * @param data       {@link Query.Data} query response data to be written to the store
   * @param mutationId mutation unique identifier
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with set of keys of {@link Record} which
   * have changed
   */
  @Nonnull <D extends Query.Data, T, V extends Query.Variables> ApolloStoreOperation<Set<String>>
  writeOptimisticUpdates(@Nonnull Query<D, T, V> query, @Nonnull D data, @Nonnull UUID mutationId);

  /**
   * Write query data to the optimistic store and publish changes of {@link Record}s which have changed, that will
   * notify any {@linkcom.apollographql.apollo.ApolloQueryWatcher} that depends on these {@link Record} to re-fetch.
   *
   * @param query      {@link Query} response data of which should be written to the store
   * @param data       {@link Query.Data} query response data to be written to the store
   * @param mutationId mutation unique identifier
   * @return {@ApolloStoreOperation} to be performed
   */
  @Nonnull <D extends Query.Data, T, V extends Query.Variables> ApolloStoreOperation<Boolean>
  writeOptimisticUpdatesAndPublish(@Nonnull Query<D, T, V> query, @Nonnull D data, @Nonnull UUID mutationId);

  /**
   * Rollback query data optimistic updates.
   *
   * @param mutationId mutation unique identifier
   * @return {@ApolloStoreOperation} to be performed
   */
  @Nonnull ApolloStoreOperation<Set<String>> rollbackOptimisticUpdates(@Nonnull UUID mutationId);

  /**
   * Rollback query data optimistic updates and publish changes of {@link Record}s which have changed, that will notify
   * any {@linkcom.apollographql.apollo.ApolloQueryWatcher} that depends on these {@link Record} to re-fetch.
   *
   * @param mutationId mutation unique identifier
   * @return {@ApolloStoreOperation} to be performed, that will be resolved with set of keys of {@link Record} which
   * have changed
   */
  @Nonnull ApolloStoreOperation<Boolean> rollbackOptimisticUpdatesAndPublish(@Nonnull UUID mutationId);

  ApolloStore NO_APOLLO_STORE = new NoOpApolloStore();
}
