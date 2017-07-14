package com.apollographql.apollo.internal.cache.normalized;

import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.internal.field.CacheFieldValueResolver;
import com.apollographql.apollo.internal.reader.RealResponseReader;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.apollographql.apollo.api.internal.Utils.checkNotNull;

public final class RealApolloStore implements ApolloStore, ReadableStore, WriteableStore {
  private final NormalizedCache normalizedCache;
  private final CacheKeyResolver cacheKeyResolver;
  private final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
  private final ReadWriteLock lock;
  private final Set<RecordChangeSubscriber> subscribers;

  public RealApolloStore(@Nonnull NormalizedCache normalizedCache, @Nonnull CacheKeyResolver cacheKeyResolver,
      @Nonnull final Map<ScalarType, CustomTypeAdapter> customTypeAdapters) {
    this.normalizedCache = checkNotNull(normalizedCache, "cacheStore == null");
    this.cacheKeyResolver = checkNotNull(cacheKeyResolver, "cacheKeyResolver == null");
    this.customTypeAdapters = checkNotNull(customTypeAdapters, "customTypeAdapters == null");
    this.lock = new ReentrantReadWriteLock();
    this.subscribers = Collections.newSetFromMap(new WeakHashMap<RecordChangeSubscriber, Boolean>());
  }

  @Override public ResponseNormalizer<Map<String, Object>> networkResponseNormalizer() {
    return new ResponseNormalizer<Map<String, Object>>() {
      @Nonnull @Override public CacheKey resolveCacheKey(@Nonnull ResponseField field,
          @Nonnull Map<String, Object> record) {
        return cacheKeyResolver.fromFieldRecordSet(field, record);
      }
    };
  }

  @Override public ResponseNormalizer<Record> cacheResponseNormalizer() {
    return new ResponseNormalizer<Record>() {
      @Nonnull @Override public CacheKey resolveCacheKey(@Nonnull ResponseField field, @Nonnull Record record) {
        return CacheKey.from(record.key());
      }
    };
  }

  @Override public synchronized void subscribe(RecordChangeSubscriber subscriber) {
    subscribers.add(subscriber);
  }

  @Override public synchronized void unsubscribe(RecordChangeSubscriber subscriber) {
    subscribers.remove(subscriber);
  }

  @Override public void publish(@Nonnull Set<String> changedKeys) {
    checkNotNull(changedKeys, "changedKeys == null");
    if (changedKeys.isEmpty()) {
      return;
    }
    Set<RecordChangeSubscriber> iterableSubscribers;
    synchronized (this) {
      iterableSubscribers = new LinkedHashSet<>(subscribers);
    }
    for (RecordChangeSubscriber subscriber : iterableSubscribers) {
      subscriber.onCacheRecordsChanged(changedKeys);
    }
  }

  @Override public void clearAll() {
    writeTransaction(new Transaction<WriteableStore, Boolean>() {
      @Override public Boolean execute(WriteableStore cache) {
        normalizedCache.clearAll();
        return true;
      }
    });
  }

  @Override public boolean remove(@Nonnull final CacheKey cacheKey) {
    checkNotNull(cacheKey, "cacheKey == null");
    return writeTransaction(new Transaction<WriteableStore, Boolean>() {
      @Override public Boolean execute(WriteableStore cache) {
        return normalizedCache.remove(cacheKey);
      }
    });
  }

  @Override public <R> R readTransaction(Transaction<ReadableStore, R> transaction) {
    lock.readLock().lock();
    try {
      return transaction.execute(RealApolloStore.this);
    } finally {
      lock.readLock().unlock();
    }
  }

  @Override public <R> R writeTransaction(Transaction<WriteableStore, R> transaction) {
    lock.writeLock().lock();
    try {
      return transaction.execute(RealApolloStore.this);
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override public NormalizedCache normalizedCache() {
    return normalizedCache;
  }

  @Nullable public Record read(@Nonnull String key, @Nonnull CacheHeaders cacheHeaders) {
    return normalizedCache.loadRecord(checkNotNull(key, "key == null"), cacheHeaders);
  }

  @Nonnull public Collection<Record> read(@Nonnull Collection<String> keys, @Nonnull CacheHeaders cacheHeaders) {
    return normalizedCache.loadRecords(checkNotNull(keys, "keys == null"), cacheHeaders);
  }

  @Nonnull public Set<String> merge(@Nonnull Collection<Record> recordSet, @Nonnull CacheHeaders cacheHeaders) {
    return normalizedCache.merge(checkNotNull(recordSet, "recordSet == null"), cacheHeaders);
  }

  @Override public CacheKeyResolver cacheKeyResolver() {
    return cacheKeyResolver;
  }

  @Nullable @Override public <D extends Operation.Data, T, V extends Operation.Variables> T read(
      @Nonnull final Operation<D, T, V> operation) {
    checkNotNull(operation, "operation == null");
    return readTransaction(new Transaction<ReadableStore, T>() {
      @Nullable @Override public T execute(ReadableStore cache) {
        Record rootRecord = cache.read(CacheKeyResolver.rootKeyForOperation(operation).key(), CacheHeaders.NONE);
        if (rootRecord == null) {
          return null;
        }

        ResponseFieldMapper<D> responseFieldMapper = operation.responseFieldMapper();
        CacheFieldValueResolver fieldValueResolver = new CacheFieldValueResolver(cache, operation.variables(),
            cacheKeyResolver(), CacheHeaders.NONE);
        //noinspection unchecked
        RealResponseReader<Record> responseReader = new RealResponseReader<>(operation.variables(), rootRecord,
            fieldValueResolver, customTypeAdapters, ResponseNormalizer.NO_OP_NORMALIZER);
        return operation.wrapData(responseFieldMapper.map(responseReader));
      }
    });
  }

  @Nonnull @Override public <D extends Operation.Data, T, V extends Operation.Variables> Response<T> read(
      @Nonnull final Operation<D, T, V> operation, @Nonnull final ResponseFieldMapper<D> responseFieldMapper,
      @Nonnull final ResponseNormalizer<Record> responseNormalizer, @Nonnull final CacheHeaders cacheHeaders) {
    checkNotNull(operation, "operation == null");
    checkNotNull(responseNormalizer, "responseNormalizer == null");
    checkNotNull(customTypeAdapters, "customTypeAdapters == null");

    return readTransaction(new Transaction<ReadableStore, Response<T>>() {
      @Nonnull @Override public Response<T> execute(ReadableStore cache) {
        Record rootRecord = cache.read(CacheKeyResolver.rootKeyForOperation(operation).key(), cacheHeaders);
        if (rootRecord == null) {
          return Response.<T>builder(operation).fromCache(true).build();
        }

        CacheFieldValueResolver fieldValueResolver = new CacheFieldValueResolver(cache, operation.variables(),
            cacheKeyResolver(), cacheHeaders);
        RealResponseReader<Record> responseReader = new RealResponseReader<>(operation.variables(), rootRecord,
            fieldValueResolver, customTypeAdapters, responseNormalizer);
        responseNormalizer.willResolveRootQuery(operation);
        T data = operation.wrapData(responseFieldMapper.map(responseReader));
        return Response.<T>builder(operation)
            .data(data)
            .fromCache(true)
            .dependentKeys(responseNormalizer.dependentKeys())
            .build();
      }
    });
  }

  @Nullable @Override public <F extends GraphqlFragment> F read(
      @Nonnull final ResponseFieldMapper<F> responseFieldMapper, @Nonnull final CacheKey cacheKey,
      @Nonnull final Operation.Variables variables) {
    checkNotNull(responseFieldMapper, "responseFieldMapper == null");
    checkNotNull(cacheKey, "cacheKey == null");
    checkNotNull(variables, "variables == null");

    return readTransaction(new Transaction<ReadableStore, F>() {
      @Nullable @Override public F execute(ReadableStore cache) {
        Record rootRecord = cache.read(cacheKey.key(), CacheHeaders.NONE);
        if (rootRecord == null) {
          return null;
        }

        CacheFieldValueResolver fieldValueResolver = new CacheFieldValueResolver(cache, variables, cacheKeyResolver(),
            CacheHeaders.NONE);
        //noinspection unchecked
        RealResponseReader<Record> responseReader = new RealResponseReader<>(variables, rootRecord, fieldValueResolver,
            customTypeAdapters, ResponseNormalizer.NO_OP_NORMALIZER);
        return responseFieldMapper.map(responseReader);
      }
    });
  }

  @Override @Nonnull public <D extends Operation.Data, T, V extends Operation.Variables> Set<String> write(
      @Nonnull final Operation<D, T, V> operation, @Nonnull final D operationData) {
    checkNotNull(operation, "operation == null");
    checkNotNull(operationData, "operationData == null");
    return writeTransaction(new Transaction<WriteableStore, Set<String>>() {
      @Override public Set<String> execute(WriteableStore cache) {
        CacheResponseWriter cacheResponseWriter = new CacheResponseWriter(operation.variables(), customTypeAdapters);
        operationData.marshaller().marshal(cacheResponseWriter);
        ResponseNormalizer<Map<String, Object>> responseNormalizer = networkResponseNormalizer();
        responseNormalizer.willResolveRootQuery(operation);
        Collection<Record> records = cacheResponseWriter.normalize(responseNormalizer);
        return merge(records, CacheHeaders.NONE);
      }
    });
  }

  @Override public <D extends Operation.Data, T, V extends Operation.Variables> void writeAndPublish(
      @Nonnull Operation<D, T, V> operation, @Nonnull D operationData) {
    Set<String> changedKeys = write(operation, operationData);
    publish(changedKeys);
  }

  @Override @Nonnull public Set<String> write(@Nonnull final GraphqlFragment fragment, @Nonnull final CacheKey cacheKey,
      @Nonnull final Operation.Variables variables) {
    checkNotNull(fragment, "fragment == null");
    checkNotNull(cacheKey, "cacheKey == null");
    checkNotNull(variables, "operation == null");

    if (cacheKey == CacheKey.NO_KEY) {
      throw new IllegalArgumentException("undefined cache key");
    }

    return writeTransaction(new Transaction<WriteableStore, Set<String>>() {
      @Override public Set<String> execute(WriteableStore cache) {
        CacheResponseWriter cacheResponseWriter = new CacheResponseWriter(variables, customTypeAdapters);
        fragment.marshaller().marshal(cacheResponseWriter);
        ResponseNormalizer<Map<String, Object>> responseNormalizer = networkResponseNormalizer();
        responseNormalizer.willResolveRecord(cacheKey);
        Collection<Record> records = cacheResponseWriter.normalize(responseNormalizer);
        return merge(records, CacheHeaders.NONE);
      }
    });
  }

  @Override public void writeAndPublish(@Nonnull GraphqlFragment fragment, @Nonnull CacheKey cacheKey,
      @Nonnull Operation.Variables variables) {
    Set<String> changedKeys = write(fragment, cacheKey, variables);
    publish(changedKeys);
  }
}
