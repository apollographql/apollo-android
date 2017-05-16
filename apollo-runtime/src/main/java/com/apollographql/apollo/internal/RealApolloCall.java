package com.apollographql.apollo.internal;

import com.apollographql.apollo.ApolloMutationCall;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.http.HttpCachePolicy;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import okhttp3.Call;
import okhttp3.HttpUrl;

import static com.apollographql.apollo.api.internal.Utils.checkNotNull;

@SuppressWarnings("WeakerAccess")
public final class RealApolloCall<T> implements ApolloQueryCall<T>, ApolloMutationCall<T> {
  final Operation operation;
  final HttpUrl serverUrl;
  final Call.Factory httpCallFactory;
  final HttpCache httpCache;
  final HttpCachePolicy.Policy httpCachePolicy;
  final Moshi moshi;
  final ResponseFieldMapper responseFieldMapper;
  final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
  final ApolloStore apolloStore;
  final CacheControl cacheControl;
  final CacheHeaders cacheHeaders;
  final ApolloInterceptorChain interceptorChain;
  final ExecutorService dispatcher;
  final ApolloLogger logger;
  final List<ApolloInterceptor> applicationInterceptors;
  final List<OperationName> refetchQueryNames;
  final AtomicBoolean executed = new AtomicBoolean();
  volatile boolean canceled;

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  private RealApolloCall(Builder<T> builder) {
    this.operation = builder.operation;
    this.serverUrl = builder.serverUrl;
    this.httpCallFactory = builder.httpCallFactory;
    this.httpCache = builder.httpCache;
    this.httpCachePolicy = builder.httpCachePolicy;
    this.moshi = builder.moshi;
    this.responseFieldMapper = builder.responseFieldMapper;
    this.customTypeAdapters = builder.customTypeAdapters;
    this.apolloStore = builder.apolloStore;
    this.cacheControl = builder.cacheControl;
    this.cacheHeaders = builder.cacheHeaders;
    this.dispatcher = builder.dispatcher;
    this.logger = builder.logger;
    this.applicationInterceptors = builder.applicationInterceptors;
    this.refetchQueryNames = builder.refetchQueryNames;
    interceptorChain = prepareInterceptorChain(operation);
  }

  @SuppressWarnings("unchecked") @Nonnull @Override public Response<T> execute() throws ApolloException {
    if (!executed.compareAndSet(false, true)) {
      throw new IllegalStateException("Already Executed");
    }

    if (canceled) {
      throw new ApolloCanceledException("Canceled");
    }

    Response<T> response;
    try {
      response = interceptorChain.proceed().parsedResponse.or(new Response(operation));
    } catch (Exception e) {
      if (canceled) {
        throw new ApolloCanceledException("Canceled", e);
      } else {
        throw e;
      }
    }

    if (canceled) {
      throw new ApolloCanceledException("Canceled");
    }

    return response;
  }

  @Override public void enqueue(@Nullable final Callback<T> callback) {
    if (!executed.compareAndSet(false, true)) {
      throw new IllegalStateException("Already Executed");
    }

    interceptorChain.proceedAsync(dispatcher, new ApolloInterceptor.CallBack() {
      @Override public void onResponse(@Nonnull ApolloInterceptor.InterceptorResponse response) {
        if (callback == null) {
          return;
        }

        if (canceled) {
          callback.onCanceledError(new ApolloCanceledException("Canceled"));
        } else {
          //noinspection unchecked
          callback.onResponse(response.parsedResponse.get());
        }
      }

      @Override public void onFailure(@Nonnull ApolloException e) {
        if (callback == null) {
          return;
        }

        if (canceled) {
          callback.onCanceledError(new ApolloCanceledException("Canceled", e));
        } else if (e instanceof ApolloHttpException) {
          callback.onHttpError((ApolloHttpException) e);
        } else if (e instanceof ApolloParseException) {
          callback.onParseError((ApolloParseException) e);
        } else if (e instanceof ApolloNetworkException) {
          callback.onNetworkError((ApolloNetworkException) e);
        } else {
          callback.onFailure(e);
        }
      }
    });
  }

  @Nonnull @Override public RealApolloQueryWatcher<T> watcher() {
    return new RealApolloQueryWatcher<>(clone(), apolloStore);
  }

  @Nonnull @Override public RealApolloCall<T> httpCachePolicy(@Nonnull HttpCachePolicy.Policy httpCachePolicy) {
    if (executed.get()) throw new IllegalStateException("Already Executed");
    return toBuilder()
        .httpCachePolicy(checkNotNull(httpCachePolicy, "httpCachePolicy == null"))
        .build();
  }

  @Nonnull @Override public RealApolloCall<T> cacheControl(@Nonnull CacheControl cacheControl) {
    if (executed.get()) throw new IllegalStateException("Already Executed");
    return toBuilder()
        .cacheControl(checkNotNull(cacheControl, "cacheControl == null"))
        .build();
  }

  @Nonnull @Override public RealApolloCall<T> cacheHeaders(@Nonnull CacheHeaders cacheHeaders) {
    if (executed.get()) throw new IllegalStateException("Already Executed");
    return toBuilder()
        .cacheHeaders(checkNotNull(cacheHeaders, "cacheHeaders == null"))
        .build();
  }

  @Override public void cancel() {
    canceled = true;
    interceptorChain.dispose();
  }

  @Override public boolean isCanceled() {
    return canceled;
  }

  @Override @Nonnull public RealApolloCall<T> clone() {
    return toBuilder().build();
  }

  @Nonnull @Override public ApolloMutationCall<T> refetchQueries(@Nonnull OperationName... operationNames) {
    if (executed.get()) throw new IllegalStateException("Already Executed");
    return toBuilder()
        .refetchQueryNames(Arrays.asList(checkNotNull(operationNames, "operationNames == null")))
        .build();
  }

  public Builder<T> toBuilder() {
    return RealApolloCall.<T>builder()
        .operation(operation)
        .serverUrl(serverUrl)
        .httpCallFactory(httpCallFactory)
        .httpCache(httpCache)
        .httpCachePolicy(httpCachePolicy)
        .moshi(moshi)
        .responseFieldMapper(responseFieldMapper)
        .customTypeAdapters(customTypeAdapters)
        .apolloStore(apolloStore)
        .cacheControl(cacheControl)
        .cacheHeaders(cacheHeaders)
        .dispatcher(dispatcher)
        .logger(logger)
        .applicationInterceptors(applicationInterceptors)
        .refetchQueryNames(refetchQueryNames);
  }

  private ApolloInterceptorChain prepareInterceptorChain(Operation operation) {
    List<ApolloInterceptor> interceptors = new ArrayList<>();
    HttpCachePolicy.Policy httpCachePolicy = operation instanceof Query ? this.httpCachePolicy : null;

    interceptors.addAll(applicationInterceptors);
    interceptors.add(new ApolloCacheInterceptor(apolloStore, cacheControl, cacheHeaders, responseFieldMapper,
        customTypeAdapters, dispatcher, logger));
    interceptors.add(new ApolloParseInterceptor(httpCache, apolloStore.networkResponseNormalizer(), responseFieldMapper,
        customTypeAdapters, logger));
    interceptors.add(new ApolloServerInterceptor(serverUrl, httpCallFactory, httpCachePolicy, false, moshi, logger));

    return new RealApolloInterceptorChain(operation, interceptors);
  }

  public static final class Builder<T> {
    Operation operation;
    HttpUrl serverUrl;
    Call.Factory httpCallFactory;
    HttpCache httpCache;
    HttpCachePolicy.Policy httpCachePolicy;
    Moshi moshi;
    ResponseFieldMapper responseFieldMapper;
    Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
    ApolloStore apolloStore;
    CacheControl cacheControl;
    CacheHeaders cacheHeaders;
    ApolloInterceptorChain interceptorChain;
    ExecutorService dispatcher;
    ApolloLogger logger;
    List<ApolloInterceptor> applicationInterceptors;
    List<OperationName> refetchQueryNames;

    public Builder<T> operation(Operation operation) {
      this.operation = operation;
      return this;
    }

    public Builder<T> serverUrl(HttpUrl serverUrl) {
      this.serverUrl = serverUrl;
      return this;
    }

    public Builder<T> httpCallFactory(Call.Factory httpCallFactory) {
      this.httpCallFactory = httpCallFactory;
      return this;
    }

    public Builder<T> httpCache(HttpCache httpCache) {
      this.httpCache = httpCache;
      return this;
    }

    public Builder<T> httpCachePolicy(HttpCachePolicy.Policy httpCachePolicy) {
      this.httpCachePolicy = httpCachePolicy;
      return this;
    }

    public Builder<T> moshi(Moshi moshi) {
      this.moshi = moshi;
      return this;
    }

    public Builder<T> responseFieldMapper(ResponseFieldMapper responseFieldMapper) {
      this.responseFieldMapper = responseFieldMapper;
      return this;
    }

    public Builder<T> customTypeAdapters(Map<ScalarType, CustomTypeAdapter> customTypeAdapters) {
      this.customTypeAdapters = customTypeAdapters;
      return this;
    }

    public Builder<T> apolloStore(ApolloStore apolloStore) {
      this.apolloStore = apolloStore;
      return this;
    }

    public Builder<T> cacheControl(CacheControl cacheControl) {
      this.cacheControl = cacheControl;
      return this;
    }

    public Builder<T> cacheHeaders(CacheHeaders cacheHeaders) {
      this.cacheHeaders = cacheHeaders;
      return this;
    }

    public Builder<T> interceptorChain(ApolloInterceptorChain interceptorChain) {
      this.interceptorChain = interceptorChain;
      return this;
    }

    public Builder<T> dispatcher(ExecutorService dispatcher) {
      this.dispatcher = dispatcher;
      return this;
    }

    public Builder<T> logger(ApolloLogger logger) {
      this.logger = logger;
      return this;
    }

    public Builder<T> applicationInterceptors(List<ApolloInterceptor> applicationInterceptors) {
      this.applicationInterceptors = applicationInterceptors;
      return this;
    }

    public Builder<T> refetchQueryNames(List<OperationName> refetchQueryNames) {
      this.refetchQueryNames = refetchQueryNames;
      return this;
    }

    Builder() {
    }

    public RealApolloCall<T> build() {
      return new RealApolloCall<>(this);
    }
  }
}
