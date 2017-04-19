package com.example.fragment_with_inline_fragment.fragment;

import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.internal.Optional;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public class HeroDetails {
  public static final String FRAGMENT_DEFINITION = "fragment HeroDetails on Character {\n"
      + "  __typename\n"
      + "  name\n"
      + "  friendsConnection {\n"
      + "    __typename\n"
      + "    totalCount\n"
      + "    edges {\n"
      + "      __typename\n"
      + "      node {\n"
      + "        __typename\n"
      + "        name\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "  ... on Droid {\n"
      + "    __typename\n"
      + "    name\n"
      + "    primaryFunction\n"
      + "  }\n"
      + "}";

  public static final List<String> POSSIBLE_TYPES = Collections.unmodifiableList(Arrays.asList( "Human", "Droid"));

  private final @Nonnull String name;

  private final @Nonnull FriendsConnection friendsConnection;

  private final Optional<AsDroid> asDroid;

  private volatile String $toString;

  private volatile int $hashCode;

  private volatile boolean $hashCodeMemoized;

  public HeroDetails(@Nonnull String name, @Nonnull FriendsConnection friendsConnection,
      @Nullable AsDroid asDroid) {
    this.name = name;
    this.friendsConnection = friendsConnection;
    this.asDroid = Optional.fromNullable(asDroid);
  }

  /**
   * The name of the character
   */
  public @Nonnull String name() {
    return this.name;
  }

  /**
   * The friends of the character exposed as a connection with edges
   */
  public @Nonnull FriendsConnection friendsConnection() {
    return this.friendsConnection;
  }

  public Optional<AsDroid> asDroid() {
    return this.asDroid;
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = "HeroDetails{"
        + "name=" + name + ", "
        + "friendsConnection=" + friendsConnection + ", "
        + "asDroid=" + asDroid
        + "}";
    }
    return $toString;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof HeroDetails) {
      HeroDetails that = (HeroDetails) o;
      return ((this.name == null) ? (that.name == null) : this.name.equals(that.name))
       && ((this.friendsConnection == null) ? (that.friendsConnection == null) : this.friendsConnection.equals(that.friendsConnection))
       && ((this.asDroid == null) ? (that.asDroid == null) : this.asDroid.equals(that.asDroid));
    }
    return false;
  }

  @Override
  public int hashCode() {
    if (!$hashCodeMemoized) {
      int h = 1;
      h *= 1000003;
      h ^= (name == null) ? 0 : name.hashCode();
      h *= 1000003;
      h ^= (friendsConnection == null) ? 0 : friendsConnection.hashCode();
      h *= 1000003;
      h ^= (asDroid == null) ? 0 : asDroid.hashCode();
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  public static final class Mapper implements ResponseFieldMapper<HeroDetails> {
    final FriendsConnection.Mapper friendsConnectionFieldMapper = new FriendsConnection.Mapper();

    final AsDroid.Mapper asDroidFieldMapper = new AsDroid.Mapper();

    final Field[] fields = {
      Field.forString("name", "name", null, false),
      Field.forObject("friendsConnection", "friendsConnection", null, false, new Field.ObjectReader<FriendsConnection>() {
        @Override public FriendsConnection read(final ResponseReader reader) throws IOException {
          return friendsConnectionFieldMapper.map(reader);
        }
      }),
      Field.forConditionalType("__typename", "__typename", new Field.ConditionalTypeReader<AsDroid>() {
        @Override
        public AsDroid read(String conditionalType, ResponseReader reader) throws IOException {
          if (conditionalType.equals("Droid")) {
            return asDroidFieldMapper.map(reader);
          } else {
            return null;
          }
        }
      })
    };

    @Override
    public HeroDetails map(ResponseReader reader) throws IOException {
      final String name = reader.read(fields[0]);
      final FriendsConnection friendsConnection = reader.read(fields[1]);
      final AsDroid asDroid = reader.read(fields[2]);
      return new HeroDetails(name, friendsConnection, asDroid);
    }
  }

  public static class Node {
    private final @Nonnull String name;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Node(@Nonnull String name) {
      this.name = name;
    }

    /**
     * The name of the character
     */
    public @Nonnull String name() {
      return this.name;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Node{"
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Node) {
        Node that = (Node) o;
        return ((this.name == null) ? (that.name == null) : this.name.equals(that.name));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (name == null) ? 0 : name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Node> {
      final Field[] fields = {
        Field.forString("name", "name", null, false)
      };

      @Override
      public Node map(ResponseReader reader) throws IOException {
        final String name = reader.read(fields[0]);
        return new Node(name);
      }
    }
  }

  public static class Edge {
    private final Optional<Node> node;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Edge(@Nullable Node node) {
      this.node = Optional.fromNullable(node);
    }

    /**
     * The character represented by this friendship edge
     */
    public Optional<Node> node() {
      return this.node;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Edge{"
          + "node=" + node
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Edge) {
        Edge that = (Edge) o;
        return ((this.node == null) ? (that.node == null) : this.node.equals(that.node));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (node == null) ? 0 : node.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Edge> {
      final Node.Mapper nodeFieldMapper = new Node.Mapper();

      final Field[] fields = {
        Field.forObject("node", "node", null, true, new Field.ObjectReader<Node>() {
          @Override public Node read(final ResponseReader reader) throws IOException {
            return nodeFieldMapper.map(reader);
          }
        })
      };

      @Override
      public Edge map(ResponseReader reader) throws IOException {
        final Node node = reader.read(fields[0]);
        return new Edge(node);
      }
    }
  }

  public static class FriendsConnection {
    private final Optional<Integer> totalCount;

    private final Optional<List<Edge>> edges;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public FriendsConnection(@Nullable Integer totalCount, @Nullable List<Edge> edges) {
      this.totalCount = Optional.fromNullable(totalCount);
      this.edges = Optional.fromNullable(edges);
    }

    /**
     * The total number of friends
     */
    public Optional<Integer> totalCount() {
      return this.totalCount;
    }

    /**
     * The edges for each of the character's friends.
     */
    public Optional<List<Edge>> edges() {
      return this.edges;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "FriendsConnection{"
          + "totalCount=" + totalCount + ", "
          + "edges=" + edges
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
        return ((this.totalCount == null) ? (that.totalCount == null) : this.totalCount.equals(that.totalCount))
         && ((this.edges == null) ? (that.edges == null) : this.edges.equals(that.edges));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (totalCount == null) ? 0 : totalCount.hashCode();
        h *= 1000003;
        h ^= (edges == null) ? 0 : edges.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<FriendsConnection> {
      final Edge.Mapper edgeFieldMapper = new Edge.Mapper();

      final Field[] fields = {
        Field.forInt("totalCount", "totalCount", null, true),
        Field.forList("edges", "edges", null, true, new Field.ObjectReader<Edge>() {
          @Override public Edge read(final ResponseReader reader) throws IOException {
            return edgeFieldMapper.map(reader);
          }
        })
      };

      @Override
      public FriendsConnection map(ResponseReader reader) throws IOException {
        final Integer totalCount = reader.read(fields[0]);
        final List<Edge> edges = reader.read(fields[1]);
        return new FriendsConnection(totalCount, edges);
      }
    }
  }

  public static class Node1 {
    private final @Nonnull String name;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Node1(@Nonnull String name) {
      this.name = name;
    }

    /**
     * The name of the character
     */
    public @Nonnull String name() {
      return this.name;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Node1{"
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Node1) {
        Node1 that = (Node1) o;
        return ((this.name == null) ? (that.name == null) : this.name.equals(that.name));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (name == null) ? 0 : name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Node1> {
      final Field[] fields = {
        Field.forString("name", "name", null, false)
      };

      @Override
      public Node1 map(ResponseReader reader) throws IOException {
        final String name = reader.read(fields[0]);
        return new Node1(name);
      }
    }
  }

  public static class Edge1 {
    private final Optional<Node1> node;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Edge1(@Nullable Node1 node) {
      this.node = Optional.fromNullable(node);
    }

    /**
     * The character represented by this friendship edge
     */
    public Optional<Node1> node() {
      return this.node;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Edge1{"
          + "node=" + node
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Edge1) {
        Edge1 that = (Edge1) o;
        return ((this.node == null) ? (that.node == null) : this.node.equals(that.node));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (node == null) ? 0 : node.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Edge1> {
      final Node1.Mapper node1FieldMapper = new Node1.Mapper();

      final Field[] fields = {
        Field.forObject("node", "node", null, true, new Field.ObjectReader<Node1>() {
          @Override public Node1 read(final ResponseReader reader) throws IOException {
            return node1FieldMapper.map(reader);
          }
        })
      };

      @Override
      public Edge1 map(ResponseReader reader) throws IOException {
        final Node1 node = reader.read(fields[0]);
        return new Edge1(node);
      }
    }
  }

  public static class FriendsConnection1 {
    private final Optional<Integer> totalCount;

    private final Optional<List<Edge1>> edges;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public FriendsConnection1(@Nullable Integer totalCount, @Nullable List<Edge1> edges) {
      this.totalCount = Optional.fromNullable(totalCount);
      this.edges = Optional.fromNullable(edges);
    }

    /**
     * The total number of friends
     */
    public Optional<Integer> totalCount() {
      return this.totalCount;
    }

    /**
     * The edges for each of the character's friends.
     */
    public Optional<List<Edge1>> edges() {
      return this.edges;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "FriendsConnection1{"
          + "totalCount=" + totalCount + ", "
          + "edges=" + edges
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof FriendsConnection1) {
        FriendsConnection1 that = (FriendsConnection1) o;
        return ((this.totalCount == null) ? (that.totalCount == null) : this.totalCount.equals(that.totalCount))
         && ((this.edges == null) ? (that.edges == null) : this.edges.equals(that.edges));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (totalCount == null) ? 0 : totalCount.hashCode();
        h *= 1000003;
        h ^= (edges == null) ? 0 : edges.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<FriendsConnection1> {
      final Edge1.Mapper edge1FieldMapper = new Edge1.Mapper();

      final Field[] fields = {
        Field.forInt("totalCount", "totalCount", null, true),
        Field.forList("edges", "edges", null, true, new Field.ObjectReader<Edge1>() {
          @Override public Edge1 read(final ResponseReader reader) throws IOException {
            return edge1FieldMapper.map(reader);
          }
        })
      };

      @Override
      public FriendsConnection1 map(ResponseReader reader) throws IOException {
        final Integer totalCount = reader.read(fields[0]);
        final List<Edge1> edges = reader.read(fields[1]);
        return new FriendsConnection1(totalCount, edges);
      }
    }
  }

  public static class AsDroid {
    private final @Nonnull String name;

    private final @Nonnull FriendsConnection1 friendsConnection;

    private final Optional<String> primaryFunction;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public AsDroid(@Nonnull String name, @Nonnull FriendsConnection1 friendsConnection,
        @Nullable String primaryFunction) {
      this.name = name;
      this.friendsConnection = friendsConnection;
      this.primaryFunction = Optional.fromNullable(primaryFunction);
    }

    /**
     * What others call this droid
     */
    public @Nonnull String name() {
      return this.name;
    }

    /**
     * The friends of the droid exposed as a connection with edges
     */
    public @Nonnull FriendsConnection1 friendsConnection() {
      return this.friendsConnection;
    }

    /**
     * This droid's primary function
     */
    public Optional<String> primaryFunction() {
      return this.primaryFunction;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsDroid{"
          + "name=" + name + ", "
          + "friendsConnection=" + friendsConnection + ", "
          + "primaryFunction=" + primaryFunction
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsDroid) {
        AsDroid that = (AsDroid) o;
        return ((this.name == null) ? (that.name == null) : this.name.equals(that.name))
         && ((this.friendsConnection == null) ? (that.friendsConnection == null) : this.friendsConnection.equals(that.friendsConnection))
         && ((this.primaryFunction == null) ? (that.primaryFunction == null) : this.primaryFunction.equals(that.primaryFunction));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (name == null) ? 0 : name.hashCode();
        h *= 1000003;
        h ^= (friendsConnection == null) ? 0 : friendsConnection.hashCode();
        h *= 1000003;
        h ^= (primaryFunction == null) ? 0 : primaryFunction.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsDroid> {
      final FriendsConnection1.Mapper friendsConnection1FieldMapper = new FriendsConnection1.Mapper();

      final Field[] fields = {
        Field.forString("name", "name", null, false),
        Field.forObject("friendsConnection", "friendsConnection", null, false, new Field.ObjectReader<FriendsConnection1>() {
          @Override public FriendsConnection1 read(final ResponseReader reader) throws IOException {
            return friendsConnection1FieldMapper.map(reader);
          }
        }),
        Field.forString("primaryFunction", "primaryFunction", null, true)
      };

      @Override
      public AsDroid map(ResponseReader reader) throws IOException {
        final String name = reader.read(fields[0]);
        final FriendsConnection1 friendsConnection = reader.read(fields[1]);
        final String primaryFunction = reader.read(fields[2]);
        return new AsDroid(name, friendsConnection, primaryFunction);
      }
    }
  }
}
