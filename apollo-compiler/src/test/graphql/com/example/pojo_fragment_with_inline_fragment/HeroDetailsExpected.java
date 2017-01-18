package com.example.pojo_fragment_with_inline_fragment;

import com.apollostack.api.graphql.Field;
import com.apollostack.api.graphql.ResponseFieldMapper;
import com.apollostack.api.graphql.ResponseReader;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HeroDetails {
  private static final ResponseFieldMapper<HeroDetails> MAPPER = new ResponseFieldMapper<HeroDetails>() {
    private final Field[] FIELDS = {
      Field.forString("name", "name", null, false),
      Field.forObject("friendsConnection", "friendsConnection", null, false, new Field.ObjectReader<FriendsConnection>() {
        @Override public FriendsConnection read(final ResponseReader reader) throws IOException {
          return new FriendsConnection(reader);
        }
      }),
      Field.forString("__typename", "__typename", null, false)
    };

    @Override
    public void handleValue(int fieldIndex, Object value, HeroDetails instance,
        ResponseReader reader) throws IOException {
      switch (fieldIndex) {
        case 0: {
          instance.name = (String) value;
          break;
        }
        case 1: {
          instance.friendsConnection = (FriendsConnection) value;
          break;
        }
        case 2: {
          String typename = (String) value;
          if (typename.equals("Droid")) {
            instance.asDroid = new AsDroid(reader);
          }
          break;
        }
      }
    }

    @Override
    public Field[] fields() {
      return FIELDS;
    }
  };

  public static final String FRAGMENT_DEFINITION = "fragment HeroDetails on Character {\n"
      + "  __typename\n"
      + "  name\n"
      + "  friendsConnection {\n"
      + "    totalCount\n"
      + "    edges {\n"
      + "      node {\n"
      + "        __typename\n"
      + "        name\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "  ... on Droid {\n"
      + "    name\n"
      + "    primaryFunction\n"
      + "  }\n"
      + "}";

  public static final String TYPE_CONDITION = "Character";

  private @Nonnull String name;

  private @Nonnull FriendsConnection friendsConnection;

  private @Nullable AsDroid asDroid;

  public HeroDetails(ResponseReader reader) throws IOException {
    MAPPER.map(reader.toBufferedReader(), this);
  }

  public @Nonnull String name() {
    return this.name;
  }

  public @Nonnull FriendsConnection friendsConnection() {
    return this.friendsConnection;
  }

  public @Nullable AsDroid asDroid() {
    return this.asDroid;
  }

  public static class FriendsConnection {
    private static final ResponseFieldMapper<FriendsConnection> MAPPER = new ResponseFieldMapper<FriendsConnection>() {
      private final Field[] FIELDS = {
        Field.forInt("totalCount", "totalCount", null, true),
        Field.forList("edges", "edges", null, true, new Field.ObjectReader<Edge>() {
          @Override public Edge read(final ResponseReader reader) throws IOException {
            return new Edge(reader);
          }
        })
      };

      @Override
      public void handleValue(int fieldIndex, Object value, FriendsConnection instance,
          ResponseReader reader) throws IOException {
        switch (fieldIndex) {
          case 0: {
            instance.totalCount = (Integer) value;
            break;
          }
          case 1: {
            instance.edges = (List<? extends Edge>) value;
            break;
          }
        }
      }

      @Override
      public Field[] fields() {
        return FIELDS;
      }
    };

    private @Nullable Integer totalCount;

    private @Nullable List<? extends Edge> edges;

    public FriendsConnection(ResponseReader reader) throws IOException {
      MAPPER.map(reader, this);
    }

    public @Nullable Integer totalCount() {
      return this.totalCount;
    }

    public @Nullable List<? extends Edge> edges() {
      return this.edges;
    }

    public static class Edge {
      private static final ResponseFieldMapper<Edge> MAPPER = new ResponseFieldMapper<Edge>() {
        private final Field[] FIELDS = {
          Field.forObject("node", "node", null, true, new Field.ObjectReader<Node>() {
            @Override public Node read(final ResponseReader reader) throws IOException {
              return new Node(reader);
            }
          })
        };

        @Override
        public void handleValue(int fieldIndex, Object value, Edge instance, ResponseReader reader)
            throws IOException {
          switch (fieldIndex) {
            case 0: {
              instance.node = (Node) value;
              break;
            }
          }
        }

        @Override
        public Field[] fields() {
          return FIELDS;
        }
      };

      private @Nullable Node node;

      public Edge(ResponseReader reader) throws IOException {
        MAPPER.map(reader, this);
      }

      public @Nullable Node node() {
        return this.node;
      }

      public static class Node {
        private static final ResponseFieldMapper<Node> MAPPER = new ResponseFieldMapper<Node>() {
          private final Field[] FIELDS = {
            Field.forString("name", "name", null, false)
          };

          @Override
          public void handleValue(int fieldIndex, Object value, Node instance,
              ResponseReader reader) throws IOException {
            switch (fieldIndex) {
              case 0: {
                instance.name = (String) value;
                break;
              }
            }
          }

          @Override
          public Field[] fields() {
            return FIELDS;
          }
        };

        private @Nonnull String name;

        public Node(ResponseReader reader) throws IOException {
          MAPPER.map(reader, this);
        }

        public @Nonnull String name() {
          return this.name;
        }
      }
    }
  }

  public static class AsDroid {
    private static final ResponseFieldMapper<AsDroid> MAPPER = new ResponseFieldMapper<AsDroid>() {
      private final Field[] FIELDS = {
        Field.forString("name", "name", null, false),
        Field.forObject("friendsConnection", "friendsConnection", null, false, new Field.ObjectReader<FriendsConnection$>() {
          @Override public FriendsConnection$ read(final ResponseReader reader) throws IOException {
            return new FriendsConnection$(reader);
          }
        }),
        Field.forString("primaryFunction", "primaryFunction", null, true)
      };

      @Override
      public void handleValue(int fieldIndex, Object value, AsDroid instance, ResponseReader reader)
          throws IOException {
        switch (fieldIndex) {
          case 0: {
            instance.name = (String) value;
            break;
          }
          case 1: {
            instance.friendsConnection = (FriendsConnection$) value;
            break;
          }
          case 2: {
            instance.primaryFunction = (String) value;
            break;
          }
        }
      }

      @Override
      public Field[] fields() {
        return FIELDS;
      }
    };

    private @Nonnull String name;

    private @Nonnull FriendsConnection$ friendsConnection;

    private @Nullable String primaryFunction;

    public AsDroid(ResponseReader reader) throws IOException {
      MAPPER.map(reader, this);
    }

    public @Nonnull String name() {
      return this.name;
    }

    public @Nonnull FriendsConnection$ friendsConnection() {
      return this.friendsConnection;
    }

    public @Nullable String primaryFunction() {
      return this.primaryFunction;
    }

    public static class FriendsConnection$ {
      private static final ResponseFieldMapper<FriendsConnection$> MAPPER = new ResponseFieldMapper<FriendsConnection$>() {
        private final Field[] FIELDS = {
          Field.forInt("totalCount", "totalCount", null, true),
          Field.forList("edges", "edges", null, true, new Field.ObjectReader<Edge>() {
            @Override public Edge read(final ResponseReader reader) throws IOException {
              return new Edge(reader);
            }
          })
        };

        @Override
        public void handleValue(int fieldIndex, Object value, FriendsConnection$ instance,
            ResponseReader reader) throws IOException {
          switch (fieldIndex) {
            case 0: {
              instance.totalCount = (Integer) value;
              break;
            }
            case 1: {
              instance.edges = (List<? extends Edge>) value;
              break;
            }
          }
        }

        @Override
        public Field[] fields() {
          return FIELDS;
        }
      };

      private @Nullable Integer totalCount;

      private @Nullable List<? extends Edge> edges;

      public FriendsConnection$(ResponseReader reader) throws IOException {
        MAPPER.map(reader, this);
      }

      public @Nullable Integer totalCount() {
        return this.totalCount;
      }

      public @Nullable List<? extends Edge> edges() {
        return this.edges;
      }

      public static class Edge {
        private static final ResponseFieldMapper<Edge> MAPPER = new ResponseFieldMapper<Edge>() {
          private final Field[] FIELDS = {
            Field.forObject("node", "node", null, true, new Field.ObjectReader<Node>() {
              @Override public Node read(final ResponseReader reader) throws IOException {
                return new Node(reader);
              }
            })
          };

          @Override
          public void handleValue(int fieldIndex, Object value, Edge instance,
              ResponseReader reader) throws IOException {
            switch (fieldIndex) {
              case 0: {
                instance.node = (Node) value;
                break;
              }
            }
          }

          @Override
          public Field[] fields() {
            return FIELDS;
          }
        };

        private @Nullable Node node;

        public Edge(ResponseReader reader) throws IOException {
          MAPPER.map(reader, this);
        }

        public @Nullable Node node() {
          return this.node;
        }

        public static class Node {
          private static final ResponseFieldMapper<Node> MAPPER = new ResponseFieldMapper<Node>() {
            private final Field[] FIELDS = {
              Field.forString("name", "name", null, false)
            };

            @Override
            public void handleValue(int fieldIndex, Object value, Node instance,
                ResponseReader reader) throws IOException {
              switch (fieldIndex) {
                case 0: {
                  instance.name = (String) value;
                  break;
                }
              }
            }

            @Override
            public Field[] fields() {
              return FIELDS;
            }
          };

          private @Nonnull String name;

          public Node(ResponseReader reader) throws IOException {
            MAPPER.map(reader, this);
          }

          public @Nonnull String name() {
            return this.name;
          }
        }
      }
    }
  }
}
