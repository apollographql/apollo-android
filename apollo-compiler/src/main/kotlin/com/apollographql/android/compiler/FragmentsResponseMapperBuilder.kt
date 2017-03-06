package com.apollographql.android.compiler

import com.apollographql.android.api.graphql.FragmentResponseFieldMapper
import com.apollographql.android.api.graphql.ResponseReader
import com.apollographql.android.compiler.ir.CodeGenerationContext
import com.apollographql.android.compiler.ir.Fragment
import com.squareup.javapoet.*
import java.io.IOException
import javax.annotation.Nonnull
import javax.lang.model.element.Modifier

/**
 * Responsible for [Fragments.Mapper] class generation
 *
 * Example of generated class:
 *
 * ```
 * public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
 *   final HeroDetails.Mapper heroDetailsFieldMapper = new HeroDetails.Mapper();
 *
 *   @Override
 *   public Fragments map(ResponseReader reader, @Nonnull String conditionalType) throws IOException {
 *     HeroDetails heroDetails = null;
 *     if (conditionalType.equals(HeroDetails.TYPE_CONDITION)) {
 *       heroDetails = heroDetailsFieldMapper.map(reader);
 *     }
 *     return new Fragments(heroDetails);
 *   }
 * }
 *
 *```
 */
class FragmentsResponseMapperBuilder(
    val fragments: List<String>,
    val codeGenerationContext: CodeGenerationContext
) {
  fun build(): TypeSpec {
    val fragmentFields = fragments.map { FieldSpec.builder(fragmentType(it), it.decapitalize()).build() }
    return TypeSpec.classBuilder(Util.MAPPER_TYPE_NAME)
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
        .addSuperinterface(RESPONSE_FIELD_MAPPER_TYPE)
        .addFields(mapperFields(fragmentFields))
        .addMethod(mapMethod(fragmentFields))
        .build()
  }

  private fun fragmentType(fragmentName: String) =
      ClassName.get(codeGenerationContext.fragmentsPackage, fragmentName.capitalize())

  private fun mapMethod(fragmentFields: List<FieldSpec>) =
      MethodSpec.methodBuilder("map")
          .addModifiers(Modifier.PUBLIC)
          .addAnnotation(Override::class.java)
          .addParameter(READER_PARAM)
          .addParameter(CONDITIONAL_TYPE_PARAM)
          .addException(IOException::class.java)
          .returns(SchemaTypeSpecBuilder.FRAGMENTS_TYPE)
          .addCode(mapMethodCode(fragmentFields))
          .build()

  private fun mapMethodCode(fragmentFields: List<FieldSpec>) =
      CodeBlock.builder()
          .add(initFragmentsCode(fragmentFields))
          .add(createFragmentsCode(fragmentFields))
          .add(");\n")
          .build()

  private fun initFragmentsCode(fragmentFields: List<FieldSpec>) =
      CodeBlock.builder()
          .add(fragmentFields
              .fold(CodeBlock.builder()) { builder, field -> builder.addStatement("\$T \$N = null", field.type, field) }
              .build())
          .add(fragmentFields
              .fold(CodeBlock.builder()) { builder, field -> builder.add(initFragmentCode(field)) }
              .build())
          .build()

  private fun initFragmentCode(fragmentField: FieldSpec): CodeBlock {
    val fieldClass = fragmentField.type as ClassName
    return CodeBlock.builder()
        .beginControlFlow("if ($CONDITIONAL_TYPE_VAR.equals(\$T.${Fragment.TYPE_CONDITION_FIELD_NAME}))", fieldClass)
        .addStatement("\$N = \$L.map(\$L)", fragmentField, fieldClass.mapperFieldName(), READER_VAR)
        .endControlFlow()
        .build()
  }

  private fun createFragmentsCode(fragmentFields: List<FieldSpec>) =
      CodeBlock.builder()
          .add("return new \$L(", SchemaTypeSpecBuilder.FRAGMENTS_TYPE.withoutAnnotations())
          .add(fragmentFields
              .mapIndexed { i, fieldSpec -> CodeBlock.of("\$L\$L", if (i > 0) ", " else "", fieldSpec.name) }
              .fold(CodeBlock.builder(), CodeBlock.Builder::add)
              .build())
          .build()

  private fun mapperFields(fragments: List<FieldSpec>) =
      fragments
          .map { it.type as ClassName }
          .map {
            val mapperClassName = it.mapper()
            FieldSpec.builder(mapperClassName, it.mapperFieldName(), Modifier.FINAL)
                .initializer(CodeBlock.of("new \$T()", mapperClassName))
                .build()
          }

  companion object {
    private val API_RESPONSE_FIELD_MAPPER_TYPE = ClassName.get(FragmentResponseFieldMapper::class.java)
    private val RESPONSE_FIELD_MAPPER_TYPE = ParameterizedTypeName.get(API_RESPONSE_FIELD_MAPPER_TYPE,
        SchemaTypeSpecBuilder.FRAGMENTS_TYPE.withoutAnnotations())
    private val CONDITIONAL_TYPE_VAR = "conditionalType"
    private val CONDITIONAL_TYPE_PARAM = ParameterSpec.builder(String::class.java, CONDITIONAL_TYPE_VAR)
        .addAnnotation(Nonnull::class.java).build()
    private val READER_VAR = "reader"
    private val READER_PARAM = ParameterSpec.builder(ResponseReader::class.java, READER_VAR).build()
  }
}