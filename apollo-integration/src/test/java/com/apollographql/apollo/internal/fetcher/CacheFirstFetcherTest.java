package com.apollographql.apollo.internal.fetcher;

import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.integration.normalizer.EpisodeHeroNameQuery;
import com.apollographql.apollo.integration.normalizer.type.Episode;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.apollographql.apollo.fetcher.ApolloResponseFetchers.CACHE_FIRST;
import static com.google.common.truth.Truth.assertThat;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

public class CacheFirstFetcherTest extends BaseFetcherTest {
  @Test public void enqueue() throws IOException, ApolloException, TimeoutException, InterruptedException {

    EpisodeHeroNameQuery query = EpisodeHeroNameQuery.builder().episode(Episode.EMPIRE).build();
    TrackingCallback trackingCallback;

    // Has error when cache empty, and network error
    server.enqueue(new MockResponse().setResponseCode(HTTP_INTERNAL_ERROR).setBody("Server Error"));
    trackingCallback = new TrackingCallback();
    apolloClient.query(query).responseFetcher(CACHE_FIRST).enqueue(trackingCallback);
    assertThat(trackingCallback.exceptions.size()).isEqualTo(1);

    // Goes to network when empty
    trackingCallback = new TrackingCallback();
    server.enqueue(mockResponse("HeroNameResponse.json"));
    apolloClient.query(query).responseFetcher(CACHE_FIRST).enqueue(trackingCallback);
    assertThat(trackingCallback.exceptions).isEmpty();
    assertThat(trackingCallback.responseList.size()).isEqualTo(1);
    assertThat(trackingCallback.responseList.get(0).isFromCache()).isFalse();
    assertThat(trackingCallback.responseList.get(0).getData().hero().name()).isEqualTo("R2-D2");
    assertThat(server.getRequestCount()).isEqualTo(2);

    // Hits only cache after populated
    trackingCallback = new TrackingCallback();
    apolloClient.query(query).responseFetcher(CACHE_FIRST).enqueue(trackingCallback);
    assertThat(trackingCallback.exceptions).isEmpty();
    assertThat(trackingCallback.responseList.size()).isEqualTo(1);
    assertThat(trackingCallback.responseList.get(0).isFromCache()).isTrue();
    assertThat(trackingCallback.responseList.get(0).getData().hero().name()).isEqualTo("R2-D2");
    assertThat(server.getRequestCount()).isEqualTo(2);
  }
}
