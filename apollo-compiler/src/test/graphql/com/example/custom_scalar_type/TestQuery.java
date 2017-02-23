package com.example.custom_scalar_type;

import com.apollographql.android.api.graphql.Field;
import com.apollographql.android.api.graphql.Operation;
import com.apollographql.android.api.graphql.Query;
import com.apollographql.android.api.graphql.ResponseFieldMapper;
import com.apollographql.android.api.graphql.ResponseReader;
import com.example.custom_scalar_type.type.CustomType;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public final class TestQuery implements Query<Operation.Variables> {
  public static final String OPERATION_DEFINITION = "query TestQuery {\n"
      + "  hero {\n"
      + "    __typename\n"
      + "    name\n"
      + "    birthDate\n"
      + "    appearanceDates\n"
      + "  }\n"
      + "}";

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
    private final @Nullable Hero hero;

    public Data(@Nullable Hero hero) {
      this.hero = hero;
    }

    public @Nullable Hero hero() {
      return this.hero;
    }

    @Override
    public String toString() {
      return "Data{"
        + "hero=" + hero
        + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return ((this.hero == null) ? (that.hero == null) : this.hero.equals(that.hero));
      }
      return false;
    }

    @Override
    public int hashCode() {
      int h = 1;
      h *= 1000003;
      h ^= (hero == null) ? 0 : hero.hashCode();
      return h;
    }

    public static class Hero {
      private final @Nonnull String name;

      private final @Nonnull Date birthDate;

      private final @Nonnull List<Date> appearanceDates;

      public Hero(@Nonnull String name, @Nonnull Date birthDate,
          @Nonnull List<Date> appearanceDates) {
        this.name = name;
        this.birthDate = birthDate;
        this.appearanceDates = appearanceDates;
      }

      public @Nonnull String name() {
        return this.name;
      }

      public @Nonnull Date birthDate() {
        return this.birthDate;
      }

      public @Nonnull List<Date> appearanceDates() {
        return this.appearanceDates;
      }

      @Override
      public String toString() {
        return "Hero{"
          + "name=" + name + ", "
          + "birthDate=" + birthDate + ", "
          + "appearanceDates=" + appearanceDates
          + "}";
      }

      @Override
      public boolean equals(Object o) {
        if (o == this) {
          return true;
        }
        if (o instanceof Hero) {
          Hero that = (Hero) o;
          return ((this.name == null) ? (that.name == null) : this.name.equals(that.name))
           && ((this.birthDate == null) ? (that.birthDate == null) : this.birthDate.equals(that.birthDate))
           && ((this.appearanceDates == null) ? (that.appearanceDates == null) : this.appearanceDates.equals(that.appearanceDates));
        }
        return false;
      }

      @Override
      public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (name == null) ? 0 : name.hashCode();
        h *= 1000003;
        h ^= (birthDate == null) ? 0 : birthDate.hashCode();
        h *= 1000003;
        h ^= (appearanceDates == null) ? 0 : appearanceDates.hashCode();
        return h;
      }

      public static final class Mapper implements ResponseFieldMapper<Hero> {
        final Field[] fields = {
          Field.forString("name", "name", null, false),
          Field.forCustomType("birthDate", "birthDate", null, false, CustomType.DATE),
          Field.forList("appearanceDates", "appearanceDates", null, false, new Field.ListReader<Date>() {
            @Override public Date read(final Field.ListItemReader reader) throws IOException {
              return reader.readCustomType(CustomType.DATE);
            }
          })
        };

        @Override
        public Hero map(ResponseReader reader) throws IOException {
          final String name = reader.read(fields[0]);
          final Date birthDate = reader.read(fields[1]);
          final List<Date> appearanceDates = reader.read(fields[2]);
          return new Hero(name, birthDate, appearanceDates);
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Field[] fields = {
        Field.forObject("hero", "hero", null, true, new Field.ObjectReader<Hero>() {
          @Override public Hero read(final ResponseReader reader) throws IOException {
            return new Hero.Mapper().map(reader);
          }
        })
      };

      @Override
      public Data map(ResponseReader reader) throws IOException {
        final Hero hero = reader.read(fields[0]);
        return new Data(hero);
      }
    }
  }
}
