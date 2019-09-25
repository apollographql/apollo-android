// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragments_with_type_condition_nullable.fragment;

import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Utils;
import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HumanDetails implements GraphqlFragment {
  static final ResponseField[] $responseFields = {
    ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forDouble("height", "height", null, true, Collections.<ResponseField.Condition>emptyList())
  };

  public static final String FRAGMENT_DEFINITION = "fragment HumanDetails on Human {\n"
      + "  name\n"
      + "  height\n"
      + "}";

  public static final List<String> POSSIBLE_TYPES = Collections.unmodifiableList(Arrays.asList( "Human"));

  final @NotNull String name;

  final @Nullable Double height;

  private transient volatile String $toString;

  private transient volatile int $hashCode;

  private transient volatile boolean $hashCodeMemoized;

  public HumanDetails(@NotNull String name, @Nullable Double height) {
    this.name = Utils.checkNotNull(name, "name == null");
    this.height = height;
  }

  /**
   * What this human calls themselves
   */
  public @NotNull String name() {
    return this.name;
  }

  /**
   * Height in the preferred unit, default is meters
   */
  public @Nullable Double height() {
    return this.height;
  }

  @SuppressWarnings("unchecked")
  public ResponseFieldMarshaller marshaller() {
    return new ResponseFieldMarshaller() {
      @Override
      public void marshal(ResponseWriter writer) {
        writer.writeString($responseFields[0], name);
        writer.writeDouble($responseFields[1], height);
      }
    };
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = "HumanDetails{"
        + "name=" + name + ", "
        + "height=" + height
        + "}";
    }
    return $toString;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof HumanDetails) {
      HumanDetails that = (HumanDetails) o;
      return this.name.equals(that.name)
       && ((this.height == null) ? (that.height == null) : this.height.equals(that.height));
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
      h ^= (height == null) ? 0 : height.hashCode();
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  public static final class Mapper implements ResponseFieldMapper<HumanDetails> {
    @Override
    public HumanDetails map(ResponseReader reader) {
      final String name = reader.readString($responseFields[0]);
      final Double height = reader.readDouble($responseFields[1]);
      return new HumanDetails(name, height);
    }
  }
}
