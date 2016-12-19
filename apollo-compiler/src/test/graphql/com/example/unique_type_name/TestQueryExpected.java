package com.example.unique_type_name;

import com.apollostack.api.Query;
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class TestQuery implements Query<Query.Variables> {
  public static final String OPERATION_DEFINITION = "query TestQuery {\n"
      + "  hero {\n"
      + "    __typename\n"
      + "    name\n"
      + "    friends {\n"
      + "      __typename\n"
      + "      name\n"
      + "    }\n"
      + "    ... on Human {\n"
      + "      height\n"
      + "      friends {\n"
      + "        __typename\n"
      + "        appearsIn\n"
      + "        friends {\n"
      + "          __typename\n"
      + "          ...HeroDetails\n"
      + "        }\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION + "\n"
   + HeroDetails.FRAGMENT_DEFINITION;

  private final Query.Variables variables;

  public TestQuery() {
    this.variables = Query.EMPTY_VARIABLES;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public Query.Variables variables() {
    return variables;
  }

  public interface Data extends Query.Data {
    @Nullable Hero hero();

    interface Hero {
      @Nonnull String name();

      @Nullable List<Friend> friends();

      @Nullable AsHuman asHuman();

      interface Friend {
        @Nonnull String name();
      }

      interface AsHuman {
        @Nonnull String name();

        @Nullable List<Friend$> friends();

        @Nullable Double height();

        interface Friend$ {
          @Nonnull String name();

          @Nonnull List<Episode> appearsIn();

          @Nullable List<Friend$$> friends();

          interface Friend$$ {
            Fragments fragments();

            interface Fragments {
              HeroDetails heroDetails();
            }
          }
        }
      }
    }
  }
}
