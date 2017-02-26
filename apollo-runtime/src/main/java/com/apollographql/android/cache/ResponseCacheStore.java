package com.apollographql.android.cache;

import java.io.IOException;

import javax.annotation.Nonnull;

import okhttp3.Response;

public interface ResponseCacheStore {
  ResponseCacheRecord cacheRecord(@Nonnull String cacheKey) throws IOException;

  ResponseCacheRecordEditor cacheRecordEditor(@Nonnull String cacheKey) throws IOException;

  void remove(@Nonnull String cacheKey) throws IOException;

  void delete() throws IOException;

  @Nonnull EvictionStrategy evictionStrategy();

  interface EvictionStrategy {
    boolean isStale(@Nonnull Response response);
  }
}
