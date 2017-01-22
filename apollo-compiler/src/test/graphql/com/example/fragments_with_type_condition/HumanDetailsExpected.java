package com.example.fragments_with_type_condition;

import java.lang.Double;
import java.lang.String;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQl")
public interface HumanDetails {
  String FRAGMENT_DEFINITION = "fragment HumanDetails on Human {\n"
      + "  name\n"
      + "  height\n"
      + "}";

  String TYPE_CONDITION = "Human";

  @Nonnull String name();

  @Nullable Double height();
}
