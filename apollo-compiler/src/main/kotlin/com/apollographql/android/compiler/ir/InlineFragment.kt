package com.apollographql.android.compiler.ir

import com.apollographql.android.compiler.JavaTypeResolver
import com.apollographql.android.compiler.SchemaTypeSpecBuilder
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier

data class InlineFragment(
    val typeCondition: String,
    val fields: List<Field>,
    val fragmentSpreads: List<String>?
) : CodeGenerator {
  override fun toTypeSpec(context: CodeGenerationContext): TypeSpec =
      SchemaTypeSpecBuilder(interfaceName(), fields, fragmentSpreads ?: emptyList(), emptyList(), context)
          .build(Modifier.PUBLIC, Modifier.STATIC)

  fun accessorMethodSpec(): MethodSpec {
    return MethodSpec.methodBuilder(interfaceName().decapitalize())
        .addModifiers(Modifier.PUBLIC)
        .returns(typeName())
        .addStatement("return this.\$L", interfaceName().decapitalize())
        .build()
  }

  fun fieldSpec(): FieldSpec =
      FieldSpec.builder(typeName(), interfaceName().decapitalize())
          .addModifiers(Modifier.PRIVATE)
          .build()

  private fun interfaceName() = "$INTERFACE_PREFIX${typeCondition.capitalize()}"

  private fun typeName() = JavaTypeResolver(emptyMap(), "").resolve(interfaceName(), true)

  companion object {
    private val INTERFACE_PREFIX = "As"
  }
}
