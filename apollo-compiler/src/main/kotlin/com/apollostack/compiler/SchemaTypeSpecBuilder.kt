package com.apollostack.compiler

import com.apollostack.compiler.ir.Field
import com.apollostack.compiler.ir.InlineFragment
import com.squareup.javapoet.*
import javax.lang.model.element.Modifier

class SchemaTypeSpecBuilder(
    val typeName: String,
    val fields: List<Field>,
    val fragmentSpreads: List<String>,
    val inlineFragments: List<InlineFragment>,
    val abstract: Boolean
) {
  fun build(vararg modifiers: Modifier): TypeSpec {
    val typeSpecBuilder = if (abstract) {
      TypeSpec.interfaceBuilder(typeName)
    } else {
      TypeSpec.classBuilder(typeName)
          .addMethod(SchemaTypeConstructorBuilder(typeName, fields, fragmentSpreads, inlineFragments).build())
    }
    return typeSpecBuilder
        .addModifiers(*modifiers)
        .addFields(fields, abstract)
        .addInnerTypes(fields)
        .addInlineFragments(inlineFragments)
        .addInnerFragmentTypes(fragmentSpreads)
        .build()
  }

  private fun TypeSpec.Builder.addFields(fields: List<Field>, abstract: Boolean): TypeSpec.Builder {
    val fieldSpecs = if (abstract) emptyList() else fields.map(Field::fieldSpec)
    val methodSpecs = fields.map { it.accessorMethodSpec(abstract) }
    return addFields(fieldSpecs)
        .addMethods(methodSpecs)
  }

  /** Returns a list of fragment types referenced by the provided list of fields */
  private fun TypeSpec.Builder.addInnerFragmentTypes(fragments: List<String>): TypeSpec.Builder {
    if (fragments.isNotEmpty()) {
      addMethod(fragmentsAccessorMethodSpec(abstract))
      addFields(if (abstract) emptyList() else listOf(fragmentsFieldSpec()))
      addType(fragmentsTypeSpec(fragments, abstract))
    }
    return this
  }

  /** Returns a list of types referenced by the inner fields in the provided fields */
  private fun TypeSpec.Builder.addInnerTypes(fields: List<Field>): TypeSpec.Builder {
    val typeSpecs = fields.filter(Field::isNonScalar).map { it.toTypeSpec(abstract) }
    return addTypes(typeSpecs)
  }

  private fun TypeSpec.Builder.addInlineFragments(fragments: List<InlineFragment>): TypeSpec.Builder {
    val typeSpecs = fragments.map { it.toTypeSpec(abstract) }
    val methodSpecs = fragments.map { it.accessorMethodSpec(abstract) }
    val fieldSpecs = if (abstract) emptyList() else fragments.map { it.fieldSpec() }
    return addTypes(typeSpecs)
        .addMethods(methodSpecs)
        .addFields(fieldSpecs)
  }

  private fun fragmentsAccessorMethodSpec(abstract: Boolean): MethodSpec {
    val methodSpecBuilder = MethodSpec
        .methodBuilder(FRAGMENTS_INTERFACE_NAME.decapitalize())
        .returns(ClassName.get("", FRAGMENTS_INTERFACE_NAME))
        .addModifiers(Modifier.PUBLIC)
        .addModifiers(if (abstract) listOf(Modifier.ABSTRACT) else emptyList())
    if (!abstract) {
      methodSpecBuilder.addCode(CodeBlock.of("return this.${FRAGMENTS_INTERFACE_NAME.toLowerCase()};\n"))
    }
    return methodSpecBuilder.build()
  }

  private fun fragmentsFieldSpec(): FieldSpec = FieldSpec
      .builder(ClassName.get("", FRAGMENTS_INTERFACE_NAME.capitalize()), FRAGMENTS_INTERFACE_NAME.decapitalize())
      .addModifiers(Modifier.PRIVATE)
      .build()

  /** Returns a generic `Fragments` interface with methods for each of the provided fragments */
  private fun fragmentsTypeSpec(fragments: List<String>, abstract: Boolean): TypeSpec {

    fun TypeSpec.Builder.addFragmentFields(): TypeSpec.Builder {
      if (!abstract) {
        addFields(fragments.map {
          FieldSpec
              .builder(ClassName.get("", it.capitalize()), it.decapitalize())
              .addModifiers(Modifier.PRIVATE)
              .build()
        })
      }
      return this
    }

    fun TypeSpec.Builder.addFragmentAccessorMethods(): TypeSpec.Builder {
      return addMethods(fragments.map {
        val methodSpecBuilder = MethodSpec
            .methodBuilder(it.decapitalize())
            .returns(ClassName.get("", it.capitalize()))
            .addModifiers(Modifier.PUBLIC)
            .addModifiers(if (abstract) listOf(Modifier.ABSTRACT) else emptyList())
        if (!abstract) {
          methodSpecBuilder.addStatement("return this.\$L", it.decapitalize())
        }
        methodSpecBuilder.build()
      })
    }

    val typeSpecBuilder = if (abstract) {
      TypeSpec.interfaceBuilder(FRAGMENTS_INTERFACE_NAME)
    } else {
      TypeSpec.classBuilder(FRAGMENTS_INTERFACE_NAME)
          .addMethod( SchemaFragmentsConstructorBuilder(fragmentSpreads).build())
    }
    return typeSpecBuilder
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .addFragmentFields()
        .addFragmentAccessorMethods()
        .build()
  }

  companion object {
    val FRAGMENTS_INTERFACE_NAME: String = "Fragments"
  }
}