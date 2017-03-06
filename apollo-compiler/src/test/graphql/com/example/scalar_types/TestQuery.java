package com.example.scalar_types;

import com.apollographql.android.api.graphql.Field;
import com.apollographql.android.api.graphql.Operation;
import com.apollographql.android.api.graphql.Query;
import com.apollographql.android.api.graphql.ResponseFieldMapper;
import com.apollographql.android.api.graphql.ResponseReader;
import java.io.IOException;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public final class TestQuery implements Query<TestQuery.Data, Operation.Variables> {
  public static final String OPERATION_DEFINITION = "";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private final Operation.Variables variables;

  public TestQuery() {
    this.variables = Operation.EMPTY_VARIABLES;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public Operation.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<? extends Operation.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static class Data implements Operation.Data {
    private final @Nullable String graphQlString;

    private final @Nullable String graphQlIdNullable;

    private final @Nonnull String graphQlIdNonNullable;

    private final @Nullable Integer graphQlIntNullable;

    private final int graphQlIntNonNullable;

    private final @Nullable Double graphQlFloatNullable;

    private final double graphQlFloatNonNullable;

    private final @Nullable Boolean graphQlBooleanNullable;

    private final boolean graphQlBooleanNonNullable;

    private final @Nullable List<Integer> graphQlListOfInt;

    private final @Nullable List<GraphQlListOfObject> graphQlListOfObjects;

    public Data(@Nullable String graphQlString, @Nullable String graphQlIdNullable,
        @Nonnull String graphQlIdNonNullable, @Nullable Integer graphQlIntNullable,
        int graphQlIntNonNullable, @Nullable Double graphQlFloatNullable,
        double graphQlFloatNonNullable, @Nullable Boolean graphQlBooleanNullable,
        boolean graphQlBooleanNonNullable, @Nullable List<Integer> graphQlListOfInt,
        @Nullable List<GraphQlListOfObject> graphQlListOfObjects) {
      this.graphQlString = graphQlString;
      this.graphQlIdNullable = graphQlIdNullable;
      this.graphQlIdNonNullable = graphQlIdNonNullable;
      this.graphQlIntNullable = graphQlIntNullable;
      this.graphQlIntNonNullable = graphQlIntNonNullable;
      this.graphQlFloatNullable = graphQlFloatNullable;
      this.graphQlFloatNonNullable = graphQlFloatNonNullable;
      this.graphQlBooleanNullable = graphQlBooleanNullable;
      this.graphQlBooleanNonNullable = graphQlBooleanNonNullable;
      this.graphQlListOfInt = graphQlListOfInt;
      this.graphQlListOfObjects = graphQlListOfObjects;
    }

    public @Nullable String graphQlString() {
      return this.graphQlString;
    }

    public @Nullable String graphQlIdNullable() {
      return this.graphQlIdNullable;
    }

    public @Nonnull String graphQlIdNonNullable() {
      return this.graphQlIdNonNullable;
    }

    public @Nullable Integer graphQlIntNullable() {
      return this.graphQlIntNullable;
    }

    public int graphQlIntNonNullable() {
      return this.graphQlIntNonNullable;
    }

    public @Nullable Double graphQlFloatNullable() {
      return this.graphQlFloatNullable;
    }

    public double graphQlFloatNonNullable() {
      return this.graphQlFloatNonNullable;
    }

    public @Nullable Boolean graphQlBooleanNullable() {
      return this.graphQlBooleanNullable;
    }

    public boolean graphQlBooleanNonNullable() {
      return this.graphQlBooleanNonNullable;
    }

    public @Nullable List<Integer> graphQlListOfInt() {
      return this.graphQlListOfInt;
    }

    public @Nullable List<GraphQlListOfObject> graphQlListOfObjects() {
      return this.graphQlListOfObjects;
    }

    @Override
    public String toString() {
      return "Data{"
        + "graphQlString=" + graphQlString + ", "
        + "graphQlIdNullable=" + graphQlIdNullable + ", "
        + "graphQlIdNonNullable=" + graphQlIdNonNullable + ", "
        + "graphQlIntNullable=" + graphQlIntNullable + ", "
        + "graphQlIntNonNullable=" + graphQlIntNonNullable + ", "
        + "graphQlFloatNullable=" + graphQlFloatNullable + ", "
        + "graphQlFloatNonNullable=" + graphQlFloatNonNullable + ", "
        + "graphQlBooleanNullable=" + graphQlBooleanNullable + ", "
        + "graphQlBooleanNonNullable=" + graphQlBooleanNonNullable + ", "
        + "graphQlListOfInt=" + graphQlListOfInt + ", "
        + "graphQlListOfObjects=" + graphQlListOfObjects
        + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return ((this.graphQlString == null) ? (that.graphQlString == null) : this.graphQlString.equals(that.graphQlString))
         && ((this.graphQlIdNullable == null) ? (that.graphQlIdNullable == null) : this.graphQlIdNullable.equals(that.graphQlIdNullable))
         && ((this.graphQlIdNonNullable == null) ? (that.graphQlIdNonNullable == null) : this.graphQlIdNonNullable.equals(that.graphQlIdNonNullable))
         && ((this.graphQlIntNullable == null) ? (that.graphQlIntNullable == null) : this.graphQlIntNullable.equals(that.graphQlIntNullable))
         && this.graphQlIntNonNullable == that.graphQlIntNonNullable
         && ((this.graphQlFloatNullable == null) ? (that.graphQlFloatNullable == null) : this.graphQlFloatNullable.equals(that.graphQlFloatNullable))
         && Double.doubleToLongBits(this.graphQlFloatNonNullable) == Double.doubleToLongBits(that.graphQlFloatNonNullable)
         && ((this.graphQlBooleanNullable == null) ? (that.graphQlBooleanNullable == null) : this.graphQlBooleanNullable.equals(that.graphQlBooleanNullable))
         && this.graphQlBooleanNonNullable == that.graphQlBooleanNonNullable
         && ((this.graphQlListOfInt == null) ? (that.graphQlListOfInt == null) : this.graphQlListOfInt.equals(that.graphQlListOfInt))
         && ((this.graphQlListOfObjects == null) ? (that.graphQlListOfObjects == null) : this.graphQlListOfObjects.equals(that.graphQlListOfObjects));
      }
      return false;
    }

    @Override
    public int hashCode() {
      int h = 1;
      h *= 1000003;
      h ^= (graphQlString == null) ? 0 : graphQlString.hashCode();
      h *= 1000003;
      h ^= (graphQlIdNullable == null) ? 0 : graphQlIdNullable.hashCode();
      h *= 1000003;
      h ^= (graphQlIdNonNullable == null) ? 0 : graphQlIdNonNullable.hashCode();
      h *= 1000003;
      h ^= (graphQlIntNullable == null) ? 0 : graphQlIntNullable.hashCode();
      h *= 1000003;
      h ^= graphQlIntNonNullable;
      h *= 1000003;
      h ^= (graphQlFloatNullable == null) ? 0 : graphQlFloatNullable.hashCode();
      h *= 1000003;
      h ^= Double.valueOf(graphQlFloatNonNullable).hashCode();
      h *= 1000003;
      h ^= (graphQlBooleanNullable == null) ? 0 : graphQlBooleanNullable.hashCode();
      h *= 1000003;
      h ^= Boolean.valueOf(graphQlBooleanNonNullable).hashCode();
      h *= 1000003;
      h ^= (graphQlListOfInt == null) ? 0 : graphQlListOfInt.hashCode();
      h *= 1000003;
      h ^= (graphQlListOfObjects == null) ? 0 : graphQlListOfObjects.hashCode();
      return h;
    }

    public static class GraphQlListOfObject {
      private final int someField;

      public GraphQlListOfObject(int someField) {
        this.someField = someField;
      }

      public int someField() {
        return this.someField;
      }

      @Override
      public String toString() {
        return "GraphQlListOfObject{"
          + "someField=" + someField
          + "}";
      }

      @Override
      public boolean equals(Object o) {
        if (o == this) {
          return true;
        }
        if (o instanceof GraphQlListOfObject) {
          GraphQlListOfObject that = (GraphQlListOfObject) o;
          return this.someField == that.someField;
        }
        return false;
      }

      @Override
      public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= someField;
        return h;
      }

      public static final class Mapper implements ResponseFieldMapper<GraphQlListOfObject> {
        final Field[] fields = {
          Field.forInt("someField", "someField", null, false)
        };

        @Override
        public GraphQlListOfObject map(ResponseReader reader) throws IOException {
          final int someField = reader.read(fields[0]);
          return new GraphQlListOfObject(someField);
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final GraphQlListOfObject.Mapper graphQlListOfObjectFieldMapper = new GraphQlListOfObject.Mapper();

      final Field[] fields = {
        Field.forString("graphQlString", "graphQlString", null, true),
        Field.forString("graphQlIdNullable", "graphQlIdNullable", null, true),
        Field.forString("graphQlIdNonNullable", "graphQlIdNonNullable", null, false),
        Field.forInt("graphQlIntNullable", "graphQlIntNullable", null, true),
        Field.forInt("graphQlIntNonNullable", "graphQlIntNonNullable", null, false),
        Field.forDouble("graphQlFloatNullable", "graphQlFloatNullable", null, true),
        Field.forDouble("graphQlFloatNonNullable", "graphQlFloatNonNullable", null, false),
        Field.forBoolean("graphQlBooleanNullable", "graphQlBooleanNullable", null, true),
        Field.forBoolean("graphQlBooleanNonNullable", "graphQlBooleanNonNullable", null, false),
        Field.forList("graphQlListOfInt", "graphQlListOfInt", null, true, new Field.ListReader<Integer>() {
          @Override public Integer read(final Field.ListItemReader reader) throws IOException {
            return reader.readInt();
          }
        }),
        Field.forList("graphQlListOfObjects", "graphQlListOfObjects", null, true, new Field.ObjectReader<GraphQlListOfObject>() {
          @Override public GraphQlListOfObject read(final ResponseReader reader) throws IOException {
            return graphQlListOfObjectFieldMapper.map(reader);
          }
        })
      };

      @Override
      public Data map(ResponseReader reader) throws IOException {
        final String graphQlString = reader.read(fields[0]);
        final String graphQlIdNullable = reader.read(fields[1]);
        final String graphQlIdNonNullable = reader.read(fields[2]);
        final Integer graphQlIntNullable = reader.read(fields[3]);
        final int graphQlIntNonNullable = reader.read(fields[4]);
        final Double graphQlFloatNullable = reader.read(fields[5]);
        final double graphQlFloatNonNullable = reader.read(fields[6]);
        final Boolean graphQlBooleanNullable = reader.read(fields[7]);
        final boolean graphQlBooleanNonNullable = reader.read(fields[8]);
        final List<Integer> graphQlListOfInt = reader.read(fields[9]);
        final List<GraphQlListOfObject> graphQlListOfObjects = reader.read(fields[10]);
        return new Data(graphQlString, graphQlIdNullable, graphQlIdNonNullable, graphQlIntNullable, graphQlIntNonNullable, graphQlFloatNullable, graphQlFloatNonNullable, graphQlBooleanNullable, graphQlBooleanNonNullable, graphQlListOfInt, graphQlListOfObjects);
      }
    }
  }
}
