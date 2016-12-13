package com.example.directives;

import com.apollostack.api.Query;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public final class TestQuery implements Query {
  public static final String OPERATION_DEFINITION = "query TestQuery {\n"
      + "  hero {\n"
      + "    __typename\n"
      + "    name @include(if: false)\n"
      + "  }\n"
      + "}";

  @Override
  public String operationDefinition() {
    return OPERATION_DEFINITION;
  }

  @Override
  public List<String> fragmentDefinitions() {
    return Collections.emptyList();
  }

  @Override
  public Map<String, Object> variableDefinitions() {
    return Collections.emptyMap();
  }

  public interface Data extends Query.Data {
    @Nullable Hero hero();

    interface Hero {
      @Nullable String name();
    }
  }
}
