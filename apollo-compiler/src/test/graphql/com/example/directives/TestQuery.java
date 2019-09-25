// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.directives;

import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Optional;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public final class TestQuery implements Query<TestQuery.Data, Optional<TestQuery.Data>, TestQuery.Variables> {
  public static final String OPERATION_ID = "891f457ef884c9dba5cf3ace02d1ebad79bd22ddf56e57191015ec05bbca944c";

  public static final String QUERY_DOCUMENT = "query TestQuery($includeName: Boolean!, $skipFriends: Boolean!) {\n"
      + "  hero {\n"
      + "    name @include(if: $includeName)\n"
      + "    friendsConnection @skip(if: $skipFriends) {\n"
      + "      totalCount\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "TestQuery";
    }
  };

  private final TestQuery.Variables variables;

  public TestQuery(boolean includeName, boolean skipFriends) {
    variables = new TestQuery.Variables(includeName, skipFriends);
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

  public static final class Builder {
    private boolean includeName;

    private boolean skipFriends;

    Builder() {
    }

    public Builder includeName(boolean includeName) {
      this.includeName = includeName;
      return this;
    }

    public Builder skipFriends(boolean skipFriends) {
      this.skipFriends = skipFriends;
      return this;
    }

    public TestQuery build() {
      return new TestQuery(includeName, skipFriends);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final boolean includeName;

    private final boolean skipFriends;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(boolean includeName, boolean skipFriends) {
      this.includeName = includeName;
      this.skipFriends = skipFriends;
      this.valueMap.put("includeName", includeName);
      this.valueMap.put("skipFriends", skipFriends);
    }

    public boolean includeName() {
      return includeName;
    }

    public boolean skipFriends() {
      return skipFriends;
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
          writer.writeBoolean("includeName", includeName);
          writer.writeBoolean("skipFriends", skipFriends);
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("hero", "hero", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final Optional<Hero> hero;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Data(@Nullable Hero hero) {
      this.hero = Optional.fromNullable(hero);
    }

    public Optional<Hero> hero() {
      return this.hero;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], hero.isPresent() ? hero.get().marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "hero=" + hero
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
        return this.hero.equals(that.hero);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= hero.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Hero.Mapper heroFieldMapper = new Hero.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final Hero hero = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<Hero>() {
          @Override
          public Hero read(ResponseReader reader) {
            return heroFieldMapper.map(reader);
          }
        });
        return new Data(hero);
      }
    }
  }

  public static class Hero {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("name", "name", null, true, Arrays.<ResponseField.Condition>asList(ResponseField.Condition.booleanCondition("includeName", false))),
      ResponseField.forObject("friendsConnection", "friendsConnection", null, true, Arrays.<ResponseField.Condition>asList(ResponseField.Condition.booleanCondition("skipFriends", true)))
    };

    final Optional<String> name;

    final Optional<FriendsConnection> friendsConnection;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Hero(@Nullable String name, @Nullable FriendsConnection friendsConnection) {
      this.name = Optional.fromNullable(name);
      this.friendsConnection = Optional.fromNullable(friendsConnection);
    }

    /**
     * The name of the character
     */
    public Optional<String> name() {
      return this.name;
    }

    /**
     * The friends of the character exposed as a connection with edges
     */
    public Optional<FriendsConnection> friendsConnection() {
      return this.friendsConnection;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], name.isPresent() ? name.get() : null);
          writer.writeObject($responseFields[1], friendsConnection.isPresent() ? friendsConnection.get().marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Hero{"
          + "name=" + name + ", "
          + "friendsConnection=" + friendsConnection
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Hero) {
        Hero that = (Hero) o;
        return this.name.equals(that.name)
         && this.friendsConnection.equals(that.friendsConnection);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= name.hashCode();
        h *= 1000003;
        h ^= friendsConnection.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Hero> {
      final FriendsConnection.Mapper friendsConnectionFieldMapper = new FriendsConnection.Mapper();

      @Override
      public Hero map(ResponseReader reader) {
        final String name = reader.readString($responseFields[0]);
        final FriendsConnection friendsConnection = reader.readObject($responseFields[1], new ResponseReader.ObjectReader<FriendsConnection>() {
          @Override
          public FriendsConnection read(ResponseReader reader) {
            return friendsConnectionFieldMapper.map(reader);
          }
        });
        return new Hero(name, friendsConnection);
      }
    }
  }

  public static class FriendsConnection {
    static final ResponseField[] $responseFields = {
      ResponseField.forInt("totalCount", "totalCount", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final Optional<Integer> totalCount;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public FriendsConnection(@Nullable Integer totalCount) {
      this.totalCount = Optional.fromNullable(totalCount);
    }

    /**
     * The total number of friends
     */
    public Optional<Integer> totalCount() {
      return this.totalCount;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeInt($responseFields[0], totalCount.isPresent() ? totalCount.get() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "FriendsConnection{"
          + "totalCount=" + totalCount
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof FriendsConnection) {
        FriendsConnection that = (FriendsConnection) o;
        return this.totalCount.equals(that.totalCount);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= totalCount.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<FriendsConnection> {
      @Override
      public FriendsConnection map(ResponseReader reader) {
        final Integer totalCount = reader.readInt($responseFields[0]);
        return new FriendsConnection(totalCount);
      }
    }
  }
}
