// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.reserved_keywords;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ScalarTypeAdapters;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.QueryDocumentMinifier;
import com.apollographql.apollo.api.internal.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller;
import com.apollographql.apollo.api.internal.ResponseReader;
import com.apollographql.apollo.api.internal.ResponseWriter;
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import com.example.reserved_keywords.type.CustomType;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TestQuery implements Query<TestQuery.Data, Optional<TestQuery.Data>, Operation.Variables> {
  public static final String OPERATION_ID = "47d23fa9d7e9bf697a19f43297b1c422ae31ce1886f740e3982a5daf9b7e1ebd";

  public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify(
    "query TestQuery {\n"
        + "  yield: hero {\n"
        + "    __typename\n"
        + "    it: id\n"
        + "    name\n"
        + "  }\n"
        + "  objects: search(text: \"abc\") {\n"
        + "    __typename\n"
        + "    ... on Character {\n"
        + "      name\n"
        + "    }\n"
        + "  }\n"
        + "}"
  );

  public static final OperationName OPERATION_NAME = new OperationName() {
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

  @Override
  @NotNull
  public Response<Optional<TestQuery.Data>> parse(@NotNull final BufferedSource source,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<Optional<TestQuery.Data>> parse(@NotNull final BufferedSource source) throws
      IOException {
    return parse(source, ScalarTypeAdapters.DEFAULT);
  }

  public static final class Builder {
    Builder() {
    }

    public TestQuery build() {
      return new TestQuery();
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("yield", "hero", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forList("objects", "search", new UnmodifiableMapBuilder<String, java.lang.Object>(1)
      .put("text", "abc")
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final Optional<Yield> yield;

    final Optional<List<Object>> objects;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Data(@Nullable Yield yield, @Nullable List<Object> objects) {
      this.yield = Optional.fromNullable(yield);
      this.objects = Optional.fromNullable(objects);
    }

    public Optional<Yield> yield() {
      return this.yield;
    }

    public Optional<List<Object>> objects() {
      return this.objects;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], yield.isPresent() ? yield.get().marshaller() : null);
          writer.writeList($responseFields[1], objects.isPresent() ? objects.get() : null, new ResponseWriter.ListWriter() {
            @Override
            public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
              for (java.lang.Object item : items) {
                listItemWriter.writeObject(((Object) item).marshaller());
              }
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "yield=" + yield + ", "
          + "objects=" + objects
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(java.lang.Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return this.yield.equals(that.yield)
         && this.objects.equals(that.objects);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= yield.hashCode();
        h *= 1000003;
        h ^= objects.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Yield.Mapper yieldFieldMapper = new Yield.Mapper();

      final Object.Mapper objectFieldMapper = new Object.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final Yield yield = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<Yield>() {
          @Override
          public Yield read(ResponseReader reader) {
            return yieldFieldMapper.map(reader);
          }
        });
        final List<Object> objects = reader.readList($responseFields[1], new ResponseReader.ListReader<Object>() {
          @Override
          public Object read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Object>() {
              @Override
              public Object read(ResponseReader reader) {
                return objectFieldMapper.map(reader);
              }
            });
          }
        });
        return new Data(yield, objects);
      }
    }
  }

  public static class Yield {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forCustomType("it", "id", null, false, CustomType.ID, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String it;

    final @NotNull String name;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Yield(@NotNull String __typename, @NotNull String it, @NotNull String name) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.it = Utils.checkNotNull(it, "it == null");
      this.name = Utils.checkNotNull(name, "name == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The ID of the character
     */
    public @NotNull String it() {
      return this.it;
    }

    /**
     * The name of the character
     */
    public @NotNull String name() {
      return this.name;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeCustom((ResponseField.CustomTypeField) $responseFields[1], it);
          writer.writeString($responseFields[2], name);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Yield{"
          + "__typename=" + __typename + ", "
          + "it=" + it + ", "
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(java.lang.Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Yield) {
        Yield that = (Yield) o;
        return this.__typename.equals(that.__typename)
         && this.it.equals(that.it)
         && this.name.equals(that.name);
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
        h ^= it.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Yield> {
      @Override
      public Yield map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String it = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[1]);
        final String name = reader.readString($responseFields[2]);
        return new Yield(__typename, it, name);
      }
    }
  }

  public interface Object {
    @NotNull String __typename();

    ResponseFieldMarshaller marshaller();

    default <T> T visit(Visitor<T> visitor) {
      if (this instanceof AsCharacter) {
        return visitor.visit((AsCharacter) this);
      } else if (this instanceof AsSearchResult) {
        return visitor.visit((AsSearchResult) this);
      }
      return visitor.visitDefault(this);
    }

    final class Mapper implements ResponseFieldMapper<Object> {
      static final ResponseField[] $responseFields = {
        ResponseField.forFragment("__typename", "__typename", Arrays.<ResponseField.Condition>asList(
          ResponseField.Condition.typeCondition(new String[] {"Human", "Droid"})
        ))
      };

      final AsCharacter.Mapper asCharacterFieldMapper = new AsCharacter.Mapper();

      final AsSearchResult.Mapper asSearchResultFieldMapper = new AsSearchResult.Mapper();

      @Override
      public Object map(ResponseReader reader) {
        final AsCharacter asCharacter = reader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsCharacter>() {
          @Override
          public AsCharacter read(ResponseReader reader) {
            return asCharacterFieldMapper.map(reader);
          }
        });
        if (asCharacter != null) {
          return asCharacter;
        }
        return asSearchResultFieldMapper.map(reader);
      }
    }

    interface Visitor<T> {
      T visitDefault(@NotNull Object object);

      T visit(@NotNull AsCharacter asCharacter);

      T visit(@NotNull AsSearchResult asSearchResult);
    }
  }

  public static class AsCharacter implements Object {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String name;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AsCharacter(@NotNull String __typename, @NotNull String name) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.name = Utils.checkNotNull(name, "name == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The name of the character
     */
    public @NotNull String name() {
      return this.name;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], name);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsCharacter{"
          + "__typename=" + __typename + ", "
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(java.lang.Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsCharacter) {
        AsCharacter that = (AsCharacter) o;
        return this.__typename.equals(that.__typename)
         && this.name.equals(that.name);
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
        h ^= name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsCharacter> {
      @Override
      public AsCharacter map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String name = reader.readString($responseFields[1]);
        return new AsCharacter(__typename, name);
      }
    }
  }

  public static class AsSearchResult implements Object {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AsSearchResult(@NotNull String __typename) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsSearchResult{"
          + "__typename=" + __typename
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(java.lang.Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsSearchResult) {
        AsSearchResult that = (AsSearchResult) o;
        return this.__typename.equals(that.__typename);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsSearchResult> {
      @Override
      public AsSearchResult map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        return new AsSearchResult(__typename);
      }
    }
  }
}
