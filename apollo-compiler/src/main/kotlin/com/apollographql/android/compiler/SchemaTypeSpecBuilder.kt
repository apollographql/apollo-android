package com.apollographql.android.compiler

import com.apollographql.android.compiler.ir.CodeGeneratorContext
import com.apollographql.android.compiler.ir.Field
import com.apollographql.android.compiler.ir.InlineFragment
import com.squareup.javapoet.*
import java.io.IOException
import javax.lang.model.element.Modifier

class SchemaTypeSpecBuilder(
    val typeName: String,
    val fields: List<Field>,
    val fragmentSpreads: List<String>,
    val inlineFragments: List<InlineFragment>,
    val context: CodeGeneratorContext
) {
  private val uniqueTypeName = formatUniqueTypeName(typeName, context.reservedTypeNames)
  private val innerTypeNameOverrideMap = buildUniqueTypeNameMap(context.reservedTypeNames + typeName)
  private val hasFragments = inlineFragments.isNotEmpty() || fragmentSpreads.isNotEmpty()

  fun build(vararg modifiers: Modifier): TypeSpec {
    val typeSpecBuilder = if (context.abstractType) {
      TypeSpec.interfaceBuilder(uniqueTypeName)
    } else {
      val mapperField = ResponseFieldMapperBuilder(uniqueTypeName, fields, fragmentSpreads, inlineFragments,
          innerTypeNameOverrideMap, context.typeDeclarations, context.customScalarTypeMap).build()
      TypeSpec.classBuilder(uniqueTypeName)
          .addField(mapperField)
          .addMethod(MethodSpec
              .constructorBuilder()
              .addModifiers(Modifier.PUBLIC)
              .addParameter(PARAM_SPEC_READER)
              .addException(IOException::class.java)
              .addStatement("\$L.map(\$L, this)", mapperField.name,
                  if (hasFragments) "$PARAM_READER.toBufferedReader()" else PARAM_READER)
              .build()
          )
    }
    return typeSpecBuilder
        .addModifiers(*modifiers)
        .addFields(fields, context.abstractType)
        .addInnerTypes(fields)
        .addInlineFragments(inlineFragments)
        .addInnerFragmentTypes(fragmentSpreads)
        .build()
  }

  private fun TypeSpec.Builder.addFields(fields: List<Field>, abstractClass: Boolean): TypeSpec.Builder {
    val fieldSpecs = if (abstractClass) emptyList() else fields.map {
      it.fieldSpec(context.typesPackage, context.customScalarTypeMap)
    }
    val methodSpecs = fields.map {
      it.accessorMethodSpec(abstractClass, context.typesPackage, context.customScalarTypeMap)
    }
    return addFields(fieldSpecs.map { it.overrideType(innerTypeNameOverrideMap) })
        .addMethods(methodSpecs.map { it.overrideReturnType(innerTypeNameOverrideMap) })
  }

  /** Returns a list of fragment types referenced by the provided list of fields */
  private fun TypeSpec.Builder.addInnerFragmentTypes(fragments: List<String>): TypeSpec.Builder {
    if (fragments.isNotEmpty()) {
      addMethod(fragmentsAccessorMethodSpec(context.abstractType))
      addFields(if (context.abstractType) emptyList() else listOf(fragmentsFieldSpec()))
      addType(fragmentsTypeSpec(fragments, context.abstractType, context.fragmentsPackage))
    }
    return this
  }

  /** Returns a list of types referenced by the inner fields in the provided fields */
  private fun TypeSpec.Builder.addInnerTypes(fields: List<Field>): TypeSpec.Builder {
    val reservedTypeNames = context.reservedTypeNames + typeName + fields.filter(Field::isNonScalar).map(
        Field::normalizedName)
    val typeSpecs = fields.filter(Field::isNonScalar).map {
      it.toTypeSpec(CodeGeneratorContext(context.abstractType, reservedTypeNames.minus(it.normalizedName()),
          context.typeDeclarations, context.fragmentsPackage, context.typesPackage, context.customScalarTypeMap))
    }
    return addTypes(typeSpecs)
  }

  private fun TypeSpec.Builder.addInlineFragments(fragments: List<InlineFragment>): TypeSpec.Builder {
    val reservedTypeNames = context.reservedTypeNames + typeName + fields.filter(Field::isNonScalar).map(
        Field::normalizedName)
    val typeSpecs = fragments.map {
      it.toTypeSpec(CodeGeneratorContext(context.abstractType, reservedTypeNames, context.typeDeclarations,
          context.fragmentsPackage, context.typesPackage, context.customScalarTypeMap))
    }
    val methodSpecs = fragments.map { it.accessorMethodSpec(context.abstractType) }
    val fieldSpecs = if (context.abstractType) emptyList() else fragments.map { it.fieldSpec() }
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
            .returns(ClassName.get(fragmentsPackage, it.capitalize()))
            .addModifiers(Modifier.PUBLIC)
            .addModifiers(if (abstractClass) listOf(Modifier.ABSTRACT) else emptyList())
        if (!abstractClass) {
          methodSpecBuilder.addStatement("return this.\$L", it.decapitalize())
        }
        methodSpecBuilder.build()
      })
    }

    val typeSpecBuilder = if (abstractClass) {
      TypeSpec.interfaceBuilder(FRAGMENTS_INTERFACE_NAME)
    } else {
      TypeSpec.classBuilder(FRAGMENTS_INTERFACE_NAME)
          .addMethod(SchemaFragmentsConstructorBuilder(fragmentSpreads).build())
    }
    return typeSpecBuilder
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .addFragmentFields()
        .addFragmentAccessorMethods()
        .build()
  }

  private fun buildUniqueTypeNameMap(reservedTypeNames: List<String>) =
      reservedTypeNames.distinct().associate {
        it to formatUniqueTypeName(it, reservedTypeNames)
      }

  private fun formatUniqueTypeName(typeName: String, reservedTypeNames: List<String>): String {
    val suffix = reservedTypeNames.count { it == typeName }.let { if (it > 0) "$".repeat(it) else "" }
    return "$typeName$suffix"
  }

  companion object {
    val FRAGMENTS_INTERFACE_NAME: String = "Fragments"
    private val PARAM_READER = "reader"
    private val PARAM_SPEC_READER = ParameterSpec.builder(ClassNames.API_RESPONSE_READER, PARAM_READER).build()
  }
}
