package com.apollographql.apollo;

import android.support.annotation.NonNull;

import com.apollographql.android.impl.normalizer.HeroAndFriendsNames;
import com.apollographql.android.impl.normalizer.type.Episode;
import com.apollographql.apollo.cache.ApolloCacheHeaders;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.NormalizedCache;
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.RecordFieldAdapter;
import com.apollographql.apollo.exception.ApolloException;
import com.squareup.moshi.Moshi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static com.google.common.truth.Truth.assertThat;

public class CacheHeadersTest {

  private MockWebServer server;

  @Before
  public void setUp() {
    server = new MockWebServer();
  }

  @After
  public void tearDown() throws IOException {
    server.shutdown();
  }

  @Test
  public void testHeadersReceived() throws ApolloException, IOException {
    final NormalizedCache normalizedCache = new NormalizedCache(RecordFieldAdapter.create(new Moshi.Builder().build())) {
      @Nullable @Override public Record loadRecord(@NonNull String key, @NonNull CacheHeaders cacheHeaders) {
        assertThat(cacheHeaders.hasHeader(ApolloCacheHeaders.DO_NOT_STORE)).isTrue();
        return null;
      }

      @Nonnull @Override public Set<String> merge(@NonNull Record record, @NonNull CacheHeaders cacheHeaders) {
        assertThat(cacheHeaders.hasHeader(ApolloCacheHeaders.DO_NOT_STORE)).isTrue();
        return Collections.emptySet();
      }

      @Override public void clearAll() {
      }

    };

    final NormalizedCacheFactory<NormalizedCache> cacheFactory = new NormalizedCacheFactory<NormalizedCache>() {
      @Override public NormalizedCache createNormalizedCache(RecordFieldAdapter recordFieldAdapter) {
        return normalizedCache;
      }
    };

    ApolloClient apolloClient = ApolloClient.builder()
        .normalizedCache(cacheFactory, new IdFieldCacheKeyResolver())
        .serverUrl(server.url("/"))
        .okHttpClient(new OkHttpClient())
        .build();


    server.enqueue(mockResponse("HeroAndFriendsNameResponse.json"));
    CacheHeaders cacheHeaders = CacheHeaders.builder().addHeader(ApolloCacheHeaders.DO_NOT_STORE, "true").build();
    apolloClient.newCall(new HeroAndFriendsNames(Episode.NEWHOPE))
        .cacheHeaders(cacheHeaders)
        .execute();
  }

  @Test
  public void testDefaultHeadersReceived() throws IOException, ApolloException {
    final NormalizedCache normalizedCache = new NormalizedCache(RecordFieldAdapter.create(new Moshi.Builder().build())) {
      @Nullable @Override public Record loadRecord(@NonNull String key, @NonNull CacheHeaders cacheHeaders) {
        assertThat(cacheHeaders.hasHeader(ApolloCacheHeaders.DO_NOT_STORE)).isTrue();
        return null;
      }

      @Nonnull @Override public Set<String> merge(@NonNull Record record, @NonNull CacheHeaders cacheHeaders) {
        assertThat(cacheHeaders.hasHeader(ApolloCacheHeaders.DO_NOT_STORE)).isTrue();
        return Collections.emptySet();
      }

      @Override public void clearAll() {

      }
    };

    final NormalizedCacheFactory<NormalizedCache> cacheFactory = new NormalizedCacheFactory<NormalizedCache>() {
      @Override public NormalizedCache createNormalizedCache(RecordFieldAdapter recordFieldAdapter) {
        return normalizedCache;
      }
    };

    CacheHeaders cacheHeaders = CacheHeaders.builder().addHeader(ApolloCacheHeaders.DO_NOT_STORE, "true").build();

    ApolloClient apolloClient = ApolloClient.builder()
        .normalizedCache(cacheFactory, new IdFieldCacheKeyResolver())
        .serverUrl(server.url("/"))
        .okHttpClient(new OkHttpClient())
        .defaultCacheHeaders(cacheHeaders)
        .build();

    server.enqueue(mockResponse("HeroAndFriendsNameResponse.json"));

    apolloClient.newCall(new HeroAndFriendsNames(Episode.NEWHOPE)).execute();
  }

  private MockResponse mockResponse(String fileName) throws IOException {
    return new MockResponse().setChunkedBody(Utils.readFileToString(getClass(), "/" + fileName), 32);
  }

}
