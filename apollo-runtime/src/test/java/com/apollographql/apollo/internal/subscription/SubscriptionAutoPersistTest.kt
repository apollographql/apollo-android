package com.apollographql.apollo.internal.subscription

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.Subscription
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.cache.normalized.ApolloStore
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.subscription.ApolloOperationMessageSerializer
import com.apollographql.apollo.subscription.OperationClientMessage
import com.apollographql.apollo.subscription.OperationServerMessage
import com.apollographql.apollo.subscription.SubscriptionConnectionParams
import com.apollographql.apollo.subscription.SubscriptionConnectionParamsProvider
import com.apollographql.apollo.subscription.SubscriptionManagerState
import com.apollographql.apollo.subscription.SubscriptionTransport
import com.google.common.truth.Truth
import okio.Buffer
import org.junit.Before
import org.junit.Test
import java.util.ArrayList
import java.util.Collections
import java.util.concurrent.Executor

class SubscriptionAutoPersistTest {
  private var subscriptionTransportFactory: MockSubscriptionTransportFactory? = null
  private var subscriptionManager: RealSubscriptionManager? = null
  private val subscription = MockSubscription("MockSubscription")
  private var callbackAdapter: SubscriptionManagerCallbackAdapter<Operation.Data>? = null
  @Before
  fun setUp() {
    subscriptionTransportFactory = MockSubscriptionTransportFactory()
    subscriptionManager = RealSubscriptionManager(
        CustomScalarAdapters(emptyMap()),
        subscriptionTransportFactory!!,
        SubscriptionConnectionParamsProvider.Const(SubscriptionConnectionParams()),
        MockExecutor(),
        -1,
        CacheKeyResolver.DEFAULT,
        true)
    Truth.assertThat(subscriptionTransportFactory!!.subscriptionTransport).isNotNull()
    Truth.assertThat(subscriptionManager!!.state).isEqualTo(SubscriptionManagerState.DISCONNECTED)
    callbackAdapter = SubscriptionManagerCallbackAdapter()
    subscriptionManager!!.subscribe(subscription, callbackAdapter!!)
    subscriptionTransportFactory!!.callback!!.onConnected()
    subscriptionTransportFactory!!.callback!!.onMessage(OperationServerMessage.ConnectionAcknowledge)
    assertStartMessage(false)
  }

  @Test
  fun success() {
    val subscriptionId = ArrayList(subscriptionManager!!.subscriptions.keys)[0]
    subscriptionTransportFactory!!.callback!!.onMessage(
        OperationServerMessage.Data(subscriptionId.toString(), emptyMap<String, Any>())
    )
    Truth.assertThat(callbackAdapter!!.response).isNotNull()
  }

  @Test
  fun protocolNegotiationErrorNotFound() {
    val subscriptionId = ArrayList(subscriptionManager!!.subscriptions.keys)[0]
    subscriptionTransportFactory!!.callback!!.onMessage(
        OperationServerMessage.Error(
            subscriptionId.toString(),
            Collections.singletonMap("message", RealSubscriptionManager.PROTOCOL_NEGOTIATION_ERROR_NOT_FOUND)
        )
    )
    assertStartMessage(true)
  }

  @Test
  fun protocolNegotiationErrorNotSupported() {
    val subscriptionId = ArrayList(subscriptionManager!!.subscriptions.keys)[0]
    subscriptionTransportFactory!!.callback!!.onMessage(
        OperationServerMessage.Error(
            subscriptionId.toString(),
            Collections.singletonMap("message", RealSubscriptionManager.PROTOCOL_NEGOTIATION_ERROR_NOT_SUPPORTED)
        )
    )
    assertStartMessage(true)
  }

  @Test
  fun unknownError() {
    val subscriptionId = ArrayList(subscriptionManager!!.subscriptions.keys)[0]
    subscriptionTransportFactory!!.callback!!.onMessage(
        OperationServerMessage.Error(
            subscriptionId.toString(),
            Collections.singletonMap("meh", "¯\\_(ツ)_/¯")
        )
    )
    Truth.assertThat(callbackAdapter!!.error).isInstanceOf(ApolloSubscriptionServerException::class.java)
    Truth.assertThat((callbackAdapter!!.error as ApolloSubscriptionServerException?)!!.errorPayload).containsEntry("meh", "¯\\_(ツ)_/¯")
  }

  private fun OperationClientMessage.toJsonString(): String {
    return Buffer().also {
      ApolloOperationMessageSerializer.writeClientMessage(this, it)
    }.readUtf8()
  }
  private fun assertStartMessage(isWriteDocument: Boolean) {
    val subscriptionId = ArrayList(subscriptionManager!!.subscriptions.keys)[0]
    if (isWriteDocument) {

      Truth.assertThat(subscriptionTransportFactory!!.subscriptionTransport!!.lastSentMessage!!.toJsonString()).isEqualTo(
          ""
              + "{\"id\":\"" + subscriptionId.toString() + "\","
              + "\"type\":\"start\","
              + "\"payload\":{"
              + "\"variables\":{},"
              + "\"operationName\":\"SomeSubscription\","
              + "\"query\":\"subscription{\\ncommentAdded(repoFullName:\\\"repo\\\"){\\n__typename\\nid\\ncontent\\n}\\n}\","
              + "\"extensions\":{\"persistedQuery\":{\"version\":1,\"sha256Hash\":\"MockSubscription\"}}}}"
      )
    } else {
      Truth.assertThat(subscriptionTransportFactory!!.subscriptionTransport!!.lastSentMessage!!.toJsonString()).isEqualTo(
          ""
              + "{\"id\":\"" + subscriptionId.toString() + "\","
              + "\"type\":\"start\","
              + "\"payload\":{"
              + "\"variables\":{},"
              + "\"operationName\":\"SomeSubscription\","
              + "\"extensions\":{\"persistedQuery\":{\"version\":1,\"sha256Hash\":\"MockSubscription\"}}}}"
      )
    }
  }

  private class MockSubscriptionTransportFactory : SubscriptionTransport.Factory {
    var subscriptionTransport: MockSubscriptionTransport? = null
    var callback: SubscriptionTransport.Callback? = null
    override fun create(callback: SubscriptionTransport.Callback): SubscriptionTransport {
      this.callback = callback
      return MockSubscriptionTransport().also { subscriptionTransport = it }
    }
  }

  private class MockSubscriptionTransport : SubscriptionTransport {
    @Volatile
    var lastSentMessage: OperationClientMessage? = null
    override fun connect() {}
    override fun disconnect(message: OperationClientMessage) {}
    override fun send(message: OperationClientMessage) {
      lastSentMessage = message
    }
  }

  private class MockExecutor : Executor {
    override fun execute(command: Runnable) {
      command.run()
    }
  }

  private class MockSubscription(val operationId: String) : Subscription<Operation.Data> {
    override fun queryDocument(): String {
      return "subscription{\ncommentAdded(repoFullName:\"repo\"){\n__typename\nid\ncontent\n}\n}"
    }

    override fun variables(): Operation.Variables {
      return Operation.EMPTY_VARIABLES
    }

    override fun adapter(customScalarAdapters: CustomScalarAdapters): ResponseAdapter<Operation.Data> {
      throw UnsupportedOperationException()
    }

    override fun name(): String {
      return "SomeSubscription"
    }

    override fun operationId(): String {
      return operationId
    }

    override fun responseFields(): List<ResponseField.FieldSet> {
      return emptyList()
    }
  }

  private class SubscriptionManagerCallbackAdapter<D : Operation.Data> : SubscriptionManager.Callback<D> {
    @Volatile
    var response: SubscriptionResponse<D>? = null

    @Volatile
    var error: ApolloSubscriptionException? = null

    @Volatile
    var networkError: Throwable? = null

    @Volatile
    var completed = false

    @Volatile
    var terminated = false

    @Volatile
    var connected = false
    override fun onResponse(response: SubscriptionResponse<D>) {
      this.response = response
    }

    override fun onError(error: ApolloSubscriptionException) {
      this.error = error
    }

    override fun onNetworkError(t: Throwable) {
      networkError = t
    }

    override fun onCompleted() {
      completed = true
    }

    override fun onTerminated() {
      terminated = true
    }

    override fun onConnected() {
      connected = true
    }
  }
}
