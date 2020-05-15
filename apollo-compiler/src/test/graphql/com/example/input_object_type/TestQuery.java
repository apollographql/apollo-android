// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.input_object_type;

import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.InputFieldMarshaller;
import com.apollographql.apollo.api.internal.InputFieldWriter;
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.QueryDocumentMinifier;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseReader;
import com.apollographql.apollo.api.internal.ResponseWriter;
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import com.example.input_object_type.type.Episode;
import com.example.input_object_type.type.ReviewInput;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TestQuery implements Mutation<TestQuery.Data, Optional<TestQuery.Data>, TestQuery.Variables> {
  public static final String OPERATION_ID = "9850c60bd20e2361afd7a41d51b709fcba9637809e387afe5c7a1cb738fc254b";

  public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify(
    "mutation TestQuery($ep: Episode!, $review: ReviewInput!) {\n"
        + "  createReview(episode: $ep, review: $review) {\n"
        + "    __typename\n"
        + "    stars\n"
        + "    commentary\n"
        + "  }\n"
        + "}"
  );

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "TestQuery";
    }
  };

  private final TestQuery.Variables variables;

  public TestQuery(@NotNull Episode ep, @NotNull ReviewInput review) {
    Utils.checkNotNull(ep, "ep == null");
    Utils.checkNotNull(review, "review == null");
    variables = new TestQuery.Variables(ep, review);
  }

  @Override
  public String operationId() {
    return OPERATION_ID;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public Optional<TestQuery.Data> wrapData(TestQuery.Data data) {
    return Optional.fromNullable(data);
  }

  @Override
  public TestQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<TestQuery.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public OperationName name() {
    return OPERATION_NAME;
  }

  @Override
  @NotNull
  public Response<Optional<TestQuery.Data>> parse(@NotNull final BufferedSource source,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<Optional<TestQuery.Data>> parse(@NotNull final ByteString byteString,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return parse(new Buffer().write(byteString), scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<Optional<TestQuery.Data>> parse(@NotNull final BufferedSource source) throws
      IOException {
    return parse(source, ScalarTypeAdapters.DEFAULT);
  }

  @Override
  @NotNull
  public Response<Optional<TestQuery.Data>> parse(@NotNull final ByteString byteString) throws
      IOException {
    return parse(byteString, ScalarTypeAdapters.DEFAULT);
  }

  @Override
  @NotNull
  public ByteString composeRequestBody(@NotNull final ScalarTypeAdapters scalarTypeAdapters) {
    return OperationRequestBodyComposer.compose(this, false, true, scalarTypeAdapters);
  }

  @NotNull
  @Override
  public ByteString composeRequestBody() {
    return OperationRequestBodyComposer.compose(this, false, true, ScalarTypeAdapters.DEFAULT);
  }

  public static final class Builder {
    @NotNull
    private Episode ep;

    @NotNull
    private ReviewInput review;

    Builder() {
    }

    public Builder ep(@NotNull Episode ep) {
      this.ep = ep;
      return this;
    }

    public Builder review(@NotNull ReviewInput review) {
      this.review = review;
      return this;
    }

    public TestQuery build() {
      Utils.checkNotNull(ep, "ep == null");
      Utils.checkNotNull(review, "review == null");
      return new TestQuery(ep, review);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final @NotNull Episode ep;

    private final @NotNull ReviewInput review;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@NotNull Episode ep, @NotNull ReviewInput review) {
      this.ep = ep;
      this.review = review;
      this.valueMap.put("ep", ep);
      this.valueMap.put("review", review);
    }

    public @NotNull Episode ep() {
      return ep;
    }

    public @NotNull ReviewInput review() {
      return review;
    }

    @Override
    public Map<String, Object> valueMap() {
      return Collections.unmodifiableMap(valueMap);
    }

    @Override
    public InputFieldMarshaller marshaller() {
      return new InputFieldMarshaller() {
        @Override
        public void marshal(InputFieldWriter writer) throws IOException {
          writer.writeString("ep", ep.rawValue());
          writer.writeObject("review", review.marshaller());
        }
      };
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("createReview", "createReview", new UnmodifiableMapBuilder<String, Object>(2)
      .put("episode", new UnmodifiableMapBuilder<String, Object>(2)
        .put("kind", "Variable")
        .put("variableName", "ep")
        .build())
      .put("review", new UnmodifiableMapBuilder<String, Object>(2)
        .put("kind", "Variable")
        .put("variableName", "review")
        .build())
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final Optional<CreateReview> createReview;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Data(@Nullable CreateReview createReview) {
      this.createReview = Optional.fromNullable(createReview);
    }

    public Optional<CreateReview> createReview() {
      return this.createReview;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], createReview.isPresent() ? createReview.get().marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "createReview=" + createReview
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return this.createReview.equals(that.createReview);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= createReview.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final CreateReview.Mapper createReviewFieldMapper = new CreateReview.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final CreateReview createReview = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<CreateReview>() {
          @Override
          public CreateReview read(ResponseReader reader) {
            return createReviewFieldMapper.map(reader);
          }
        });
        return new Data(createReview);
      }
    }
  }

  /**
   * Represents a review for a movie
   */
  public static class CreateReview {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("stars", "stars", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("commentary", "commentary", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final int stars;

    final Optional<String> commentary;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public CreateReview(@NotNull String __typename, int stars, @Nullable String commentary) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.stars = stars;
      this.commentary = Optional.fromNullable(commentary);
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The number of stars this review gave, 1-5
     */
    public int stars() {
      return this.stars;
    }

    /**
     * Comment about the movie
     */
    public Optional<String> commentary() {
      return this.commentary;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeInt($responseFields[1], stars);
          writer.writeString($responseFields[2], commentary.isPresent() ? commentary.get() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "CreateReview{"
          + "__typename=" + __typename + ", "
          + "stars=" + stars + ", "
          + "commentary=" + commentary
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof CreateReview) {
        CreateReview that = (CreateReview) o;
        return this.__typename.equals(that.__typename)
         && this.stars == that.stars
         && this.commentary.equals(that.commentary);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= stars;
        h *= 1000003;
        h ^= commentary.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<CreateReview> {
      @Override
      public CreateReview map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final int stars = reader.readInt($responseFields[1]);
        final String commentary = reader.readString($responseFields[2]);
        return new CreateReview(__typename, stars, commentary);
      }
    }
  }
}
