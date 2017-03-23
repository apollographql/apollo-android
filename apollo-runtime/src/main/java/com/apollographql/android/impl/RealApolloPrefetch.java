package com.apollographql.android.impl;

import com.apollographql.android.ApolloPrefetch;
import com.apollographql.android.api.graphql.Operation;
import com.apollographql.android.cache.http.HttpCache;
import com.apollographql.android.cache.http.HttpCacheControl;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import okhttp3.Call;
import okhttp3.HttpUrl;

@SuppressWarnings("WeakerAccess") final class RealApolloPrefetch implements ApolloPrefetch {
  final Operation operation;
  final HttpUrl serverUrl;
  final Call.Factory httpCallFactory;
  final HttpCache httpCache;
  final Moshi moshi;
  final ExecutorService dispatcher;
  final CallInterceptorChain interceptorChain;
  volatile boolean executed;

  RealApolloPrefetch(Operation operation, HttpUrl serverUrl, Call.Factory httpCallFactory, HttpCache httpCache,
      Moshi moshi, ExecutorService dispatcher) {
    this.operation = operation;
    this.serverUrl = serverUrl;
    this.httpCallFactory = httpCallFactory;
    this.httpCache = httpCache;
    this.moshi = moshi;
    this.dispatcher = dispatcher;
    interceptorChain = new RealCallInterceptorChain(operation)
        .chain(new ServerCallInterceptor(serverUrl, httpCallFactory, HttpCacheControl.NETWORK_FIRST, true, moshi));
  }

  @Override public void execute() throws IOException {
    synchronized (this) {
      if (executed) throw new IllegalStateException("Already Executed");
      executed = true;
    }
    interceptorChain.proceed().httpResponse.close();
  }

  @Nonnull @Override public ApolloPrefetch enqueue(@Nullable final Callback callback) {
    synchronized (this) {
      if (executed) throw new IllegalStateException("Already Executed");
      executed = true;
    }

    interceptorChain.proceedAsync(dispatcher, new CallInterceptor.CallBack() {
      @SuppressWarnings("unchecked") @Override public void onResponse(CallInterceptor.InterceptorResponse response) {
        try {
          response.httpResponse.close();
          if (callback != null) {
            callback.onSuccess();
          }
        } catch (Exception e) {
          onFailure(e);
        }

      }

      @Override public void onFailure(Throwable t) {
        if (callback != null) {
          callback.onFailure(t);
        }
      }
    });
    return this;
  }

  @Override public ApolloPrefetch clone() {
    return new RealApolloPrefetch(operation, serverUrl, httpCallFactory, httpCache, moshi, dispatcher);
  }

  @Override public void cancel() {
    interceptorChain.dispose();
  }
}
