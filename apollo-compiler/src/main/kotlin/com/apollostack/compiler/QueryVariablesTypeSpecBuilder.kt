package com.apollostack.compiler

import com.apollostack.compiler.ir.GraphQlType
import com.apollostack.compiler.ir.Variable
import com.squareup.javapoet.*
import javax.lang.model.element.Modifier

class QueryVariablesTypeSpecBuilder(
    val variables: List<Variable>
) {
  fun build(): TypeSpec {
    return TypeSpec.classBuilder(VARIABLES_CLASS_NAME)
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
        .addDataField(CodeBlock.of(""))
        .addConstructor()
        .addVariablesAccessorMethods(variables)
        .addBuilder(variables)
        .build()
  }

  private fun TypeSpec.Builder.addDataField(initializer: CodeBlock): TypeSpec.Builder {
    return addField(FieldSpec.builder(
        JavaPoetUtils.parameterizedMap(JavaPoetUtils.STRING_CLASS_NAME, JavaPoetUtils.OBJECT_CLASS_NAME),
        VARIABLES_MAP_FIELD_NAME)
        .addModifiers(Modifier.FINAL)
        .initializer(initializer)
        .build()
    )
  }

  private fun TypeSpec.Builder.addConstructor(): TypeSpec.Builder {
    return addMethod(MethodSpec.constructorBuilder()
        .addParameter(JavaPoetUtils.parameterizedMap(JavaPoetUtils.STRING_CLASS_NAME, JavaPoetUtils.OBJECT_CLASS_NAME),
            VARIABLES_MAP_FIELD_NAME)
        .addStatement("this.\$L = \$T.unmodifiableMap(\$L)", VARIABLES_MAP_FIELD_NAME,
            JavaPoetUtils.COLLECTIONS_CLASS_NAME, VARIABLES_MAP_FIELD_NAME)
        .build()
    )
  }

  private fun TypeSpec.Builder.addVariablesAccessorMethods(variable: List<Variable>): TypeSpec.Builder {
    variable.forEach { addVariableAccessorMethod(it) }
    return this
  }

  private fun TypeSpec.Builder.addVariableAccessorMethod(variable: Variable): TypeSpec.Builder {
    val returnType = GraphQlType.resolveByName(variable.type, !variable.type.endsWith("!")).toJavaTypeName()
    return addMethod(MethodSpec.methodBuilder(variable.name)
        .addModifiers(Modifier.PUBLIC)
        .returns(returnType)
        .addStatement("return (\$T) \$L.get(\$S)", returnType.withoutAnnotations(), VARIABLES_MAP_FIELD_NAME,
            variable.name)
        .build()
    )
  }

  private fun TypeSpec.Builder.addBuilder(variables: List<Variable>): TypeSpec.Builder {
    return addType(TypeSpec.classBuilder(BUILDER_CLASS_NAME)
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
        .addDataField(CodeBlock.of("new \$T()",
            JavaPoetUtils.parameterizedHashMap(JavaPoetUtils.STRING_CLASS_NAME, JavaPoetUtils.OBJECT_CLASS_NAME)))
        .addVariableSetterMethods(variables)
        .addBuildMethod()
        .build()
    )
  }

  private fun TypeSpec.Builder.addVariableSetterMethods(variables: List<Variable>): TypeSpec.Builder {
    variables.forEach { addVariableSetterMethod(it) }
    return this
  }

  private fun TypeSpec.Builder.addVariableSetterMethod(variable: Variable): TypeSpec.Builder {
    val paramType = GraphQlType.resolveByName(variable.type, !variable.type.endsWith("!")).toJavaTypeName()
    return addMethod(MethodSpec.methodBuilder(variable.name)
        .addModifiers(Modifier.PUBLIC)
        .returns(ClassName.get("", BUILDER_CLASS_NAME))
        .addParameter(ParameterSpec.builder(paramType, variable.name).build())
        .addStatement("\$L.put(\$S, \$L)", VARIABLES_MAP_FIELD_NAME, variable.name, variable.name)
        .addStatement("return this", VARIABLES_MAP_FIELD_NAME, variable.name, variable.name)
        .build()
    )
  }

  private fun TypeSpec.Builder.addBuildMethod(): TypeSpec.Builder {
    return addMethod(MethodSpec.methodBuilder(BUILD_METHOD_NAME)
        .addModifiers(Modifier.PUBLIC)
        .returns(VARIABLES_TYPE_NAME)
        .addStatement("return new \$L(\$L)", VARIABLES_CLASS_NAME, VARIABLES_MAP_FIELD_NAME)
        .build()
    )
  }

  companion object {
    val VARIABLES_CLASS_NAME: String = "Variables"
    val VARIABLES_MAP_FIELD_NAME: String = "data"
    val BUILDER_CLASS_NAME: String = "Builder"
    val BUILD_METHOD_NAME: String = "build"
    val VARIABLES_TYPE_NAME: TypeName = ClassName.get("", VARIABLES_CLASS_NAME)
  }
}