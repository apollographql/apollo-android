package com.apollographql.apollo3.cache.normalized

import com.apollographql.apollo3.cache.CacheHeaders

interface ReadOnlyNormalizedCache {
  /**
   * @param key          The key of the record to read.
   * @param cacheHeaders The cache headers associated with the request which generated this record.
   * @return The [Record] for key. If not present return null.
   */
  fun loadRecord(key: String, cacheHeaders: CacheHeaders): Record?

  /**
   * Calls through to [NormalizedCache.loadRecord]. Implementations should override this
   * method if the underlying storage technology can offer an optimized manner to read multiple records.
   *
   * @param keys         The set of [Record] keys to read.
   * @param cacheHeaders The cache headers associated with the request which generated this record.
   */
  fun loadRecords(keys: Collection<String>, cacheHeaders: CacheHeaders): Collection<Record>

}