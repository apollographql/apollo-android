package com.apollostack.android;

import com.apollostack.api.GraphQLOperation;
import com.squareup.moshi.Moshi;

import org.junit.Test;

import java.lang.annotation.Annotation;

import static com.google.common.truth.Truth.assertThat;

public class ApolloConverterFactoryTest {
  private final Annotation[] annotations = new Annotation[0];
  private final Moshi moshi = new Moshi.Builder().build();
  private final ApolloConverterFactory factory = new ApolloConverterFactory(moshi);

  private static class TestData implements GraphQLOperation.Data {
  }

  private static class Foo {
  }

  @Test public void responsebodyAppliesToDataClasses() {
    assertThat(factory.responseBodyConverter(TestData.class, null, null)).isNotNull();
  }

  @Test public void responseBodydoesNotApplyToOtherClasses() {
    assertThat(factory.responseBodyConverter(Foo.class, null, null)).isNull();
  }

  @Test public void requestBodyDoesNotApplyToDataClasses() {
    assertThat(factory.requestBodyConverter(TestData.class, annotations, annotations, null))
        .isNull();
  }

  @Test public void requestBodyDoesNotApplyToOtherClasses() {
    assertThat(factory.requestBodyConverter(Foo.class, annotations, annotations, null))
        .isNull();
  }

  @Test public void requestBodyAppliesToPostBodyClass() {
    assertThat(factory.requestBodyConverter(GraphQLOperation.class, annotations, annotations, null))
        .isNotNull();
  }
}