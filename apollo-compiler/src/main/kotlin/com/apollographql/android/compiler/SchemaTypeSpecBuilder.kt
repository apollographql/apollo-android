package com.apollographql.android.compiler

import com.apollographql.android.compiler.ir.CodeGenerationContext
import com.apollographql.android.compiler.ir.Field
import com.apollographql.android.compiler.ir.InlineFragment
import com.squareup.javapoet.*
import javax.lang.model.element.Modifier

class SchemaTypeSpecBuilder(
    val typeName: String,
    val fields: List<Field>,
    val fragmentSpreads: List<String>,
    val inlineFragments: List<InlineFragment>,
    val context: CodeGenerationContext
) {
  private val uniqueTypeName = formatUniqueTypeName(typeName, context.reservedTypeNames)
  private val innerTypeNameOverrideMap = buildUniqueTypeNameMap(context.reservedTypeNames + typeName)

  fun build(vararg modifiers: Modifier): TypeSpec {
    val mapper = SchemaTypeResponseMapperBuilder(uniqueTypeName, fields, fragmentSpreads, inlineFragments,
        innerTypeNameOverrideMap, context).build()
    val typeSpecBuilder = if (context.abstractType) {
      TypeSpec.interfaceBuilder(uniqueTypeName)
    } else {
      TypeSpec.classBuilder(uniqueTypeName)
    }
    return typeSpecBuilder
        .addModifiers(*modifiers)
        .addFields(fields, context.abstractType)
        .addInnerTypes(fields)
        .addInlineFragments(inlineFragments)
        .addInnerFragmentTypes(fragmentSpreads)
        .addType(mapper)
        .build()
        .withFactory(exclude = listOf(mapper.name))
        .withCreator()
        .let {
          if (context.abstractType)
            it
          else
            it
                .withValueInitConstructor()
                .withCreatorImplementation()
                .withFactoryImplementation(exclude = listOf(mapper.name))
        }
  }

  private fun TypeSpec.Builder.addFields(fields: List<Field>, abstractClass: Boolean): TypeSpec.Builder {
    val fieldSpecs = if (abstractClass) emptyList() else fields.map {
      it.fieldSpec(customScalarTypeMap = context.customTypeMap, typesPackage = context.typesPackage)
    }
    val methodSpecs = fields.map {
      it.accessorMethodSpec(abstractClass, context.typesPackage, context.customTypeMap)
    }
    return addFields(fieldSpecs.map { it.overrideType(innerTypeNameOverrideMap) })
        .addMethods(methodSpecs.map { it.overrideReturnType(innerTypeNameOverrideMap) })
  }

  private fun TypeSpec.Builder.addInnerFragmentTypes(fragments: List<String>): TypeSpec.Builder {
    if (fragments.isNotEmpty()) {
      addMethod(fragmentsAccessorMethodSpec(context.abstractType))
      addFields(if (context.abstractType) emptyList() else listOf(fragmentsFieldSpec()))
      addType(fragmentsTypeSpec(fragments, context.abstractType, context.fragmentsPackage))
    }
    return this
  }

  private fun TypeSpec.Builder.addInnerTypes(fields: List<Field>): TypeSpec.Builder {
    val reservedTypeNames = context.reservedTypeNames + typeName + fields.filter(Field::isNonScalar).map(
        Field::normalizedName)
    val typeSpecs = fields.filter(Field::isNonScalar).map { field ->
      field.toTypeSpec(context.withReservedTypeNames(reservedTypeNames.minus(field.normalizedName())))
    }
    return addTypes(typeSpecs)
  }

  private fun TypeSpec.Builder.addInlineFragments(fragments: List<InlineFragment>): TypeSpec.Builder {
    val reservedTypeNames = context.reservedTypeNames + typeName + fields.filter(Field::isNonScalar).map(
        Field::normalizedName)
    val typeSpecs = fragments.map { it.toTypeSpec(context.withReservedTypeNames(reservedTypeNames)) }
    val methodSpecs = fragments.map { it.accessorMethodSpec(context.abstractType) }
    val fieldSpecs = if (context.abstractType) emptyList() else fragments.map { it.fieldSpec() }
    return addTypes(typeSpecs)
        .addMethods(methodSpecs)
        .addFields(fieldSpecs)
  }

  private fun fragmentsAccessorMethodSpec(abstract: Boolean): MethodSpec {
    val methodSpecBuilder = MethodSpec
        .methodBuilder(FRAGMENTS_TYPE_NAME.decapitalize())
        .returns(FRAGMENTS_TYPE)
        .addModifiers(Modifier.PUBLIC)
        .addModifiers(if (abstract) listOf(Modifier.ABSTRACT) else emptyList())
    if (!abstract) {
      methodSpecBuilder.addCode(CodeBlock.of("return this.${FRAGMENTS_TYPE_NAME.toLowerCase()};\n"))
    }
    return methodSpecBuilder.build()
  }

  private fun fragmentsFieldSpec(): FieldSpec = FieldSpec
      .builder(ClassName.get("", FRAGMENTS_TYPE_NAME.capitalize()), FRAGMENTS_TYPE_NAME.decapitalize())
      .addModifiers(Modifier.PRIVATE)
      .build()

  /** Returns a generic `Fragments` interface with methods for each of the provided fragments */
  private fun fragmentsTypeSpec(fragments: List<String>, abstractClass: Boolean, fragmentsPackage: String): TypeSpec {

    fun TypeSpec.Builder.addFragmentFields(): TypeSpec.Builder {
      if (!abstractClass) {
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
            .returns(ClassName.get(fragmentsPackage, it.capitalize()).annotated(listOf(Annotations.NULLABLE)))
            .addModifiers(Modifier.PUBLIC)
            .addModifiers(if (abstractClass) listOf(Modifier.ABSTRACT) else emptyList())
        if (!abstractClass) {
          methodSpecBuilder.addStatement("return this.\$L", it.decapitalize())
        }
        methodSpecBuilder.build()
      })
    }

    val mapper = FragmentsResponseMapperBuilder(fragments, context).build()
    val typeSpecBuilder = if (abstractClass) {
      TypeSpec.interfaceBuilder(FRAGMENTS_TYPE_NAME)
    } else {
      TypeSpec.classBuilder(FRAGMENTS_TYPE_NAME)
    }
    return typeSpecBuilder
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .addFragmentFields()
        .addFragmentAccessorMethods()
        .addType(mapper)
        .build()
        .withFactory(exclude = listOf(mapper.name), include = fragments)
        .withCreator()
        .let {
          if (context.abstractType)
            it
          else
            it
                .withValueInitConstructor()
                .withCreatorImplementation()
                .withFactoryImplementation(exclude = listOf(mapper.name), include = fragments)
        }
  }

  private fun buildUniqueTypeNameMap(reservedTypeNames: List<String>) =
      reservedTypeNames.distinct().associate {
        it to formatUniqueTypeName(it, reservedTypeNames)
      }

  private fun formatUniqueTypeName(typeName: String, reservedTypeNames: List<String>): String {
    val suffix = reservedTypeNames.count { it == typeName }.let { if (it > 0) "$it" else "" }
    return "$typeName$suffix"
  }

  companion object {
    val FRAGMENTS_TYPE_NAME: String = "Fragments"
    val FRAGMENTS_TYPE: ClassName = ClassName.get("", FRAGMENTS_TYPE_NAME).annotated(listOf(Annotations.NONNULL))
  }
}
