package test

import codegen.models.HeroAndFriendsNamesWithIDsQuery
import codegen.models.HeroAndFriendsWithFragmentsQuery
import codegen.models.HeroAndFriendsWithFragmentsQuery.Data.Hero.Companion.heroWithFriendsFragment
import codegen.models.fragment.HeroWithFriendsFragment.Friend.Companion.humanWithIdFragment
import codegen.models.fragment.HeroWithFriendsFragmentImpl
import codegen.models.fragment.HeroWithFriendsFragmentImpl.Data.Friend.Companion.humanWithIdFragment
import codegen.models.fragment.HumanWithIdFragmentImpl
import codegen.models.type.Episode
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.ApolloStore
import com.apollographql.apollo3.cache.normalized.CacheKey
import com.apollographql.apollo3.cache.normalized.MemoryCacheFactory
import com.apollographql.apollo3.interceptor.cache.withStore
import com.apollographql.apollo3.mockserver.MockServer
import com.apollographql.apollo3.testing.IdFieldCacheKeyResolver
import com.apollographql.apollo3.testing.enqueue
import com.apollographql.apollo3.testing.runWithMainLoop
import readJson
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class StoreTest {
  private lateinit var mockServer: MockServer
  private lateinit var apolloClient: ApolloClient
  private lateinit var store: ApolloStore

  @BeforeTest
  fun setUp() {
    store = ApolloStore(MemoryCacheFactory(maxSizeBytes = Int.MAX_VALUE), IdFieldCacheKeyResolver)
    mockServer = MockServer()
    apolloClient = ApolloClient(mockServer.url()).withStore(store)
  }

  @Test
  // This test currently fails because we don't store the typename in HeroAndFriendsNamesWithIDsQuery
  // So we can't query it from HeroWithFriendsFragment
  @Ignore
  fun readFragmentFromStore() = runWithMainLoop {
    mockServer.enqueue(readJson("HeroAndFriendsNamesWithIDsQuery.json"))
    apolloClient.query(HeroAndFriendsNamesWithIDsQuery(Episode.NEWHOPE))

    val heroWithFriendsFragment = store.readFragment(
        HeroWithFriendsFragmentImpl(),
        CacheKey.from("2001"),
    )!!
    assertEquals(heroWithFriendsFragment.id, "2001")
    assertEquals(heroWithFriendsFragment.name, "R2-D2")
    assertEquals(heroWithFriendsFragment.friends?.size, 3)
    assertEquals(heroWithFriendsFragment.friends?.get(0)?.humanWithIdFragment()?.id, "1000")
    assertEquals(heroWithFriendsFragment.friends?.get(0)?.humanWithIdFragment()?.name, "Luke Skywalker")
    assertEquals(heroWithFriendsFragment.friends?.get(1)?.humanWithIdFragment()?.id, "1002")
    assertEquals(heroWithFriendsFragment.friends?.get(1)?.humanWithIdFragment()?.name, "Han Solo")
    assertEquals(heroWithFriendsFragment.friends?.get(2)?.humanWithIdFragment()?.id, "1003")
    assertEquals(heroWithFriendsFragment.friends?.get(2)?.humanWithIdFragment()?.name, "Leia Organa")

    var fragment = store.readFragment(
        HumanWithIdFragmentImpl(),
        CacheKey.from("1000"),
    )!!

    assertEquals(fragment.id, "1000")
    assertEquals(fragment.name, "Luke Skywalker")

    fragment = store.readFragment(
        HumanWithIdFragmentImpl(),
        CacheKey.from("1002"),
    )!!
    assertEquals(fragment.id, "1002")
    assertEquals(fragment.name, "Han Solo")

    fragment = store.readFragment(
        HumanWithIdFragmentImpl(),
        CacheKey.from("1003"),
    )!!
    assertEquals(fragment.id, "1003")
    assertEquals(fragment.name, "Leia Organa")
  }

  /**
   * Modify the store by writing fragments
   */
  @Test
  fun fragments() = runWithMainLoop {
    mockServer.enqueue(readJson("HeroAndFriendsNamesWithIDs.json"))
    val query = HeroAndFriendsWithFragmentsQuery()
    var response = apolloClient.query(query)
    assertEquals(response.data?.hero?.__typename, "Droid")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.__typename, "Droid")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.id, "2001")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.name, "R2-D2")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.size, 3)
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(0)?.humanWithIdFragment()?.__typename, "Human")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(0)?.humanWithIdFragment()?.id, "1000")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(0)?.humanWithIdFragment()?.name, "Luke Skywalker")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(1)?.humanWithIdFragment()?.__typename, "Human")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(1)?.humanWithIdFragment()?.id, "1002")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(1)?.humanWithIdFragment()?.name, "Han Solo")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(2)?.humanWithIdFragment()?.__typename, "Human")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(2)?.humanWithIdFragment()?.id, "1003")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(2)?.humanWithIdFragment()?.name, "Leia Organa")

    store.writeFragment(
        HeroWithFriendsFragmentImpl(),
        CacheKey.from("2001"),
        HeroWithFriendsFragmentImpl.Data(
            __typename = "Droid",
            id = "2001",
            name = "R222-D222",
            friends = listOf(
                HeroWithFriendsFragmentImpl.Data.HumanFriend(
                    __typename = "Human",
                    id = "1000",
                    name = "SuperMan"
                ),
                HeroWithFriendsFragmentImpl.Data.HumanFriend(
                    __typename = "Human",
                    id = "1002",
                    name = "Han Solo"
                ),
            )
        ),
    )

    store.writeFragment(
        HumanWithIdFragmentImpl(),
        CacheKey.from("1002"),
        HumanWithIdFragmentImpl.Data(
            __typename = "Human",
            id = "1002",
            name = "Beast"
        ),
    )

    // Values should have changed
    response = apolloClient.query(query)
    assertEquals(response.data?.hero?.__typename, "Droid")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.__typename, "Droid")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.id, "2001")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.name, "R222-D222")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.size, 2)
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(0)?.humanWithIdFragment()?.__typename, "Human")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(0)?.humanWithIdFragment()?.id, "1000")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(0)?.humanWithIdFragment()?.name, "SuperMan")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(1)?.humanWithIdFragment()?.__typename, "Human")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(1)?.humanWithIdFragment()?.id, "1002")
    assertEquals(response.data?.hero?.heroWithFriendsFragment()?.friends?.get(1)?.humanWithIdFragment()?.name, "Beast")
  }
}