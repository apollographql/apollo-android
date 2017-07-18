package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloPrefetch;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.cache.http.HttpCachePolicy;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.interceptor.FetchOptions;
import com.apollographql.apollo.internal.cache.http.HttpCache;
import com.apollographql.apollo.internal.interceptor.ApolloServerInterceptor;
import com.apollographql.apollo.internal.interceptor.RealApolloInterceptorChain;
import com.apollographql.apollo.internal.util.ApolloLogger;
import com.squareup.moshi.Moshi;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Response;

@SuppressWarnings("WeakerAccess") public final class RealApolloPrefetch implements ApolloPrefetch {
  final Operation operation;
  final HttpUrl serverUrl;
  final Call.Factory httpCallFactory;
  final HttpCache httpCache;
  final Moshi moshi;
  final ExecutorService dispatcher;
  final ApolloLogger logger;
  final ApolloCallTracker tracker;
  final ApolloInterceptorChain interceptorChain;
  final AtomicBoolean executed = new AtomicBoolean();
  final AtomicBoolean canceled = new AtomicBoolean();
  final AtomicBoolean terminated = new AtomicBoolean();
  final AtomicReference<Callback> originalCallback = new AtomicReference<>();

  public RealApolloPrefetch(Operation operation, HttpUrl serverUrl, Call.Factory httpCallFactory, HttpCache httpCache,
      Moshi moshi, ExecutorService dispatcher, ApolloLogger logger, ApolloCallTracker
      callTracker) {
    this.operation = operation;
    this.serverUrl = serverUrl;
    this.httpCallFactory = httpCallFactory;
    this.httpCache = httpCache;
    this.moshi = moshi;
    this.dispatcher = dispatcher;
    this.logger = logger;
    this.tracker = callTracker;
    interceptorChain = new RealApolloInterceptorChain(operation, Collections.<ApolloInterceptor>singletonList(
        new ApolloServerInterceptor(serverUrl, httpCallFactory, HttpCachePolicy.NETWORK_ONLY, true, moshi,
            logger)
    ));
  }

  @Override public void execute() throws ApolloException {
    if (!executed.compareAndSet(false, true)) {
      throw new IllegalStateException("Already Executed");
    }
    if (canceled.get()) {
      throw new ApolloCanceledException("Canceled");
    }

    Response httpResponse;

    try {
      tracker.registerPrefetchCall(this);
      httpResponse = interceptorChain.proceed(FetchOptions.NETWORK_ONLY).httpResponse.get();
    } catch (Exception e) {
      if (canceled.get()) {
        throw new ApolloCanceledException("Canceled", e);
      } else {
        throw e;
      }
    } finally {
      tracker.unregisterPrefetchCall(this);
    }

    httpResponse.close();

    if (canceled.get()) {
      throw new ApolloCanceledException("Canceled");
    }

    if (!httpResponse.isSuccessful()) {
      throw new ApolloHttpException(httpResponse);
    }
  }

  @Override public void enqueue(@Nullable final Callback responseCallback) {
    if (!executed.compareAndSet(false, true)) {
      throw new IllegalStateException("Already Executed");
    }
    synchronized (this) {
      if (canceled.get()) {
        if (responseCallback != null) {
          responseCallback.onCanceledError(new ApolloCanceledException("Canceled"));
          return;
        } else {
          throw new IllegalStateException("Prefetch was canceled");
        }
      }
      this.originalCallback.set(responseCallback);
      tracker.registerPrefetchCall(this);
    }
    interceptorChain.proceedAsync(dispatcher, FetchOptions.NETWORK_ONLY, interceptorCallbackProxy());
  }

  @Nonnull @Override public Operation operation() {
    return operation;
  }

  private ApolloInterceptor.CallBack interceptorCallbackProxy() {
    return new ApolloInterceptor.CallBack() {
      @Override public void onResponse(@Nonnull ApolloInterceptor.InterceptorResponse response) {
        Optional<Callback> callback = responseCallback();
        Response httpResponse = response.httpResponse.get();
        try {
          if (!callback.isPresent()) {
            logger.d("onResponse for operation: %s. No callback present. Successful: %b",
                operation.name().name(), httpResponse.isSuccessful());
            return;
          }
          if (httpResponse.isSuccessful()) {
            callback.get().onSuccess();
          } else {
            callback.get().onHttpError(new ApolloHttpException(httpResponse));
          }
        } finally {
          httpResponse.close();
        }
      }

      @Override public void onFailure(@Nonnull ApolloException e) {
        Optional<Callback> callback = terminalCallback();
        if (!callback.isPresent()) {
          logger.d(e, "onFailure for prefetch of operation: %s. No callback present.", operation().name().name());
          return;
        }
        if (e instanceof ApolloHttpException) {
          callback.get().onHttpError((ApolloHttpException) e);
        } else if (e instanceof ApolloNetworkException) {
          callback.get().onNetworkError((ApolloNetworkException) e);
        } else {
          callback.get().onFailure(e);
        }
      }

      @Override public void onCompleted() {
        terminalCallback();
      }
    };
  }

  @Override public ApolloPrefetch clone() {
    return new RealApolloPrefetch(operation, serverUrl, httpCallFactory, httpCache, moshi, dispatcher, logger, tracker);
  }

  @Override public void cancel() {
    synchronized (this) {
      canceled.set(true);
      tracker.unregisterPrefetchCall(this);
      if (!terminated.get()) {
        tracker.unregisterPrefetchCall(this);
      }
    }
    interceptorChain.dispose();
  }

  @Override public boolean isCanceled() {
    return canceled.get();
  }

  private synchronized Optional<Callback> responseCallback() {
    if (!terminated.compareAndSet(false, true)) {
      throw new IllegalStateException("Operation already terminated or failed: " + operation().name().name());
    }
    return Optional.fromNullable(originalCallback.get());
  }

  private synchronized Optional<Callback> terminalCallback() {
    if (!terminated.compareAndSet(false, true)) {
      throw new IllegalStateException("Operation already terminated or failed: " + operation().name().name());
    }
    tracker.unregisterPrefetchCall(this);
    return Optional.fromNullable(originalCallback.getAndSet(null));
  }
}
