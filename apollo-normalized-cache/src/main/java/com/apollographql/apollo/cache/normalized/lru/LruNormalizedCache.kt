package com.apollographql.apollo.cache.normalized.lru

import com.apollographql.apollo.cache.ApolloCacheHeaders
import com.apollographql.apollo.cache.CacheHeaders
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.NormalizedCache
import com.apollographql.apollo.cache.normalized.Record
import com.nytimes.android.external.cache.Cache
import com.nytimes.android.external.cache.CacheBuilder
import com.nytimes.android.external.cache.Weigher
import java.nio.charset.Charset
import java.util.concurrent.Callable

/**
 * A [NormalizedCache] backed by an in memory [Cache]. Can be configured with an optional secondaryCache [ ], which will be used as a backup if a [Record] is not present in the primary cache.
 *
 *
 * A common configuration is to have secondary SQL cache.
 */
class LruNormalizedCache internal constructor(evictionPolicy: EvictionPolicy) : NormalizedCache() {

  private val lruCache: Cache<String, Record> =
      CacheBuilder.newBuilder().apply {
        if (evictionPolicy.maxSizeBytes().isPresent) {
          maximumWeight(evictionPolicy.maxSizeBytes().get()).weigher(
              Weigher { key: String, value: Record ->
                key.toByteArray(Charset.defaultCharset()).size + value.sizeEstimateBytes()
              }
          )
        }
        if (evictionPolicy.maxEntries().isPresent) {
          maximumSize(evictionPolicy.maxEntries().get())
        }
        if (evictionPolicy.expireAfterAccess().isPresent) {
          expireAfterAccess(evictionPolicy.expireAfterAccess().get(), evictionPolicy.expireAfterAccessTimeUnit().get())
        }
        if (evictionPolicy.expireAfterWrite().isPresent) {
          expireAfterWrite(evictionPolicy.expireAfterWrite().get(), evictionPolicy.expireAfterWriteTimeUnit().get())
        }
      }.build()

  override fun loadRecord(key: String, cacheHeaders: CacheHeaders): Record? {
    return try {
      lruCache.get(key, Callable {
        nextCache?.loadRecord(key, cacheHeaders)
      })
    } catch (ignored: Exception) { // Thrown when the nextCache's value is null
      return null
    }.also {
      if (cacheHeaders.hasHeader(ApolloCacheHeaders.EVICT_AFTER_READ)) {
        lruCache.invalidate(key)
      }
    }
  }

  override fun clearAll() {
    nextCache?.clearAll()
    clearCurrentCache()
  }

  override fun remove(cacheKey: CacheKey, cascade: Boolean): Boolean {
    var result: Boolean = nextCache?.remove(cacheKey, cascade) ?: false

    val record = lruCache.getIfPresent(cacheKey.key)
    if (record != null) {
      lruCache.invalidate(cacheKey.key)
      result = true
      if (cascade) {
        for (cacheReference in record.referencedFields()) {
          result = result && remove(CacheKey(cacheReference.key()), true)
        }
      }
    }
    return result
  }

  internal fun clearCurrentCache() {
    lruCache.invalidateAll()
  }

  override fun performMerge(apolloRecord: Record, cacheHeaders: CacheHeaders): Set<String> {
    val oldRecord = lruCache.getIfPresent(apolloRecord.key())
    return if (oldRecord == null) {
      lruCache.put(apolloRecord.key(), apolloRecord)
      apolloRecord.keys()
    } else {
      oldRecord.mergeWith(apolloRecord).also {
        //re-insert to trigger new weight calculation
        lruCache.put(apolloRecord.key(), oldRecord)
      }
    }
  }

  @OptIn(ExperimentalStdlibApi::class)
  override fun dump() = buildMap<Class<*>, Map<String, Record>> {
    put(this@LruNormalizedCache.javaClass, lruCache.asMap())
    putAll(nextCache?.dump().orEmpty())
  }
}
