package com.apollographql.android;

import com.apollographql.android.api.graphql.Operation;
import com.apollographql.android.api.graphql.Response;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ApolloCall {

  void cancel();

  @Nonnull <T extends Operation.Data> Response<T> execute() throws IOException;

  @Nonnull <T extends Operation.Data> ApolloCall enqueue(@Nullable Callback<T> callback);

  @Nonnull ApolloCall clone();

  public interface Callback<T extends Operation.Data> {
    void onResponse(Response<T> response);

    void onFailure(Exception e);
  }
}
