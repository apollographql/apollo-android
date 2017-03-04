package com.apollographql.android.impl;

import com.apollographql.android.api.graphql.Field;
import com.apollographql.android.api.graphql.FragmentResponseFieldMapper;
import com.apollographql.android.api.graphql.Operation;
import com.apollographql.android.api.graphql.Query;
import com.apollographql.android.api.graphql.ResponseFieldMapper;
import com.apollographql.android.api.graphql.ResponseReader;
import com.apollographql.android.impl.fragment.FilmFragment;
import com.apollographql.android.impl.fragment.PlanetFragment;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class AllPlanets implements Query<AllPlanets.Data, Operation.Variables> {
  public static final String OPERATION_DEFINITION = "query TestQuery {\n"
      + "  allPlanets(first: 300) {\n"
      + "    planets {\n"
      + "      ...PlanetFragment\n"
      + "      filmConnection {\n"
      + "        totalCount\n"
      + "        films {\n"
      + "          title\n"
      + "          ...FilmFragment\n"
      + "        }\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION + "\n"
      + PlanetFragment.FRAGMENT_DEFINITION + "\n"
      + FilmFragment.FRAGMENT_DEFINITION;

  private final Variables variables;

  public AllPlanets() {
    this.variables = Operation.EMPTY_VARIABLES;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public Variables variables() {
    return variables;
  }

  @Override public ResponseFieldMapper<? extends Operation.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static class Data implements Operation.Data {
    private @Nullable AllPlanet allPlanets;

    public Data(@Nullable AllPlanet allPlanets) {
      this.allPlanets = allPlanets;
    }

    public @Nullable AllPlanet allPlanets() {
      return this.allPlanets;
    }

    public static class AllPlanet {
      private @Nullable List<Planet> planets;

      public AllPlanet(@Nullable List<Planet> planets) {
        this.planets = planets;
      }

      public @Nullable List<Planet> planets() {
        return this.planets;
      }

      public static class Planet {
        private @Nullable FilmConnection filmConnection;

        private Fragments fragments;

        public Planet(@Nullable FilmConnection filmConnection, Fragments fragments) {
          this.filmConnection = filmConnection;
          this.fragments = fragments;
        }

        public @Nullable FilmConnection filmConnection() {
          return this.filmConnection;
        }

        public Fragments fragments() {
          return this.fragments;
        }

        public static class FilmConnection {
          private @Nullable Integer totalCount;

          private @Nullable List<Film> films;

          public FilmConnection(@Nullable Integer totalCount,
              @Nullable List<Film> films) {
            this.totalCount = totalCount;
            this.films = films;
          }

          public @Nullable Integer totalCount() {
            return this.totalCount;
          }

          public @Nullable List<Film> films() {
            return this.films;
          }

          public static class Film {
            private @Nullable String title;

            private Fragments fragments;

            public Film(@Nullable String title, Fragments fragments) {
              this.title = title;
              this.fragments = fragments;
            }

            public @Nullable String title() {
              return this.title;
            }

            public Fragments fragments() {
              return this.fragments;
            }

            public static class Fragments {
              private FilmFragment filmFragment;

              public Fragments(FilmFragment filmFragment) {
                this.filmFragment = filmFragment;
              }

              public FilmFragment filmFragment() {
                return this.filmFragment;
              }

              public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
                final FilmFragment.Mapper filmFragmentMapper = new FilmFragment.Mapper();

                @Override
                public Fragments map(ResponseReader reader, @Nonnull String conditionalType) throws IOException {
                  FilmFragment filmfragment = null;
                  if (conditionalType.equals(FilmFragment.TYPE_CONDITION)) {
                    filmfragment = new FilmFragment.Mapper().map(reader);
                  }
                  return new Fragments(filmfragment);
                }
              }
            }

            public static final class Mapper implements ResponseFieldMapper<Film> {
              final Fragments.Mapper fragmentsMapper = new Fragments.Mapper();

              final Field[] fields = {
                  Field.forString("title", "title", null, true),
                  Field.forConditionalType("__typename", "__typename", new Field.ConditionalTypeReader<Fragments>() {
                    @Override
                    public Fragments read(String conditionalType, ResponseReader reader) throws
                        IOException {
                      return fragmentsMapper.map(reader, conditionalType);
                    }
                  })
              };

              @Override
              public Film map(ResponseReader reader) throws IOException {
                final String title = reader.read(fields[0]);
                final Fragments fragments = reader.read(fields[1]);
                return new Film(title, fragments);
              }
            }
          }

          public static final class Mapper implements ResponseFieldMapper<FilmConnection> {
            final Film.Mapper filmMapper = new Film.Mapper();

            final Field[] fields = {
                Field.forInt("totalCount", "totalCount", null, true),
                Field.forList("films", "films", null, true, new Field.ObjectReader<Film>() {
                  @Override public Film read(final ResponseReader reader) throws IOException {
                    return filmMapper.map(reader);
                  }
                })
            };

            @Override
            public FilmConnection map(ResponseReader reader) throws IOException {
              final Integer totalCount = reader.read(fields[0]);
              final List<Film> films = reader.read(fields[1]);
              return new FilmConnection(totalCount, films);
            }
          }
        }

        public static class Fragments {
          private PlanetFragment planetFargment;

          public Fragments(PlanetFragment planetFargment) {
            this.planetFargment = planetFargment;
          }

          public PlanetFragment planetFargment() {
            return this.planetFargment;
          }

          public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
            final PlanetFragment.Mapper planetFragmentMapper = new PlanetFragment.Mapper();

            @Override
            public Fragments map(ResponseReader reader, @Nonnull String conditionalType) throws IOException {
              PlanetFragment planetFragment = null;
              if (conditionalType.equals(PlanetFragment.TYPE_CONDITION)) {
                planetFragment = planetFragmentMapper.map(reader);
              }
              return new Fragments(planetFragment);
            }
          }
        }

        public static final class Mapper implements ResponseFieldMapper<Planet> {
          final FilmConnection.Mapper filmConnectionMapper = new FilmConnection.Mapper();

          final Fragments.Mapper fragmentsMapper = new Fragments.Mapper();

          final Field[] fields = {
              Field.forObject("filmConnection", "filmConnection", null, true, new Field.ObjectReader<FilmConnection>() {
                @Override public FilmConnection read(final ResponseReader reader) throws IOException {
                  return filmConnectionMapper.map(reader);
                }
              }),
              Field.forConditionalType("__typename", "__typename", new Field.ConditionalTypeReader<Fragments>() {
                @Override
                public Fragments read(String conditionalType, ResponseReader reader) throws
                    IOException {
                  return fragmentsMapper.map(reader, conditionalType);
                }
              })
          };

          @Override
          public Planet map(ResponseReader reader) throws IOException {
            final FilmConnection filmConnection = reader.read(fields[0]);
            final Fragments fragments = reader.read(fields[1]);
            return new Planet(filmConnection, fragments);
          }
        }
      }

      public static final class Mapper implements ResponseFieldMapper<AllPlanet> {
        final Planet.Mapper planetMapper = new Planet.Mapper();

        final Field[] fields = {
            Field.forList("planets", "planets", null, true, new Field.ObjectReader<Planet>() {
              @Override public Planet read(final ResponseReader reader) throws IOException {
                return planetMapper.map(reader);
              }
            })
        };

        @Override
        public AllPlanet map(ResponseReader reader) throws IOException {
          final List<Planet> planets = reader.read(fields[0]);
          return new AllPlanet(planets);
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final AllPlanet.Mapper allPlanetMapper = new AllPlanet.Mapper();

      final Field[] fields = {
          Field.forObject("allPlanets", "allPlanets", null, true, new Field.ObjectReader<AllPlanet>() {
            @Override public AllPlanet read(final ResponseReader reader) throws IOException {
              return allPlanetMapper.map(reader);
            }
          })
      };

      @Override
      public Data map(ResponseReader reader) throws IOException {
        final AllPlanet allPlanets = reader.read(fields[0]);
        return new Data(allPlanets);
      }
    }
  }
}
