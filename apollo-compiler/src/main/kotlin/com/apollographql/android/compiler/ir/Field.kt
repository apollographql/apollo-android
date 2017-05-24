package com.apollographql.android.compiler.ir

import com.apollographql.android.compiler.*
import com.squareup.javapoet.*
import java.util.*
import javax.lang.model.element.Modifier

data class Field(
    val responseName: String,
    val fieldName: String,
    val type: String,
    val args: List<Map<String, Any>>? = null,
    val isConditional: Boolean = false,
    val fields: List<Field>? = null,
    val fragmentSpreads: List<String>? = null,
    val inlineFragments: List<InlineFragment>? = null,
    val description: String? = null
) : CodeGenerator {

  override fun toTypeSpec(context: CodeGenerationContext): TypeSpec {
    val fields = if (isNonScalar()) listOf(Field("__typename", "__typename", "String!")) + fields!! else emptyList()
    return SchemaTypeSpecBuilder(
        typeName = formatClassName(),
        fields = fields,
        fragmentSpreads = fragmentSpreads ?: emptyList(),
        inlineFragments = inlineFragments ?: emptyList(),
        context = context
    ).build(Modifier.PUBLIC, Modifier.STATIC)
  }

  fun accessorMethodSpec(context: CodeGenerationContext): MethodSpec {
    return MethodSpec.methodBuilder(responseName.escapeJavaReservedWord())
        .addModifiers(Modifier.PUBLIC)
        .returns(toTypeName(methodResponseType(), context))
        .addStatement("return this.\$L", responseName.escapeJavaReservedWord())
        .let { if (description != null) it.addJavadoc("\$L\n", description) else it }
        .build()
  }

  fun fieldSpec(context: CodeGenerationContext, publicModifier: Boolean = false): FieldSpec {
    return FieldSpec.builder(toTypeName(methodResponseType(), context), responseName.escapeJavaReservedWord())
        .addModifiers(if (publicModifier) Modifier.PUBLIC else Modifier.PRIVATE, Modifier.FINAL)
        .let { if (publicModifier && description != null) it.addJavadoc("\$L\n", description) else it }
        .build()
  }

  fun argumentCodeBlock(): CodeBlock {
    if (args == null || args.isEmpty()) {
      return CodeBlock.builder().add("null").build()
    }
    return jsonMapToCodeBlock(args.fold(HashMap<String, Any>(), { map, arg ->
      map.put(arg["name"].toString(), arg["value"]!!)
      return@fold map
    }))
  }

  private fun jsonMapToCodeBlock(jsonMap: Map<String, Any?>): CodeBlock {
    return jsonMap.entries.map { entry ->
      val codeBuilder = CodeBlock.builder()
      if (entry.value is Map<*, *>) {
        @Suppress("UNCHECKED_CAST")
        codeBuilder.add(".put(\$S, ", entry.key).add("\$L)\n", jsonMapToCodeBlock(entry.value as Map<String, Any?>))
      } else {
        codeBuilder.add(".put(\$S, \$S)\n", entry.key, entry.value).build()
      }
      codeBuilder.build()
    }.fold(CodeBlock.builder().add("new \$T(\$L)\n",
        ClassNames.parameterizedUnmodifiableMapBuilderOf(String::class.java, Any::class.java),
        jsonMap.size
    ).indent(), CodeBlock.Builder::add)
        .unindent()
        .add(".build()").build()
  }

  private fun toTypeName(responseType: String, context: CodeGenerationContext): TypeName {
    val packageName = if (isNonScalar()) "" else context.typesPackage
    return JavaTypeResolver(context, packageName).resolve(responseType, isOptional())
  }

  fun formatClassName() = responseName.capitalize().let { if (isList()) it.singularize() else it }

  private fun methodResponseType(): String {
    if (isNonScalar() || hasFragments()) {
      // For non scalar fields, we use the responseName as the method return type.
      // However, we need to also encode any extra information from the `type` field
      // eg, [lists], nonNulls!, [[nestedLists]], [nonNullLists]!, etc
      val normalizedName = formatClassName()
      if (type.startsWith("[")) {
        // array type
        return if (type.endsWith("!")) "[$normalizedName]!" else "[$normalizedName]"
      } else if (type.endsWith("!")) {
        // non-null type
        return "$normalizedName!"
      } else {
        // nullable type
        return normalizedName
      }
    } else {
      return type
    }
  }

  fun isNonScalar() = hasFragments() || (fields?.any() ?: false)

  fun hasFragments() = (fragmentSpreads?.any() ?: false) || (inlineFragments?.any() ?: false)

  fun isOptional(): Boolean = isConditional || !methodResponseType().endsWith("!")

  fun isList(): Boolean = type.removeSuffix("!").let { it.startsWith('[') && it.endsWith(']') }
}
