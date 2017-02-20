package com.apollographql.android;

import com.apollographql.android.api.graphql.Operation;

import javax.annotation.Nonnull;

public interface ApolloCallFactory {
  <T extends Operation> ApolloCall newCall(@Nonnull T operation);
}
