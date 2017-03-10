package com.apollographql.android.impl;

import android.support.annotation.NonNull;

import com.apollographql.android.api.graphql.Response;
import com.apollographql.android.cache.normalized.CacheControl;
import com.apollographql.android.cache.normalized.CacheKey;
import com.apollographql.android.cache.normalized.CacheKeyResolver;
import com.apollographql.android.impl.normalizer.EpisodeHeroName;
import com.apollographql.android.impl.normalizer.HeroAndFriendsNames;
import com.apollographql.android.impl.normalizer.HeroAndFriendsNamesWithIDForParentOnly;
import com.apollographql.android.impl.normalizer.HeroAndFriendsNamesWithIDs;
import com.apollographql.android.impl.normalizer.HeroAppearsIn;
import com.apollographql.android.impl.normalizer.HeroParentTypeDependentField;
import com.apollographql.android.impl.normalizer.HeroTypeDependentAliasedField;
import com.apollographql.android.impl.normalizer.SameHeroTwice;
import com.apollographql.android.impl.normalizer.type.Episode;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Nonnull;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static com.google.common.truth.Truth.assertThat;

public class NormalizedCacheTestCase {

  private ApolloClient apolloClient;
  private MockWebServer server;
  private InMemoryCacheStore cacheStore;

  @Before public void setUp() {
    server = new MockWebServer();

    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    cacheStore = new InMemoryCacheStore();

    apolloClient = ApolloClient.builder()
        .serverUrl(server.url("/"))
        .okHttpClient(okHttpClient)
        .normalizedCache(cacheStore, new CacheKeyResolver() {
          @Nonnull @Override public CacheKey resolve(@NonNull Map<String, Object> jsonObject) {
            String id = (String) jsonObject.get("id");
            if (id == null || id.isEmpty()) {
              return CacheKey.NO_KEY;
            }
            return CacheKey.from(id);
          }
        })
        .build();
  }

  private MockResponse mockResponse(String fileName) throws IOException {
    return new MockResponse().setChunkedBody(Utils.readFileToString(getClass(), "/" + fileName), 32);
  }

  @Test public void episodeHeroName() throws IOException {
    server.enqueue(mockResponse("HeroNameResponse.json"));

    EpisodeHeroName query = new EpisodeHeroName(EpisodeHeroName.Variables.builder().episode(Episode.EMPIRE).build());

    Response<EpisodeHeroName.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
  }

  @Test public void heroAndFriendsNameResponse() throws IOException {
    server.enqueue(mockResponse("HeroAndFriendsNameResponse.json"));

    HeroAndFriendsNames query = new HeroAndFriendsNames(HeroAndFriendsNames.Variables.builder().episode(Episode.JEDI)
        .build());

    Response<HeroAndFriendsNames.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
    assertThat(body.data().hero().friends()).hasSize(3);
    assertThat(body.data().hero().friends().get(0).name()).isEqualTo("Luke Skywalker");
    assertThat(body.data().hero().friends().get(1).name()).isEqualTo("Han Solo");
    assertThat(body.data().hero().friends().get(2).name()).isEqualTo("Leia Organa");
  }

  @Test public void heroAndFriendsNamesWithIDs() throws IOException {
    server.enqueue(mockResponse("HeroAndFriendsNameWithIdsResponse.json"));

    HeroAndFriendsNamesWithIDs query = new HeroAndFriendsNamesWithIDs(HeroAndFriendsNamesWithIDs.Variables.builder()
        .episode(Episode.NEWHOPE).build());

    Response<HeroAndFriendsNamesWithIDs.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().id()).isEqualTo("2001");
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
    assertThat(body.data().hero().friends()).hasSize(3);
    assertThat(body.data().hero().friends().get(0).id()).isEqualTo("1000");
    assertThat(body.data().hero().friends().get(0).name()).isEqualTo("Luke Skywalker");
    assertThat(body.data().hero().friends().get(1).id()).isEqualTo("1002");
    assertThat(body.data().hero().friends().get(1).name()).isEqualTo("Han Solo");
    assertThat(body.data().hero().friends().get(2).id()).isEqualTo("1003");
    assertThat(body.data().hero().friends().get(2).name()).isEqualTo("Leia Organa");
  }

  @Test public void heroAndFriendsNameWithIdsForParentOnly() throws IOException {
    server.enqueue(mockResponse("HeroAndFriendsNameWithIdsParentOnlyResponse.json"));

    HeroAndFriendsNamesWithIDForParentOnly query = new HeroAndFriendsNamesWithIDForParentOnly
        (HeroAndFriendsNamesWithIDForParentOnly.Variables.builder().episode(Episode.NEWHOPE).build());

    Response<HeroAndFriendsNamesWithIDForParentOnly.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().id()).isEqualTo("2001");
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
    assertThat(body.data().hero().friends()).hasSize(3);
    assertThat(body.data().hero().friends().get(0).name()).isEqualTo("Luke Skywalker");
    assertThat(body.data().hero().friends().get(1).name()).isEqualTo("Han Solo");
    assertThat(body.data().hero().friends().get(2).name()).isEqualTo("Leia Organa");
  }

  @Test public void heroAppearsInResponse() throws IOException {
    server.enqueue(mockResponse("HeroAppearsInResponse.json"));

    HeroAppearsIn query = new HeroAppearsIn();

    Response<HeroAppearsIn.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().appearsIn()).hasSize(3);
    assertThat(body.data().hero().appearsIn().get(0).name()).isEqualTo("NEWHOPE");
    assertThat(body.data().hero().appearsIn().get(1).name()).isEqualTo("EMPIRE");
    assertThat(body.data().hero().appearsIn().get(2).name()).isEqualTo("JEDI");
  }

  @Test public void heroParentTypeDependentField() throws IOException {
    server.enqueue(mockResponse("HeroParentTypeDependentFieldDroidResponse.json"));

    HeroParentTypeDependentField query = new HeroParentTypeDependentField(HeroParentTypeDependentField.Variables
        .builder().episode(Episode.NEWHOPE).build());

    Response<HeroParentTypeDependentField.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
    assertThat(body.data().hero().asDroid().name()).isEqualTo("R2-D2");
    assertThat(body.data().hero().asDroid().friends()).hasSize(3);
    assertThat(body.data().hero().asDroid().friends().get(0).name()).isEqualTo("Luke Skywalker");
    assertThat(body.data().hero().asDroid().friends().get(0).asHuman().name()).isEqualTo("Luke Skywalker");
    assertThat(body.data().hero().asDroid().friends().get(0).asHuman().height()).isWithin(1.72);
  }

  @Test public void heroTypeDependentAliasedField() throws IOException {
    server.enqueue(mockResponse("HeroTypeDependentAliasedFieldResponse.json"));

    HeroTypeDependentAliasedField query = new HeroTypeDependentAliasedField(HeroTypeDependentAliasedField.Variables
        .builder().episode(Episode.NEWHOPE).build());

    Response<HeroTypeDependentAliasedField.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().asHuman()).isNull();
    assertThat(body.data().hero().asDroid().property()).isEqualTo("Astromech");

    server.enqueue(mockResponse("HeroTypeDependentAliasedFieldResponseHuman.json"));

    body = apolloClient.newCall(query).cacheControl(CacheControl.NETWORK_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().asDroid()).isNull();
    assertThat(body.data().hero().asHuman().property()).isEqualTo("Tatooine");
  }

  @Test public void sameHeroTwice() throws IOException {
    server.enqueue(mockResponse("SameHeroTwiceResponse.json"));

    SameHeroTwice query = new SameHeroTwice();

    Response<SameHeroTwice.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
    assertThat(body.data().r2().appearsIn()).hasSize(3);
    assertThat(body.data().r2().appearsIn().get(0).name()).isEqualTo("NEWHOPE");
    assertThat(body.data().r2().appearsIn().get(1).name()).isEqualTo("EMPIRE");
    assertThat(body.data().r2().appearsIn().get(2).name()).isEqualTo("JEDI");
  }

  @Test public void cacheFirst() throws IOException {
    server.enqueue(mockResponse("HeroNameResponse.json"));

    EpisodeHeroName query = new EpisodeHeroName(EpisodeHeroName.Variables.builder().episode(Episode.EMPIRE).build());

    Response<EpisodeHeroName.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_FIRST).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
  }

  @Test public void cacheOnly() throws IOException {
    server.enqueue(mockResponse("HeroNameResponse.json"));

    EpisodeHeroName query = new EpisodeHeroName(EpisodeHeroName.Variables.builder().episode(Episode.EMPIRE).build());

    Response<EpisodeHeroName.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    body = apolloClient.newCall(query).cacheControl(CacheControl.CACHE_ONLY).execute();
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
  }

  @Test public void networkFirst() throws IOException {
    server.enqueue(mockResponse("HeroNameResponse.json"));

    EpisodeHeroName query = new EpisodeHeroName(EpisodeHeroName.Variables.builder().episode(Episode.EMPIRE).build());

    Response<EpisodeHeroName.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    server.enqueue(mockResponse("HeroNameResponse.json"));
    body = apolloClient.newCall(query).cacheControl(CacheControl.NETWORK_FIRST).execute();
    assertThat(server.getRequestCount()).isEqualTo(2);
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");

    server.enqueue(new MockResponse().setResponseCode(504).setBody(""));
    body = apolloClient.newCall(query).cacheControl(CacheControl.NETWORK_FIRST).execute();
    assertThat(server.getRequestCount()).isEqualTo(3);
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
  }

  @Test public void networkOnly() throws IOException {
    server.enqueue(mockResponse("HeroNameResponse.json"));

    EpisodeHeroName query = new EpisodeHeroName(EpisodeHeroName.Variables.builder().episode(Episode.EMPIRE).build());

    Response<EpisodeHeroName.Data> body = apolloClient.newCall(query).execute();
    assertThat(body.isSuccessful()).isTrue();

    server.enqueue(mockResponse("HeroNameResponse.json"));
    body = apolloClient.newCall(query).cacheControl(CacheControl.NETWORK_ONLY).execute();
    assertThat(server.getRequestCount()).isEqualTo(2);
    assertThat(body.isSuccessful()).isTrue();
    assertThat(body.data().hero().name()).isEqualTo("R2-D2");
  }
}
