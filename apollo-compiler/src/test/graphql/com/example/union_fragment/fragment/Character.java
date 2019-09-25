// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_fragment.fragment;

import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Utils;
import com.example.union_fragment.type.CustomType;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Character implements GraphqlFragment {
  static final ResponseField[] $responseFields = {
    ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
  };

  public static final String FRAGMENT_DEFINITION = "fragment Character on Character {\n"
      + "  id\n"
      + "  name\n"
      + "}";

  public static final List<String> POSSIBLE_TYPES = Collections.unmodifiableList(Arrays.asList( "Human", "Droid"));

  final @NotNull String id;

  final @NotNull String name;

  private transient volatile String $toString;

  private transient volatile int $hashCode;

  private transient volatile boolean $hashCodeMemoized;

  public Character(@NotNull String id, @NotNull String name) {
    this.id = Utils.checkNotNull(id, "id == null");
    this.name = Utils.checkNotNull(name, "name == null");
  }

  /**
   * The ID of the character
   */
  public @NotNull String id() {
    return this.id;
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
        writer.writeCustom((ResponseField.CustomTypeField) $responseFields[0], id);
        writer.writeString($responseFields[1], name);
      }
    };
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = "Character{"
        + "id=" + id + ", "
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
    if (o instanceof Character) {
      Character that = (Character) o;
      return this.id.equals(that.id)
       && this.name.equals(that.name);
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
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  public static final class Mapper implements ResponseFieldMapper<Character> {
    @Override
    public Character map(ResponseReader reader) {
      final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[0]);
      final String name = reader.readString($responseFields[1]);
      return new Character(id, name);
    }
  }
}
