package com.example.arguments_complex;

import com.apollographql.android.api.graphql.Field;
import com.apollographql.android.api.graphql.Operation;
import com.apollographql.android.api.graphql.Query;
import com.apollographql.android.api.graphql.ResponseFieldMapper;
import com.apollographql.android.api.graphql.ResponseReader;
import com.apollographql.android.api.graphql.util.UnmodifiableMapBuilder;
import com.example.arguments_complex.type.Episode;
import java.io.IOException;
import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public final class TestQuery implements Query<TestQuery.Data, TestQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query TestQuery($episode: Episode, $stars: Int!, $greenValue: Float!) {\n"
      + "  heroWithReview(episode: $episode, review: {stars: $stars, favoriteColor: {red: 0, green: $greenValue, blue: 0}}) {\n"
      + "    __typename\n"
      + "    name\n"
      + "    height(unit: FOOT)\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private final TestQuery.Variables variables;

  public TestQuery(TestQuery.Variables variables) {
    this.variables = variables;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public TestQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<? extends Operation.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static final class Variables extends Operation.Variables {
    private final @Nullable Episode episode;

    private final int stars;

    private final double greenValue;

    private final Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@Nullable Episode episode, int stars, double greenValue) {
      this.episode = episode;
      this.stars = stars;
      this.greenValue = greenValue;
      this.valueMap.put("episode", episode);
      this.valueMap.put("stars", stars);
      this.valueMap.put("greenValue", greenValue);
    }

    public @Nullable Episode episode() {
      return episode;
    }

    public int stars() {
      return stars;
    }

    public double greenValue() {
      return greenValue;
    }

    @Override
    public Map<String, Object> valueMap() {
      return Collections.unmodifiableMap(valueMap);
    }

    public static Builder builder() {
      return new Builder();
    }

    public static final class Builder {
      private @Nullable Episode episode;

      private int stars;

      private double greenValue;

      Builder() {
      }

      public Builder episode(@Nullable Episode episode) {
        this.episode = episode;
        return this;
      }

      public Builder stars(int stars) {
        this.stars = stars;
        return this;
      }

      public Builder greenValue(double greenValue) {
        this.greenValue = greenValue;
        return this;
      }

      public Variables build() {
        return new Variables(episode, stars, greenValue);
      }
    }
  }

  public static class Data implements Operation.Data {
    private final @Nullable HeroWithReview heroWithReview;

    public Data(@Nullable HeroWithReview heroWithReview) {
      this.heroWithReview = heroWithReview;
    }

    public @Nullable HeroWithReview heroWithReview() {
      return this.heroWithReview;
    }

    @Override
    public String toString() {
      return "Data{"
        + "heroWithReview=" + heroWithReview
        + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return ((this.heroWithReview == null) ? (that.heroWithReview == null) : this.heroWithReview.equals(that.heroWithReview));
      }
      return false;
    }

    @Override
    public int hashCode() {
      int h = 1;
      h *= 1000003;
      h ^= (heroWithReview == null) ? 0 : heroWithReview.hashCode();
      return h;
    }

    public static class HeroWithReview {
      private final @Nonnull String name;

      private final @Nullable Double height;

      public HeroWithReview(@Nonnull String name, @Nullable Double height) {
        this.name = name;
        this.height = height;
      }

      public @Nonnull String name() {
        return this.name;
      }

      public @Nullable Double height() {
        return this.height;
      }

      @Override
      public String toString() {
        return "HeroWithReview{"
          + "name=" + name + ", "
          + "height=" + height
          + "}";
      }

      @Override
      public boolean equals(Object o) {
        if (o == this) {
          return true;
        }
        if (o instanceof HeroWithReview) {
          HeroWithReview that = (HeroWithReview) o;
          return ((this.name == null) ? (that.name == null) : this.name.equals(that.name))
           && ((this.height == null) ? (that.height == null) : this.height.equals(that.height));
        }
        return false;
      }

      @Override
      public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (name == null) ? 0 : name.hashCode();
        h *= 1000003;
        h ^= (height == null) ? 0 : height.hashCode();
        return h;
      }

      public static final class Mapper implements ResponseFieldMapper<HeroWithReview> {
        final Field[] fields = {
          Field.forString("name", "name", null, false),
          Field.forDouble("height", "height", new UnmodifiableMapBuilder<String, Object>(1)
            .put("unit", "FOOT")
          .build(), true)
        };

        @Override
        public HeroWithReview map(ResponseReader reader) throws IOException {
          final String name = reader.read(fields[0]);
          final Double height = reader.read(fields[1]);
          return new HeroWithReview(name, height);
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final HeroWithReview.Mapper heroWithReviewFieldMapper = new HeroWithReview.Mapper();

      final Field[] fields = {
        Field.forObject("heroWithReview", "heroWithReview", new UnmodifiableMapBuilder<String, Object>(2)
          .put("review", new UnmodifiableMapBuilder<String, Object>(2)
            .put("stars", new UnmodifiableMapBuilder<String, Object>(2)
              .put("kind", "Variable")
              .put("variableName", "stars")
            .build())
            .put("favoriteColor", new UnmodifiableMapBuilder<String, Object>(3)
              .put("red", "0.0")
              .put("green", new UnmodifiableMapBuilder<String, Object>(2)
                .put("kind", "Variable")
                .put("variableName", "greenValue")
              .build())
              .put("blue", "0.0")
            .build())
          .build())
          .put("episode", new UnmodifiableMapBuilder<String, Object>(2)
            .put("kind", "Variable")
            .put("variableName", "episode")
          .build())
        .build(), true, new Field.ObjectReader<HeroWithReview>() {
          @Override public HeroWithReview read(final ResponseReader reader) throws IOException {
            return heroWithReviewFieldMapper.map(reader);
          }
        })
      };

      @Override
      public Data map(ResponseReader reader) throws IOException {
        final HeroWithReview heroWithReview = reader.read(fields[0]);
        return new Data(heroWithReview);
      }
    }
  }
}
