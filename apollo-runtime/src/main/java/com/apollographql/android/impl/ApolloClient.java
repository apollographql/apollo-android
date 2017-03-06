package com.apollographql.android.impl;

import com.apollographql.android.ApolloCall;
import com.apollographql.android.ApolloPrefetch;
import com.apollographql.android.CustomTypeAdapter;
import com.apollographql.android.api.graphql.Operation;
import com.apollographql.android.api.graphql.ResponseFieldMapper;
import com.apollographql.android.api.graphql.ScalarType;
import com.apollographql.android.cache.http.EvictionStrategy;
import com.apollographql.android.cache.http.HttpCache;
import com.apollographql.android.cache.http.ResponseCacheStore;
import com.apollographql.android.cache.normalized.Cache;
import com.apollographql.android.cache.normalized.CacheKeyResolver;
import com.apollographql.android.cache.normalized.CacheStore;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static com.apollographql.android.impl.util.Utils.checkNotNull;

public final class ApolloClient implements ApolloCall.Factory {
  public static Builder builder() {
    return new Builder();
  }

  private final HttpUrl serverUrl;
  private final Call.Factory httpCallFactory;
  private final HttpCache httpCache;
  private final Cache cache;
  private final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
  private final Moshi moshi;
  private final Map<Class, ResponseFieldMapper> responseFieldMapperPool = new LinkedHashMap<>();

  private ApolloClient(Builder builder) {
    this.serverUrl = builder.serverUrl;
    this.httpCallFactory = builder.okHttpClient;
    this.httpCache = builder.httpCache;
    this.cache = builder.cache;
    this.customTypeAdapters = builder.customTypeAdapters;
    this.moshi = builder.moshiBuilder.build();
  }

  @Override
  public <D extends Operation.Data, V extends Operation.Variables> ApolloCall<D> newCall(
      @Nonnull Operation<D, V> operation) {
    ResponseFieldMapper responseFieldMapper;
    synchronized (responseFieldMapperPool) {
      responseFieldMapper = responseFieldMapperPool.get(operation.getClass());
      if (responseFieldMapper == null) {
        responseFieldMapper = operation.responseFieldMapper();
        responseFieldMapperPool.put(operation.getClass(), responseFieldMapper);
      }
    }
    return new RealApolloCall<>(operation, serverUrl, httpCallFactory, httpCache, moshi,
        new ResponseBodyConverter(operation, responseFieldMapper, customTypeAdapters), cache);
  }

  @Override
  public <D extends Operation.Data, V extends Operation.Variables> ApolloPrefetch prefetch(
      @Nonnull Operation<D, V> operation) {
    return new RealApolloPrefetch(operation, serverUrl, httpCallFactory, moshi);
  }

  void clearCache() {
    if (httpCache != null) {
      httpCache.clear();
    }
  }

  Response cachedHttpResponse(String cacheKey) throws IOException {
    if (httpCache != null) {
      return httpCache.read(cacheKey);
    } else {
      return null;
    }
  }

  public static class Builder {
    OkHttpClient okHttpClient;
    HttpUrl serverUrl;
    HttpCache httpCache;
    Cache cache = Cache.NO_OP_NORMALIZED_CACHE;
    final Map<ScalarType, CustomTypeAdapter> customTypeAdapters = new LinkedHashMap<>();
    Moshi.Builder moshiBuilder = new Moshi.Builder();

    private Builder() {
    }

    public Builder okHttpClient(@Nonnull OkHttpClient okHttpClient) {
      this.okHttpClient = checkNotNull(okHttpClient, "okHttpClient is null");
      return this;
    }

    public Builder serverUrl(@Nonnull HttpUrl serverUrl) {
      this.serverUrl = checkNotNull(serverUrl, "serverUrl is null");
      return this;
    }

    public Builder serverUrl(@Nonnull String baseUrl) {
      checkNotNull(baseUrl, "baseUrl == null");
      this.serverUrl = HttpUrl.parse(baseUrl);
      return this;
    }

    public Builder httpCache(@Nonnull ResponseCacheStore cacheStore, @Nonnull EvictionStrategy evictionStrategy) {
      checkNotNull(cacheStore, "baseUrl == null");
      checkNotNull(evictionStrategy, "baseUrl == null");
      this.httpCache = new HttpCache(cacheStore, evictionStrategy);
      return this;
    }

    public Builder normalizedCache(@Nonnull CacheStore cacheStore,
        @Nonnull CacheKeyResolver cacheKeyResolver) {
      checkNotNull(cacheStore, "cacheStore == null");
      checkNotNull(cacheKeyResolver, "cacheKeyResolver == null");
      this.cache = new Cache(cacheStore, cacheKeyResolver);
      return this;
    }

    public <T> Builder withCustomTypeAdapter(@Nonnull ScalarType scalarType,
        @Nonnull final CustomTypeAdapter<T> customTypeAdapter) {
      customTypeAdapters.put(scalarType, customTypeAdapter);
      moshiBuilder.add(scalarType.javaType(), new JsonAdapter<T>() {
        @Override
        public T fromJson(com.squareup.moshi.JsonReader reader) throws IOException {
          return customTypeAdapter.decode(reader.nextString());
        }

        @Override
        public void toJson(JsonWriter writer, T value) throws IOException {
          //noinspection unchecked
          writer.value(customTypeAdapter.encode(value));
        }
      });
      return this;
    }

    public ApolloClient build() {
      checkNotNull(okHttpClient, "okHttpClient is null");
      checkNotNull(serverUrl, "serverUrl is null");

      if (httpCache != null) {
        okHttpClient = okHttpClient.newBuilder().addInterceptor(httpCache.interceptor()).build();
      }

      return new ApolloClient(this);
    }
  }
}
