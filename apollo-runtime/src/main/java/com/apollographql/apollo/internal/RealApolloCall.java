package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.http.HttpCacheControl;
import com.apollographql.apollo.cache.normalized.ApolloStore;
import com.apollographql.apollo.cache.normalized.CacheControl;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.exception.ApolloParseException;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import com.apollographql.apollo.internal.cache.http.HttpCache;
import com.apollographql.apollo.internal.interceptor.ApolloCacheInterceptor;
import com.apollographql.apollo.internal.interceptor.ApolloParseInterceptor;
import com.apollographql.apollo.internal.interceptor.ApolloServerInterceptor;
import com.apollographql.apollo.internal.interceptor.RealApolloInterceptorChain;
import com.apollographql.apollo.internal.util.ApolloLogger;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import okhttp3.Call;
import okhttp3.HttpUrl;

import static com.apollographql.apollo.api.internal.Utils.checkNotNull;

@SuppressWarnings("WeakerAccess") public final class RealApolloCall<T> implements ApolloCall<T> {
  final Operation operation;
  final HttpUrl serverUrl;
  final Call.Factory httpCallFactory;
  final HttpCache httpCache;
  final HttpCacheControl httpCacheControl;
  final Moshi moshi;
  final ResponseFieldMapper responseFieldMapper;
  final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
  final ApolloStore apolloStore;
  final CacheControl cacheControl;
  final CacheHeaders cacheHeaders;
  final ApolloInterceptorChain interceptorChain;
  final ExecutorService dispatcher;
  final ApolloLogger logger;
  final ApolloCallTracker tracker;
  final List<ApolloInterceptor> applicationInterceptors;
  volatile boolean executed;
  volatile boolean canceled;

  public RealApolloCall(Operation operation, HttpUrl serverUrl, Call.Factory httpCallFactory, HttpCache httpCache,
      HttpCacheControl httpCacheControl, Moshi moshi, ResponseFieldMapper responseFieldMapper,
      Map<ScalarType, CustomTypeAdapter> customTypeAdapters, ApolloStore apolloStore, CacheControl cacheControl,
      ExecutorService dispatcher, ApolloLogger logger, List<ApolloInterceptor> applicationInterceptors,
      ApolloCallTracker tracker, CacheHeaders cacheHeaders) {
    this.operation = operation;
    this.serverUrl = serverUrl;
    this.httpCallFactory = httpCallFactory;
    this.httpCache = httpCache;
    this.httpCacheControl = httpCacheControl;
    this.moshi = moshi;
    this.responseFieldMapper = responseFieldMapper;
    this.customTypeAdapters = customTypeAdapters;
    this.apolloStore = apolloStore;
    this.cacheControl = cacheControl;
    this.cacheHeaders = cacheHeaders;
    this.dispatcher = dispatcher;
    this.logger = logger;
    this.tracker = tracker;
    this.applicationInterceptors = applicationInterceptors;
    interceptorChain = prepareInterceptorChain(operation);
  }

  @SuppressWarnings("unchecked") @Nonnull @Override public Response<T> execute() throws ApolloException {
    synchronized (this) {
      if (executed) throw new IllegalStateException("Already Executed");
      executed = true;
    }

    if (canceled) {
      throw new ApolloCanceledException("Canceled");
    }

    Response<T> response;
    try {
      tracker.onSyncCallInProgress(this);
      response = interceptorChain.proceed().parsedResponse.or(new Response(operation));
    } catch (Exception e) {
      if (canceled) {
        throw new ApolloCanceledException("Canceled", e);
      } else {
        throw e;
      }
    } finally {
      tracker.onSyncCallFinished(this);
    }

    if (canceled) {
      throw new ApolloCanceledException("Canceled");
    }

    return response;
  }

  @Override public void enqueue(@Nullable final Callback<T> responseCallback) {
    synchronized (this) {
      if (executed) throw new IllegalStateException("Already Executed");
      executed = true;
    }
    AsyncCall asyncCall = new AsyncCall(responseCallback);
    tracker.onAsyncCallInProgress(asyncCall);
    interceptorChain.proceedAsync(dispatcher, asyncCall);
  }

  @Nonnull @Override public RealApolloWatcher<T> watcher() {
    return new RealApolloWatcher<>(clone(), apolloStore);
  }

  @Nonnull @Override public RealApolloCall<T> httpCacheControl(@Nonnull HttpCacheControl httpCacheControl) {
    synchronized (this) {
      if (executed) throw new IllegalStateException("Already Executed");
    }
    return new RealApolloCall<>(operation, serverUrl, httpCallFactory, httpCache,
        checkNotNull(httpCacheControl, "httpCacheControl == null"), moshi, responseFieldMapper, customTypeAdapters,
        apolloStore, cacheControl, dispatcher, logger, applicationInterceptors, tracker, cacheHeaders);
  }

  @Nonnull @Override public RealApolloCall<T> cacheControl(@Nonnull CacheControl cacheControl) {
    synchronized (this) {
      if (executed) throw new IllegalStateException("Already Executed");
    }
    return new RealApolloCall<>(operation, serverUrl, httpCallFactory, httpCache, httpCacheControl, moshi,
        responseFieldMapper, customTypeAdapters, apolloStore, checkNotNull(cacheControl, "cacheControl == null"),
        dispatcher, logger, applicationInterceptors, tracker, cacheHeaders);
  }

  @Nonnull @Override public RealApolloCall<T> cacheHeaders(@Nonnull CacheHeaders cacheHeaders) {
    synchronized (this) {
      if (executed) throw new IllegalStateException("Already Executed");
    }
    return new RealApolloCall<>(operation, serverUrl, httpCallFactory, httpCache, httpCacheControl, moshi,
        responseFieldMapper, customTypeAdapters, apolloStore, cacheControl,
        dispatcher, logger, applicationInterceptors, tracker, checkNotNull(cacheHeaders, "cacheHeaders == null"));
  }

  @Override public void cancel() {
    canceled = true;
    interceptorChain.dispose();
  }

  @Override public boolean isCanceled() {
    return canceled;
  }

  @Override @Nonnull public RealApolloCall<T> clone() {
    return new RealApolloCall<>(operation, serverUrl, httpCallFactory, httpCache, httpCacheControl, moshi,
        responseFieldMapper, customTypeAdapters, apolloStore, cacheControl, dispatcher, logger,
        applicationInterceptors, tracker, cacheHeaders);

  }

  final class AsyncCall implements ApolloInterceptor.CallBack {

    private final Callback<T> responseCallback;

    private AsyncCall(Callback<T> callback) {
      this.responseCallback = callback;
    }

    @Override public void onResponse(@Nonnull ApolloInterceptor.InterceptorResponse response) {
      try {

        if (responseCallback == null) {
          return;
        }

        if (canceled) {
          responseCallback.onCanceledError(new ApolloCanceledException("Canceled"));
        } else {
          //noinspection unchecked
          responseCallback.onResponse(response.parsedResponse.get());
        }

      } finally {
        tracker.onAsyncCallFinished(this);
      }
    }

    @Override public void onFailure(@Nonnull ApolloException e) {
      try {

        if (responseCallback == null) {
          return;
        }

        if (canceled) {
          responseCallback.onCanceledError(new ApolloCanceledException("Canceled", e));
        } else if (e instanceof ApolloHttpException) {
          responseCallback.onHttpError((ApolloHttpException) e);
        } else if (e instanceof ApolloParseException) {
          responseCallback.onParseError((ApolloParseException) e);
        } else if (e instanceof ApolloNetworkException) {
          responseCallback.onNetworkError((ApolloNetworkException) e);
        } else {
          responseCallback.onFailure(e);
        }
      } finally {
        tracker.onAsyncCallFinished(this);
      }
    }
  }

  private ApolloInterceptorChain prepareInterceptorChain(Operation operation) {
    List<ApolloInterceptor> interceptors = new ArrayList<>();

    interceptors.addAll(applicationInterceptors);
    interceptors.add(new ApolloCacheInterceptor(apolloStore, cacheControl, cacheHeaders, responseFieldMapper,
        customTypeAdapters, dispatcher, logger));
    interceptors.add(new ApolloParseInterceptor(httpCache, apolloStore.networkResponseNormalizer(), responseFieldMapper,
        customTypeAdapters, logger));
    interceptors.add(new ApolloServerInterceptor(serverUrl, httpCallFactory, httpCacheControl, false, moshi,
        logger));

    return new RealApolloInterceptorChain(operation, interceptors);
  }
}
