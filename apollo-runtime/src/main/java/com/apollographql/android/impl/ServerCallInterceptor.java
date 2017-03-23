package com.apollographql.android.impl;

import com.apollographql.android.api.graphql.Operation;
import com.apollographql.android.cache.http.HttpCache;
import com.apollographql.android.cache.http.HttpCacheControl;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

@SuppressWarnings("WeakerAccess") final class ServerCallInterceptor implements CallInterceptor {
  private static final String ACCEPT_TYPE = "application/json";
  private static final String CONTENT_TYPE = "application/json";
  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

  final HttpUrl serverUrl;
  final okhttp3.Call.Factory httpCallFactory;
  final HttpCacheControl cacheControl;
  final boolean prefetch;
  final Moshi moshi;
  volatile Call httpCall;

  public ServerCallInterceptor(HttpUrl serverUrl, Call.Factory httpCallFactory, HttpCacheControl cacheControl,
      boolean prefetch, Moshi moshi) {
    this.serverUrl = serverUrl;
    this.httpCallFactory = httpCallFactory;
    this.cacheControl = cacheControl;
    this.prefetch = prefetch;
    this.moshi = moshi;
  }

  @Override public InterceptorResponse intercept(Operation operation, CallInterceptorChain chain) throws IOException {
    httpCall = httpCall(operation);
    return new InterceptorResponse(httpCall.execute());
  }

  @Override
  public void interceptAsync(final Operation operation, final CallInterceptorChain chain, ExecutorService dispatcher,
      final CallBack callBack) {
    dispatcher.execute(new Runnable() {
      @Override public void run() {
        try {
          httpCall = httpCall(operation);
        } catch (Exception e) {
          callBack.onFailure(e);
          return;
        }

        httpCall.enqueue(new Callback() {
          @Override public void onFailure(Call call, IOException e) {
            callBack.onFailure(e);
          }

          @Override public void onResponse(Call call, Response response) throws IOException {
            callBack.onResponse(new CallInterceptor.InterceptorResponse(response));
          }
        });
      }
    });
  }

  @Override public void dispose() {
    Call httpCall = this.httpCall;
    if (httpCall != null) {
      httpCall.cancel();
    }
    this.httpCall = null;
  }

  private Call httpCall(Operation operation) throws IOException {
    RequestBody requestBody = httpRequestBody(operation);
    String cacheKey = cacheKey(requestBody);
    Request request = new Request.Builder()
        .url(serverUrl)
        .post(requestBody)
        .header("Accept", ACCEPT_TYPE)
        .header("Content-Type", CONTENT_TYPE)
        .header(HttpCache.CACHE_KEY_HEADER, cacheKey)
        .header(HttpCache.CACHE_CONTROL_HEADER, cacheControl.httpHeader)
        .header(HttpCache.CACHE_PREFETCH_HEADER, Boolean.toString(prefetch))
        .build();
    return httpCallFactory.newCall(request);
  }

  private RequestBody httpRequestBody(Operation operation) throws IOException {
    JsonAdapter<Operation> adapter = new OperationJsonAdapter(moshi);
    Buffer buffer = new Buffer();
    adapter.toJson(buffer, operation);
    return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
  }

  static String cacheKey(RequestBody requestBody) throws IOException {
    Buffer hashBuffer = new Buffer();
    requestBody.writeTo(hashBuffer);
    return hashBuffer.readByteString().md5().hex();
  }
}
