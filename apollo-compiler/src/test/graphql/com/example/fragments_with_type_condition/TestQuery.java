package com.example.fragments_with_type_condition;

import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.FragmentResponseFieldMapper;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.internal.Optional;
import com.example.fragments_with_type_condition.fragment.DroidDetails;
import com.example.fragments_with_type_condition.fragment.HumanDetails;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public final class TestQuery implements Query<TestQuery.Data, Optional<TestQuery.Data>, Operation.Variables> {
  public static final String OPERATION_DEFINITION = "query TestQuery {\n"
      + "  r2: hero {\n"
      + "    __typename\n"
      + "    ...HumanDetails\n"
      + "    ...DroidDetails\n"
      + "  }\n"
      + "  luke: hero {\n"
      + "    __typename\n"
      + "    ...HumanDetails\n"
      + "    ...DroidDetails\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION + "\n"
   + HumanDetails.FRAGMENT_DEFINITION + "\n"
   + DroidDetails.FRAGMENT_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "TestQuery";
    }
  };

  private final Operation.Variables variables;

  public TestQuery() {
    this.variables = Operation.EMPTY_VARIABLES;
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
  public Operation.Variables variables() {
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

  public static final class Builder {
    Builder() {
    }

    public TestQuery build() {
      return new TestQuery();
    }
  }

  public static class Data implements Operation.Data {
    private final Optional<R2> r2;

    private final Optional<Luke> luke;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nullable R2 r2, @Nullable Luke luke) {
      this.r2 = Optional.fromNullable(r2);
      this.luke = Optional.fromNullable(luke);
    }

    public Optional<R2> r2() {
      return this.r2;
    }

    public Optional<Luke> luke() {
      return this.luke;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "r2=" + r2 + ", "
          + "luke=" + luke
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
        return this.r2.equals(that.r2)
         && this.luke.equals(that.luke);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= r2.hashCode();
        h *= 1000003;
        h ^= luke.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final R2.Mapper r2FieldMapper = new R2.Mapper();

      final Luke.Mapper lukeFieldMapper = new Luke.Mapper();

      final Field[] fields = {
        Field.forObject("r2", "hero", null, true, new Field.ObjectReader<R2>() {
          @Override public R2 read(final ResponseReader reader) throws IOException {
            return r2FieldMapper.map(reader);
          }
        }),
        Field.forObject("luke", "hero", null, true, new Field.ObjectReader<Luke>() {
          @Override public Luke read(final ResponseReader reader) throws IOException {
            return lukeFieldMapper.map(reader);
          }
        })
      };

      @Override
      public Data map(ResponseReader reader) throws IOException {
        final R2 r2 = reader.read(fields[0]);
        final Luke luke = reader.read(fields[1]);
        return new Data(r2, luke);
      }
    }
  }

  public static class R2 {
    private final @Nonnull String __typename;

    private final @Nonnull Fragments fragments;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public R2(@Nonnull String __typename, @Nonnull Fragments fragments) {
      this.__typename = __typename;
      this.fragments = fragments;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nonnull Fragments fragments() {
      return this.fragments;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "R2{"
          + "__typename=" + __typename + ", "
          + "fragments=" + fragments
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof R2) {
        R2 that = (R2) o;
        return this.__typename.equals(that.__typename)
         && this.fragments.equals(that.fragments);
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
        h ^= fragments.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static class Fragments {
      private final @Nonnull HumanDetails humanDetails;

      private final @Nonnull DroidDetails droidDetails;

      private volatile String $toString;

      private volatile int $hashCode;

      private volatile boolean $hashCodeMemoized;

      public Fragments(@Nonnull HumanDetails humanDetails, @Nonnull DroidDetails droidDetails) {
        this.humanDetails = humanDetails;
        this.droidDetails = droidDetails;
      }

      public @Nonnull HumanDetails humanDetails() {
        return this.humanDetails;
      }

      public @Nonnull DroidDetails droidDetails() {
        return this.droidDetails;
      }

      @Override
      public String toString() {
        if ($toString == null) {
          $toString = "Fragments{"
            + "humanDetails=" + humanDetails + ", "
            + "droidDetails=" + droidDetails
            + "}";
        }
        return $toString;
      }

      @Override
      public boolean equals(Object o) {
        if (o == this) {
          return true;
        }
        if (o instanceof Fragments) {
          Fragments that = (Fragments) o;
          return this.humanDetails.equals(that.humanDetails)
           && this.droidDetails.equals(that.droidDetails);
        }
        return false;
      }

      @Override
      public int hashCode() {
        if (!$hashCodeMemoized) {
          int h = 1;
          h *= 1000003;
          h ^= humanDetails.hashCode();
          h *= 1000003;
          h ^= droidDetails.hashCode();
          $hashCode = h;
          $hashCodeMemoized = true;
        }
        return $hashCode;
      }

      public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
        final HumanDetails.Mapper humanDetailsFieldMapper = new HumanDetails.Mapper();

        final DroidDetails.Mapper droidDetailsFieldMapper = new DroidDetails.Mapper();

        @Override
        public @Nonnull Fragments map(ResponseReader reader, @Nonnull String conditionalType) throws
            IOException {
          HumanDetails humanDetails = null;
          DroidDetails droidDetails = null;
          if (HumanDetails.POSSIBLE_TYPES.contains(conditionalType)) {
            humanDetails = humanDetailsFieldMapper.map(reader);
          }
          if (DroidDetails.POSSIBLE_TYPES.contains(conditionalType)) {
            droidDetails = droidDetailsFieldMapper.map(reader);
          }
          return new Fragments(humanDetails, droidDetails);
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<R2> {
      final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

      final Field[] fields = {
        Field.forString("__typename", "__typename", null, false),
        Field.forConditionalType("__typename", "__typename", new Field.ConditionalTypeReader<Fragments>() {
          @Override
          public Fragments read(String conditionalType, ResponseReader reader) throws IOException {
            return fragmentsFieldMapper.map(reader, conditionalType);
          }
        })
      };

      @Override
      public R2 map(ResponseReader reader) throws IOException {
        final String __typename = reader.read(fields[0]);
        final Fragments fragments = reader.read(fields[1]);
        return new R2(__typename, fragments);
      }
    }
  }

  public static class Luke {
    private final @Nonnull String __typename;

    private final @Nonnull Fragments fragments;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Luke(@Nonnull String __typename, @Nonnull Fragments fragments) {
      this.__typename = __typename;
      this.fragments = fragments;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nonnull Fragments fragments() {
      return this.fragments;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Luke{"
          + "__typename=" + __typename + ", "
          + "fragments=" + fragments
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Luke) {
        Luke that = (Luke) o;
        return this.__typename.equals(that.__typename)
         && this.fragments.equals(that.fragments);
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
        h ^= fragments.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static class Fragments {
      private final @Nonnull HumanDetails humanDetails;

      private final @Nonnull DroidDetails droidDetails;

      private volatile String $toString;

      private volatile int $hashCode;

      private volatile boolean $hashCodeMemoized;

      public Fragments(@Nonnull HumanDetails humanDetails, @Nonnull DroidDetails droidDetails) {
        this.humanDetails = humanDetails;
        this.droidDetails = droidDetails;
      }

      public @Nonnull HumanDetails humanDetails() {
        return this.humanDetails;
      }

      public @Nonnull DroidDetails droidDetails() {
        return this.droidDetails;
      }

      @Override
      public String toString() {
        if ($toString == null) {
          $toString = "Fragments{"
            + "humanDetails=" + humanDetails + ", "
            + "droidDetails=" + droidDetails
            + "}";
        }
        return $toString;
      }

      @Override
      public boolean equals(Object o) {
        if (o == this) {
          return true;
        }
        if (o instanceof Fragments) {
          Fragments that = (Fragments) o;
          return this.humanDetails.equals(that.humanDetails)
           && this.droidDetails.equals(that.droidDetails);
        }
        return false;
      }

      @Override
      public int hashCode() {
        if (!$hashCodeMemoized) {
          int h = 1;
          h *= 1000003;
          h ^= humanDetails.hashCode();
          h *= 1000003;
          h ^= droidDetails.hashCode();
          $hashCode = h;
          $hashCodeMemoized = true;
        }
        return $hashCode;
      }

      public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
        final HumanDetails.Mapper humanDetailsFieldMapper = new HumanDetails.Mapper();

        final DroidDetails.Mapper droidDetailsFieldMapper = new DroidDetails.Mapper();

        @Override
        public @Nonnull Fragments map(ResponseReader reader, @Nonnull String conditionalType) throws
            IOException {
          HumanDetails humanDetails = null;
          DroidDetails droidDetails = null;
          if (HumanDetails.POSSIBLE_TYPES.contains(conditionalType)) {
            humanDetails = humanDetailsFieldMapper.map(reader);
          }
          if (DroidDetails.POSSIBLE_TYPES.contains(conditionalType)) {
            droidDetails = droidDetailsFieldMapper.map(reader);
          }
          return new Fragments(humanDetails, droidDetails);
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<Luke> {
      final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

      final Field[] fields = {
        Field.forString("__typename", "__typename", null, false),
        Field.forConditionalType("__typename", "__typename", new Field.ConditionalTypeReader<Fragments>() {
          @Override
          public Fragments read(String conditionalType, ResponseReader reader) throws IOException {
            return fragmentsFieldMapper.map(reader, conditionalType);
          }
        })
      };

      @Override
      public Luke map(ResponseReader reader) throws IOException {
        final String __typename = reader.read(fields[0]);
        final Fragments fragments = reader.read(fields[1]);
        return new Luke(__typename, fragments);
      }
    }
  }
}
