package com.apollostack.compiler.ir

import com.apollostack.compiler.convertToPOJO
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier

class FragmentTypeSpecBuilder(
    val fragment: Fragment,
    val generatePOJO: Boolean
) : CodeGenerator {
  override fun toTypeSpec(): TypeSpec = fragment.toTypeSpec().let {
    if (generatePOJO) it.convertToPOJO(Modifier.PUBLIC) else it
  }
}