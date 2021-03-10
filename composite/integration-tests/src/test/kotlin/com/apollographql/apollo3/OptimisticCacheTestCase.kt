package com.apollographql.apollo3

import com.apollographql.apollo3.Utils.assertResponse
import com.apollographql.apollo3.Utils.enqueueAndAssertResponse
import com.apollographql.apollo3.Utils.immediateExecutor
import com.apollographql.apollo3.Utils.immediateExecutorService
import com.apollographql.apollo3.Utils.readFileToString
import com.apollographql.apollo3.api.Input
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.cache.normalized.MemoryCacheFactory
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.fetcher.ApolloResponseFetchers
import com.apollographql.apollo3.integration.normalizer.HeroAndFriendsNamesQuery
import com.apollographql.apollo3.integration.normalizer.HeroAndFriendsNamesWithIDsQuery
import com.apollographql.apollo3.integration.normalizer.HeroNameQuery
import com.apollographql.apollo3.integration.normalizer.HeroNameWithEnumsQuery
import com.apollographql.apollo3.integration.normalizer.HeroNameWithIdQuery
import com.apollographql.apollo3.integration.normalizer.ReviewsByEpisodeQuery
import com.apollographql.apollo3.integration.normalizer.UpdateReviewMutation
import com.apollographql.apollo3.integration.normalizer.UpdateReviewMutation.Data.UpdateReview
import com.apollographql.apollo3.integration.normalizer.type.ColorInput
import com.apollographql.apollo3.integration.normalizer.type.Episode
import com.apollographql.apollo3.integration.normalizer.type.ReviewInput
import com.google.common.truth.Truth.assertThat
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.util.ArrayList
import java.util.UUID

class OptimisticCacheTestCase {
  private var apolloClient: ApolloClient? = null

  val server = MockWebServer()
  @Before
  fun setUp() {
    val okHttpClient = OkHttpClient.Builder()
        .dispatcher(Dispatcher(immediateExecutorService()))
        .build()
    apolloClient = ApolloClient.builder()
        .serverUrl(server.url("/"))
        .okHttpClient(okHttpClient)
        .normalizedCache(MemoryCacheFactory(maxSizeBytes = Int.MAX_VALUE), IdFieldCacheKeyResolver())
        .dispatcher(immediateExecutor())
        .build()
  }

  @Throws(IOException::class)
  private fun mockResponse(fileName: String): MockResponse {
    return MockResponse().setChunkedBody(readFileToString(javaClass, "/$fileName"), 32)
  }

  @Test
  @Throws(Exception::class)
  fun simple() {
    val query = HeroAndFriendsNamesQuery(Input.Present(Episode.JEDI))
    enqueueAndAssertResponse(
        server,
        "HeroAndFriendsNameResponse.json",
        apolloClient!!.query(query)
    ) { response -> !response.hasErrors() }
    val mutationId = UUID.randomUUID()
    val data = HeroAndFriendsNamesQuery.Data(HeroAndFriendsNamesQuery.Data.Hero(
        "R222-D222",
        listOf(
            HeroAndFriendsNamesQuery.Data.Hero.Friends(
                "SuperMan"
            ),
            HeroAndFriendsNamesQuery.Data.Hero.Friends(
                "Batman"
            )
        )
    ))
    apolloClient!!.apolloStore.writeOptimisticUpdates(
        operation = query,
        operationData = data,
        mutationId = mutationId,
        publish = true)
    assertResponse(
        apolloClient!!.query(query).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.name).isEqualTo("R222-D222")
      assertThat(it.data?.hero?.friends).hasSize(2)
      assertThat(it.data?.hero?.friends?.get(0)?.name).isEqualTo("SuperMan")
      assertThat(it.data?.hero?.friends?.get(1)?.name).isEqualTo("Batman")
    }
    apolloClient!!.apolloStore.rollbackOptimisticUpdates(mutationId, false)
    assertResponse(
        apolloClient!!.query(query).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.name).isEqualTo("R2-D2")
      assertThat(it.data?.hero?.friends).hasSize(3)
      assertThat(it.data?.hero?.friends?.get(0)?.name).isEqualTo("Luke Skywalker")
      assertThat(it.data?.hero?.friends?.get(1)?.name).isEqualTo("Han Solo")
      assertThat(it.data?.hero?.friends?.get(2)?.name).isEqualTo("Leia Organa")
    }
  }

  @Test
  @Throws(Exception::class)
  fun two_optimistic_two_rollback() {
    val query1 = HeroAndFriendsNamesWithIDsQuery(Input.Present(Episode.JEDI))
    val mutationId1 = UUID.randomUUID()

    // execute query1 from the network
    enqueueAndAssertResponse(
        server,
        "HeroAndFriendsNameWithIdsResponse.json",
        apolloClient!!.query(query1)
    ) { response -> !response.hasErrors() }

    // now write some optimistic updates for query1
    val data1 = HeroAndFriendsNamesWithIDsQuery.Data(
        HeroAndFriendsNamesWithIDsQuery.Data.Hero(
            "2001",
            "R222-D222",
            listOf(
                HeroAndFriendsNamesWithIDsQuery.Data.Hero.Friends(
                    "1000",
                    "SuperMan"
                ),
                HeroAndFriendsNamesWithIDsQuery.Data.Hero.Friends(
                    "1003",
                    "Batman"
                )
            )
        )
    )
    apolloClient!!.apolloStore.writeOptimisticUpdates(
        operation = query1,
        operationData= data1,
        mutationId = mutationId1,
        publish = true)

    // check if query1 see optimistic updates
    assertResponse(
        apolloClient!!.query(query1).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("2001")
      assertThat(it.data?.hero?.name).isEqualTo("R222-D222")
      assertThat(it.data?.hero?.friends).hasSize(2)
      assertThat(it.data?.hero?.friends?.get(0)?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.friends?.get(0)?.name).isEqualTo("SuperMan")
      assertThat(it.data?.hero?.friends?.get(1)?.id).isEqualTo("1003")
      assertThat(it.data?.hero?.friends?.get(1)?.name).isEqualTo("Batman")
    }

    // execute query2
    val query2 = HeroNameWithIdQuery()
    val mutationId2 = UUID.randomUUID()
    enqueueAndAssertResponse(
        server,
        "HeroNameWithIdResponse.json",
        apolloClient!!.query(query2)
    ) {
      response -> !response.hasErrors()
    }

    // write optimistic data2
    val data2 = HeroNameWithIdQuery.Data(HeroNameWithIdQuery.Data.Hero(
        "1000",
        "Beast"
    ))
    apolloClient!!.apolloStore.writeOptimisticUpdates(
        operation = query2,
        operationData = data2,
        mutationId = mutationId2,
        publish = true)

    // check if query1 sees data2
    assertResponse(
        apolloClient!!.query(query1).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("2001")
      assertThat(it.data?.hero?.name).isEqualTo("R222-D222")
      assertThat(it.data?.hero?.friends).hasSize(2)
      assertThat(it.data?.hero?.friends?.get(0)?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.friends?.get(0)?.name).isEqualTo("Beast")
      assertThat(it.data?.hero?.friends?.get(1)?.id).isEqualTo("1003")
      assertThat(it.data?.hero?.friends?.get(1)?.name).isEqualTo("Batman")
    }

    // check if query2 sees data2
    assertResponse(
        apolloClient!!.query(query2).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.name).isEqualTo("Beast")
    }

    // rollback data1
    apolloClient!!.apolloStore.rollbackOptimisticUpdates(mutationId1, false)

    // check if query2 sees the rollback
    assertResponse(
        apolloClient!!.query(query1).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("2001")
      assertThat(it.data?.hero?.name).isEqualTo("R2-D2")
      assertThat(it.data?.hero?.friends).hasSize(3)
      assertThat(it.data?.hero?.friends?.get(0)?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.friends?.get(0)?.name).isEqualTo("Beast")
      assertThat(it.data?.hero?.friends?.get(1)?.id).isEqualTo("1002")
      assertThat(it.data?.hero?.friends?.get(1)?.name).isEqualTo("Han Solo")
      assertThat(it.data?.hero?.friends?.get(2)?.id).isEqualTo("1003")
      assertThat(it.data?.hero?.friends?.get(2)?.name).isEqualTo("Leia Organa")
    }

    // check if query2 see the latest optimistic updates
    assertResponse(
        apolloClient!!.query(query2).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.name).isEqualTo("Beast")
    }

    // rollback query2 optimistic updates
    apolloClient!!.apolloStore.rollbackOptimisticUpdates(mutationId2, false)

    // check if query2 see the latest optimistic updates
    assertResponse(
        apolloClient!!.query(query2).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.name).isEqualTo("SuperMan")
    }
  }

  @Test
  @Throws(Exception::class)
  fun full_persisted_partial_optimistic() {
    enqueueAndAssertResponse(
        server,
        "HeroNameWithEnumsResponse.json",
        apolloClient!!.query(HeroNameWithEnumsQuery())
    ) { response -> !response.hasErrors() }
    val mutationId = UUID.randomUUID()
    apolloClient!!.apolloStore.writeOptimisticUpdates(
        operation = HeroNameQuery(),
        operationData = HeroNameQuery.Data(HeroNameQuery.Data.Hero("R22-D22")),
        mutationId = mutationId,
        publish = false
    )
    assertResponse(
        apolloClient!!.query(HeroNameWithEnumsQuery()).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.name).isEqualTo("R22-D22")
      assertThat(it.data?.hero?.firstAppearsIn).isEqualTo(Episode.EMPIRE)
      assertThat(it.data?.hero?.appearsIn).isEqualTo(listOf(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI))
    }
    apolloClient!!.apolloStore.rollbackOptimisticUpdates(mutationId, false)
    assertResponse(
        apolloClient!!.query(HeroNameWithEnumsQuery()).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.name).isEqualTo("R2-D2")
      assertThat(it.data?.hero?.firstAppearsIn).isEqualTo(Episode.EMPIRE)
      assertThat(it.data?.hero?.appearsIn).isEqualTo(listOf(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI))
    }
  }

  @Test
  @Throws(Exception::class)
  fun mutation_and_query_watcher() {
    server.enqueue(mockResponse("ReviewsEmpireEpisodeResponse.json"))
    val watcherData: MutableList<ReviewsByEpisodeQuery.Data> = ArrayList()
    apolloClient!!.query(ReviewsByEpisodeQuery(Episode.EMPIRE)).responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
        .watcher().refetchResponseFetcher(ApolloResponseFetchers.CACHE_FIRST)
        .enqueueAndWatch(object : ApolloCall.Callback<ReviewsByEpisodeQuery.Data>() {
          override fun onResponse(response: ApolloResponse<ReviewsByEpisodeQuery.Data>) {
            watcherData.add(response.data!!)
          }

          override fun onFailure(e: ApolloException) {}
        })
    server.enqueue(mockResponse("UpdateReviewResponse.json"))
    val updateReviewMutation = UpdateReviewMutation(
        "empireReview2",
        ReviewInput(
            commentary = Input.Present("Great"),
            stars = 5,
            favoriteColor = ColorInput()
        )
    )
    apolloClient!!.mutate(
        updateReviewMutation,
        UpdateReviewMutation.Data(
            UpdateReview(
                "empireReview2",
                5,
                "Great"
            )
        )
    ).enqueue(
        object : ApolloCall.Callback<UpdateReviewMutation.Data>() {
          override fun onResponse(response: ApolloResponse<UpdateReviewMutation.Data>) {}
          override fun onFailure(e: ApolloException) {}
        }
    )
    assertThat(watcherData).hasSize(3)

    // before mutation and optimistic updates
    assertThat(watcherData[0].reviews).hasSize(3)
    assertThat(watcherData[0].reviews?.get(0)?.id).isEqualTo("empireReview1")
    assertThat(watcherData[0].reviews?.get(0)?.stars).isEqualTo(1)
    assertThat(watcherData[0].reviews?.get(0)?.commentary).isEqualTo("Boring")
    assertThat(watcherData[0].reviews?.get(1)?.id).isEqualTo("empireReview2")
    assertThat(watcherData[0].reviews?.get(1)?.stars).isEqualTo(2)
    assertThat(watcherData[0].reviews?.get(1)?.commentary).isEqualTo("So-so")
    assertThat(watcherData[0].reviews?.get(2)?.id).isEqualTo("empireReview3")
    assertThat(watcherData[0].reviews?.get(2)?.stars).isEqualTo(5)
    assertThat(watcherData[0].reviews?.get(2)?.commentary).isEqualTo("Amazing")

    // optimistic updates
    assertThat(watcherData[1].reviews).hasSize(3)
    assertThat(watcherData[1].reviews?.get(0)?.id).isEqualTo("empireReview1")
    assertThat(watcherData[1].reviews?.get(0)?.stars).isEqualTo(1)
    assertThat(watcherData[1].reviews?.get(0)?.commentary).isEqualTo("Boring")
    assertThat(watcherData[1].reviews?.get(1)?.id).isEqualTo("empireReview2")
    assertThat(watcherData[1].reviews?.get(1)?.stars).isEqualTo(5)
    assertThat(watcherData[1].reviews?.get(1)?.commentary).isEqualTo("Great")
    assertThat(watcherData[1].reviews?.get(2)?.id).isEqualTo("empireReview3")
    assertThat(watcherData[1].reviews?.get(2)?.stars).isEqualTo(5)
    assertThat(watcherData[1].reviews?.get(2)?.commentary).isEqualTo("Amazing")

    // after mutation with rolled back optimistic updates
    assertThat(watcherData[2].reviews).hasSize(3)
    assertThat(watcherData[2].reviews?.get(0)?.id).isEqualTo("empireReview1")
    assertThat(watcherData[2].reviews?.get(0)?.stars).isEqualTo(1)
    assertThat(watcherData[2].reviews?.get(0)?.commentary).isEqualTo("Boring")
    assertThat(watcherData[2].reviews?.get(1)?.id).isEqualTo("empireReview2")
    assertThat(watcherData[2].reviews?.get(1)?.stars).isEqualTo(4)
    assertThat(watcherData[2].reviews?.get(1)?.commentary).isEqualTo("Not Bad")
    assertThat(watcherData[2].reviews?.get(2)?.id).isEqualTo("empireReview3")
    assertThat(watcherData[2].reviews?.get(2)?.stars).isEqualTo(5)
    assertThat(watcherData[2].reviews?.get(2)?.commentary).isEqualTo("Amazing")
  }

  @Test
  @Throws(Exception::class)
  fun two_optimistic_reverse_rollback_order() {
    val query1 = HeroAndFriendsNamesWithIDsQuery(Input.Present(Episode.JEDI))
    val mutationId1 = UUID.randomUUID()
    val query2 = HeroNameWithIdQuery()
    val mutationId2 = UUID.randomUUID()
    enqueueAndAssertResponse(
        server,
        "HeroAndFriendsNameWithIdsResponse.json",
        apolloClient!!.query(query1)
    ) { response -> !response.hasErrors() }

    enqueueAndAssertResponse(
        server,
        "HeroNameWithIdResponse.json",
        apolloClient!!.query(query2)
    ) { response -> !response.hasErrors() }
    val data1 = HeroAndFriendsNamesWithIDsQuery.Data(
        HeroAndFriendsNamesWithIDsQuery.Data.Hero(
            "2001",
            "R222-D222",
            listOf(
                HeroAndFriendsNamesWithIDsQuery.Data.Hero.Friends(
                    "1000",
                    "Robocop"
                ),
                HeroAndFriendsNamesWithIDsQuery.Data.Hero.Friends(
                    "1003",
                    "Batman"
                )
            )
        )
    )
    apolloClient!!.apolloStore.writeOptimisticUpdates(
        operation = query1,
        operationData = data1,
        mutationId = mutationId1,
        publish = true)
    val data2 = HeroNameWithIdQuery.Data(HeroNameWithIdQuery.Data.Hero(
        "1000",
        "Spiderman"
    ))
    apolloClient!!.apolloStore.writeOptimisticUpdates(
        operation = query2,
        operationData = data2,
        mutationId = mutationId2,
        publish = true)

    // check if query1 see optimistic updates
    assertResponse(
        apolloClient!!.query(query1).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("2001")
      assertThat(it.data?.hero?.name).isEqualTo("R222-D222")
      assertThat(it.data?.hero?.friends).hasSize(2)
      assertThat(it.data?.hero?.friends?.get(0)?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.friends?.get(0)?.name).isEqualTo("Spiderman")
      assertThat(it.data?.hero?.friends?.get(1)?.id).isEqualTo("1003")
      assertThat(it.data?.hero?.friends?.get(1)?.name).isEqualTo("Batman")
    }

    // check if query2 see the latest optimistic updates
    assertResponse(
        apolloClient!!.query(query2).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.name).isEqualTo("Spiderman")
    }

    // rollback query2 optimistic updates
    apolloClient!!.apolloStore.rollbackOptimisticUpdates(mutationId2, false)

    // check if query1 see the latest optimistic updates
    assertResponse(
        apolloClient!!.query(query1).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("2001")
      assertThat(it.data?.hero?.name).isEqualTo("R222-D222")
      assertThat(it.data?.hero?.friends).hasSize(2)
      assertThat(it.data?.hero?.friends?.get(0)?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.friends?.get(0)?.name).isEqualTo("Robocop")
      assertThat(it.data?.hero?.friends?.get(1)?.id).isEqualTo("1003")
      assertThat(it.data?.hero?.friends?.get(1)?.name).isEqualTo("Batman")
    }

    // check if query2 see the latest optimistic updates
    assertResponse(
        apolloClient!!.query(query2).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.name).isEqualTo("Robocop")
    }

    // rollback query1 optimistic updates
    apolloClient!!.apolloStore.rollbackOptimisticUpdates(mutationId1, false)

    // check if query1 see the latest non-optimistic updates
    assertResponse(
        apolloClient!!.query(query1).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("2001")
      assertThat(it.data?.hero?.name).isEqualTo("R2-D2")
      assertThat(it.data?.hero?.friends).hasSize(3)
      assertThat(it.data?.hero?.friends?.get(0)?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.friends?.get(0)?.name).isEqualTo("SuperMan")
      assertThat(it.data?.hero?.friends?.get(1)?.id).isEqualTo("1002")
      assertThat(it.data?.hero?.friends?.get(1)?.name).isEqualTo("Han Solo")
      assertThat(it.data?.hero?.friends?.get(2)?.id).isEqualTo("1003")
      assertThat(it.data?.hero?.friends?.get(2)?.name).isEqualTo("Leia Organa")
    }

    // check if query2 see the latest non-optimistic updates
    assertResponse(
        apolloClient!!.query(query2).responseFetcher(ApolloResponseFetchers.CACHE_ONLY)
    ) { 
      assertThat(it.data?.hero?.id).isEqualTo("1000")
      assertThat(it.data?.hero?.name).isEqualTo("SuperMan")
    }
  }
}
