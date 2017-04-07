package com.apollographql.apollo.cache.normalized.lru;

import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.Record;
import com.nytimes.android.external.cache.Cache;
import com.nytimes.android.external.cache.CacheBuilder;
import com.nytimes.android.external.cache.Weigher;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class LruNormalizedCache extends NormalizedCache {

  private final Cache<String, Record> lruCache;
  private final Optional<NormalizedCache> secondaryCacheStore;

  public LruNormalizedCache(EvictionPolicy evictionPolicy) {
    this(evictionPolicy, null);
  }

  public LruNormalizedCache(EvictionPolicy evictionPolicy, NormalizedCache secondaryNormalizedCache) {
    this.secondaryCacheStore = Optional.fromNullable(secondaryNormalizedCache);
    final CacheBuilder<Object, Object> lruCacheBuilder = CacheBuilder.newBuilder();
    if (evictionPolicy.maxSizeBytes().isPresent()) {
      lruCacheBuilder.maximumWeight(evictionPolicy.maxSizeBytes().get())
          .weigher(new Weigher<String, Record>() {
            @Override public int weigh(String key, Record value) {
              return key.getBytes().length + value.sizeEstimateBytes();
            }
          });
    }
    if (evictionPolicy.maxEntries().isPresent()) {
      lruCacheBuilder.maximumSize(evictionPolicy.maxEntries().get());
    }
    if (evictionPolicy.expireAfterAccess().isPresent()) {
      lruCacheBuilder.expireAfterAccess(evictionPolicy.expireAfterAccess().get(),
          evictionPolicy.expireAfterAccessTimeUnit().get());
    }
    if (evictionPolicy.expireAfterWrite().isPresent()) {
      lruCacheBuilder.expireAfterWrite(evictionPolicy.expireAfterWrite().get(),
          evictionPolicy.expireAfterWriteTimeUnit().get());
    }
    lruCache = lruCacheBuilder.build();
  }

  @Nullable @Override public Record loadRecord(final String key) {
    if (secondaryCacheStore.isPresent()) {
      try {
        return lruCache.get(key, new Callable<Record>() {
          @Override public Record call() throws Exception {
            Record record = secondaryCacheStore.get().loadRecord(key);
            // get(key, callable) requires non-null. If null, an exception should be
            //thrown, which will be converted to null in the catch clause.
            if (record == null) {
              throw new Exception(String.format("Record{key=%s} not present in secondary cache", key));
            }
            return record;
          }
        });
      } catch (Exception e) {
        return null;
      }
    }
    return lruCache.getIfPresent(key);
  }

  @Nonnull @Override public Set<String> merge(Record apolloRecord) {
    if (secondaryCacheStore.isPresent()) {
      secondaryCacheStore.get().merge(apolloRecord);
    }
    final Record oldRecord = lruCache.getIfPresent(apolloRecord.key());
    if (oldRecord == null) {
      lruCache.put(apolloRecord.key(), apolloRecord);
      return Collections.emptySet();
    } else {
      Set<String> changedKeys = oldRecord.mergeWith(apolloRecord);

      //re-insert to trigger new weight calculation
      lruCache.put(apolloRecord.key(), oldRecord);
      return changedKeys;
    }
  }

  /**
   * Clears all records from the cache. If present, secondary cache store will be cleared
   * as well.
   */
  @Override public void clearAll() {
    clearPrimaryCache();
    clearSecondaryCache();
  }

  /**
   * Clears all records from the in-memory LRU cache. The secondary cache will *not* be cleared.
   */
  public void clearPrimaryCache() {
    lruCache.invalidateAll();
  }

  /**
   * Clear all records from the secondary cache. Records in the in-memory LRU cache will remain.
   */
  public void clearSecondaryCache() {
    if (secondaryCacheStore.isPresent()) {
      secondaryCacheStore.get().clearAll();
    }
  }

}
