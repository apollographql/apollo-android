package com.apollographql.apollo.interceptor;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.exception.ApolloException;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

import javax.annotation.Nonnull;

/**
 * ApolloInterceptor is responsible for observing and modifying the requests going out and the corresponding
 * responses coming back in. Typical responsibilities include adding or removing headers from the request or response
 * objects, transforming the returned responses from one type to another, etc.
 */
public interface ApolloInterceptor {

  /**
   * Intercepts the outgoing request, performs blocking operations on the request or the response returned by the next
   * set of interceptors in the chain, and returns an {@link InterceptorResponse} if the operations succeeded or throws
   * an exception if the operations failed.
   *
   * @param operation the GraphQL Operation object contained within the outgoing request.
   * @param chain     the ApolloInterceptorChain object containing the next set of interceptors.
   * @return This interceptor's response after performing operations.
   * @throws ApolloException if an error occurred while performing operations on the request/response.
   */
  @Nonnull InterceptorResponse intercept(Operation operation, ApolloInterceptorChain chain) throws ApolloException;

  /**
   * Intercepts the outgoing request and performs non blocking operations on the request or the response returned by
   * the next set of interceptors in the chain.
   *
   * @param operation  the GraphQL Operation object contained within the outgoing request.
   * @param chain      the ApolloInterceptorChain object containing the next set of interceptors.
   * @param dispatcher the ExecutorService which dispatches the non blocking operations on the request/response.
   * @param callBack   the Callback which will handle the interceptor's response or failure exception.
   */
  void interceptAsync(@Nonnull Operation operation, @Nonnull ApolloInterceptorChain chain,
      @Nonnull ExecutorService dispatcher, @Nonnull CallBack callBack);

  /**
   * Disposes of the resources which are no longer required.
   *
   * <p>A use case for this method call would be when an {@link com.apollographql.apollo.ApolloCall} needs to be
   * cancelled and resources need to be disposed of. </p>
   */
  void dispose();

  /**
   * Handles the responses returned by {@link ApolloInterceptor}
   */
  interface CallBack {

    /**
     * Gets called when the interceptor returns a response after successfully performing operations on the
     * request/response.
     *
     * @param response The response returned by the interceptor.
     */
    void onResponse(@Nonnull InterceptorResponse response);

    /**
     * Gets called when an unexpected exception occurs while performing operations on the request or processing the
     * response returned by the next set of interceptors.
     */
    void onFailure(@Nonnull ApolloException e);
  }

  /**
   * InterceptorResponse class represents the response returned by the {@link ApolloInterceptor}.
   */
  final class InterceptorResponse {
    public final Optional<okhttp3.Response> httpResponse;
    public final Optional<Response> parsedResponse;
    public final Optional<Collection<Record>> cacheRecords;

    public InterceptorResponse(okhttp3.Response httpResponse) {
      this(httpResponse, null, null);
    }

    public InterceptorResponse(okhttp3.Response httpResponse, Response parsedResponse,
        Collection<Record> cacheRecords) {
      this.httpResponse = Optional.fromNullable(httpResponse);
      this.parsedResponse = Optional.fromNullable(parsedResponse);
      this.cacheRecords = Optional.fromNullable(cacheRecords);
    }
  }
}
