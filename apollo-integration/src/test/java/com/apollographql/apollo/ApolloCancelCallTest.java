package com.apollographql.apollo;

import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.cache.http.ApolloHttpCache;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.integration.normalizer.EpisodeHeroNameQuery;
import com.apollographql.apollo.integration.normalizer.type.Episode;
import com.apollographql.apollo.rx2.Rx2Apollo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class ApolloCancelCallTest {
  private ApolloClient apolloClient;
  @Rule public final MockWebServer server = new MockWebServer();
  private MockHttpCacheStore cacheStore;

  @Before
  public void setup() {
    cacheStore = new MockHttpCacheStore();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .dispatcher(new Dispatcher(Utils.immediateExecutorService()))
        .build();
    apolloClient = ApolloClient.builder()
        .serverUrl(server.url("/"))
        .okHttpClient(okHttpClient)
        .httpCache(new ApolloHttpCache(cacheStore, null))
        .build();
  }

  @Test
  public void cancelCallBeforeEnqueueCanceledException() throws Exception {
    server.enqueue(mockResponse("EpisodeHeroNameResponse.json"));

    ApolloCall<EpisodeHeroNameQuery.Data> call = apolloClient.query(new EpisodeHeroNameQuery(Input.fromNullable(Episode.EMPIRE)));
    call.cancel();

    Rx2Apollo.from(call)
        .test()
        .assertError(ApolloCanceledException.class);
  }

  @Test
  public void cancelCallAfterEnqueueNoCallback() throws Exception {
    server.enqueue(mockResponse("EpisodeHeroNameResponse.json"));
    final ApolloCall<EpisodeHeroNameQuery.Data> call = apolloClient.query(new EpisodeHeroNameQuery(Input.fromNullable(Episode.EMPIRE)));

    Rx2Apollo.from(call)
        .test()
        .assertNoErrors()
        .assertNoValues()
        .assertNotComplete();
  }

  @Test
  public void cancelPrefetchBeforeEnqueueCanceledException() throws Exception {
    server.enqueue(mockResponse("EpisodeHeroNameResponse.json"));

    final AtomicReference<ApolloException> errorRef = new AtomicReference<>();
    ApolloCall<EpisodeHeroNameQuery.Data> call = apolloClient.query(new EpisodeHeroNameQuery(Input.fromNullable(Episode.EMPIRE)));

    call.cancel();

    Rx2Apollo.from(call)
        .test()
        .assertError(ApolloCanceledException.class);
  }

  @Test
  public void cancelPrefetchAfterEnqueueNoCallback() throws Exception {
    server.enqueue(mockResponse("EpisodeHeroNameResponse.json"));
    final ApolloPrefetch call = apolloClient.prefetch(new EpisodeHeroNameQuery(Input.fromNullable(Episode.EMPIRE)));

    Rx2Apollo.from(call)
        .test()
        .assertNotComplete();
  }

  private MockResponse mockResponse(String fileName) throws IOException {
    return new MockResponse().setChunkedBody(Utils.readFileToString(getClass(), "/" + fileName), 32);
  }
}
