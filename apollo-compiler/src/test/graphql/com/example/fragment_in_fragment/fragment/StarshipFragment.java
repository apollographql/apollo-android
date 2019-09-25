// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_in_fragment.fragment;

import com.apollographql.apollo.api.FragmentResponseFieldMapper;
import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;
import com.example.fragment_in_fragment.type.CustomType;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StarshipFragment implements GraphqlFragment {
  static final ResponseField[] $responseFields = {
    ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forString("name", "name", null, true, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forObject("pilotConnection", "pilotConnection", null, true, Collections.<ResponseField.Condition>emptyList())
  };

  public static final String FRAGMENT_DEFINITION = "fragment starshipFragment on Starship {\n"
      + "  id\n"
      + "  name\n"
      + "  pilotConnection {\n"
      + "    edges {\n"
      + "      node {\n"
      + "        __typename\n"
      + "        ...pilotFragment\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final List<String> POSSIBLE_TYPES = Collections.unmodifiableList(Arrays.asList( "Starship"));

  final @NotNull String id;

  final Optional<String> name;

  final Optional<PilotConnection> pilotConnection;

  private transient volatile String $toString;

  private transient volatile int $hashCode;

  private transient volatile boolean $hashCodeMemoized;

  public StarshipFragment(@NotNull String id, @Nullable String name,
      @Nullable PilotConnection pilotConnection) {
    this.id = Utils.checkNotNull(id, "id == null");
    this.name = Optional.fromNullable(name);
    this.pilotConnection = Optional.fromNullable(pilotConnection);
  }

  /**
   * The ID of an object
   */
  public @NotNull String id() {
    return this.id;
  }

  /**
   * The name of this starship. The common name, such as "Death Star".
   */
  public Optional<String> name() {
    return this.name;
  }

  public Optional<PilotConnection> pilotConnection() {
    return this.pilotConnection;
  }

  @SuppressWarnings("unchecked")
  public ResponseFieldMarshaller marshaller() {
    return new ResponseFieldMarshaller() {
      @Override
      public void marshal(ResponseWriter writer) {
        writer.writeCustom((ResponseField.CustomTypeField) $responseFields[0], id);
        writer.writeString($responseFields[1], name.isPresent() ? name.get() : null);
        writer.writeObject($responseFields[2], pilotConnection.isPresent() ? pilotConnection.get().marshaller() : null);
      }
    };
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = "StarshipFragment{"
        + "id=" + id + ", "
        + "name=" + name + ", "
        + "pilotConnection=" + pilotConnection
        + "}";
    }
    return $toString;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof StarshipFragment) {
      StarshipFragment that = (StarshipFragment) o;
      return this.id.equals(that.id)
       && this.name.equals(that.name)
       && this.pilotConnection.equals(that.pilotConnection);
    }
    return false;
  }

  @Override
  public int hashCode() {
    if (!$hashCodeMemoized) {
      int h = 1;
      h *= 1000003;
      h ^= id.hashCode();
      h *= 1000003;
      h ^= name.hashCode();
      h *= 1000003;
      h ^= pilotConnection.hashCode();
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  public static final class Mapper implements ResponseFieldMapper<StarshipFragment> {
    final PilotConnection.Mapper pilotConnectionFieldMapper = new PilotConnection.Mapper();

    @Override
    public StarshipFragment map(ResponseReader reader) {
      final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[0]);
      final String name = reader.readString($responseFields[1]);
      final PilotConnection pilotConnection = reader.readObject($responseFields[2], new ResponseReader.ObjectReader<PilotConnection>() {
        @Override
        public PilotConnection read(ResponseReader reader) {
          return pilotConnectionFieldMapper.map(reader);
        }
      });
      return new StarshipFragment(id, name, pilotConnection);
    }
  }

  public static class PilotConnection {
    static final ResponseField[] $responseFields = {
      ResponseField.forList("edges", "edges", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final Optional<List<Edge>> edges;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public PilotConnection(@Nullable List<Edge> edges) {
      this.edges = Optional.fromNullable(edges);
    }

    /**
     * A list of edges.
     */
    public Optional<List<Edge>> edges() {
      return this.edges;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeList($responseFields[0], edges.isPresent() ? edges.get() : null, new ResponseWriter.ListWriter() {
            @Override
            public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
              for (Object item : items) {
                listItemWriter.writeObject(((Edge) item).marshaller());
              }
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "PilotConnection{"
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
      if (o instanceof PilotConnection) {
        PilotConnection that = (PilotConnection) o;
        return this.edges.equals(that.edges);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= edges.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<PilotConnection> {
      final Edge.Mapper edgeFieldMapper = new Edge.Mapper();

      @Override
      public PilotConnection map(ResponseReader reader) {
        final List<Edge> edges = reader.readList($responseFields[0], new ResponseReader.ListReader<Edge>() {
          @Override
          public Edge read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Edge>() {
              @Override
              public Edge read(ResponseReader reader) {
                return edgeFieldMapper.map(reader);
              }
            });
          }
        });
        return new PilotConnection(edges);
      }
    }
  }

  public static class Edge {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("node", "node", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final Optional<Node> node;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Edge(@Nullable Node node) {
      this.node = Optional.fromNullable(node);
    }

    /**
     * The item at the end of the edge
     */
    public Optional<Node> node() {
      return this.node;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], node.isPresent() ? node.get().marshaller() : null);
        }
      };
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
        return this.node.equals(that.node);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= node.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Edge> {
      final Node.Mapper nodeFieldMapper = new Node.Mapper();

      @Override
      public Edge map(ResponseReader reader) {
        final Node node = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<Node>() {
          @Override
          public Node read(ResponseReader reader) {
            return nodeFieldMapper.map(reader);
          }
        });
        return new Edge(node);
      }
    }
  }

  public static class Node {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forFragment("__typename", "__typename", Arrays.asList("Person"))
    };

    final @NotNull String __typename;

    private final @NotNull Fragments fragments;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Node(@NotNull String __typename, @NotNull Fragments fragments) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.fragments = Utils.checkNotNull(fragments, "fragments == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    public @NotNull Fragments fragments() {
      return this.fragments;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          fragments.marshaller().marshal(writer);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Node{"
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
      if (o instanceof Node) {
        Node that = (Node) o;
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
      final @NotNull PilotFragment pilotFragment;

      private transient volatile String $toString;

      private transient volatile int $hashCode;

      private transient volatile boolean $hashCodeMemoized;

      public Fragments(@NotNull PilotFragment pilotFragment) {
        this.pilotFragment = Utils.checkNotNull(pilotFragment, "pilotFragment == null");
      }

      public @NotNull PilotFragment pilotFragment() {
        return this.pilotFragment;
      }

      public ResponseFieldMarshaller marshaller() {
        return new ResponseFieldMarshaller() {
          @Override
          public void marshal(ResponseWriter writer) {
            final PilotFragment $pilotFragment = pilotFragment;
            if ($pilotFragment != null) {
              $pilotFragment.marshaller().marshal(writer);
            }
          }
        };
      }

      @Override
      public String toString() {
        if ($toString == null) {
          $toString = "Fragments{"
            + "pilotFragment=" + pilotFragment
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
          return this.pilotFragment.equals(that.pilotFragment);
        }
        return false;
      }

      @Override
      public int hashCode() {
        if (!$hashCodeMemoized) {
          int h = 1;
          h *= 1000003;
          h ^= pilotFragment.hashCode();
          $hashCode = h;
          $hashCodeMemoized = true;
        }
        return $hashCode;
      }

      public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
        final PilotFragment.Mapper pilotFragmentFieldMapper = new PilotFragment.Mapper();

        @Override
        public @NotNull Fragments map(ResponseReader reader, @NotNull String conditionalType) {
          PilotFragment pilotFragment = pilotFragmentFieldMapper.map(reader);
          return new Fragments(Utils.checkNotNull(pilotFragment, "pilotFragment == null"));
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<Node> {
      final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

      @Override
      public Node map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final Fragments fragments = reader.readConditional($responseFields[1], new ResponseReader.ConditionalTypeReader<Fragments>() {
          @Override
          public Fragments read(String conditionalType, ResponseReader reader) {
            return fragmentsFieldMapper.map(reader, conditionalType);
          }
        });
        return new Node(__typename, fragments);
      }
    }
  }
}
