package com.apollographql.apollo.gradle

import com.apollographql.apollo.compiler.NullableValueType
import org.gradle.api.Project
import org.gradle.api.provider.Property

class ApolloExtension {
  static final String NAME = "apollo"

  final Property<String> nullableValueType
  final Property<Boolean> useSemanticNaming
  final Property<Boolean> generateModelBuilder
  final Property<Boolean> useJavaBeansSemanticNaming
  final Property<Boolean> suppressRawTypesWarning
  final Property<Boolean> generateKotlinModels
  final Property<String> schemaFilePath
  final Property<String> outputPackageName
  final Property<Map> customTypeMapping

  ApolloExtension(Project project) {
    nullableValueType = project.getObjects().property(String.class)
    nullableValueType.set(NullableValueType.ANNOTATED.getValue())

    useSemanticNaming = project.getObjects().property(Boolean.class)
    useSemanticNaming.set(true)

    generateModelBuilder = project.getObjects().property(Boolean.class)
    generateModelBuilder.set(false)

    useJavaBeansSemanticNaming = project.getObjects().property(Boolean.class)
    useJavaBeansSemanticNaming.set(false)

    suppressRawTypesWarning = project.getObjects().property(Boolean.class)
    suppressRawTypesWarning.set(false)

    generateKotlinModels = project.getObjects().property(Boolean.class)
    generateKotlinModels.set(false)

    schemaFilePath = project.getObjects().property(String.class)
    schemaFilePath.set("")

    outputPackageName = project.getObjects().property(String.class)
    outputPackageName.set("")

    customTypeMapping = project.getObjects().property(Map.class)
    customTypeMapping.set(new LinkedHashMap())
  }

  void setNullableValueType(String nullableValueType) {
    this.nullableValueType.set(nullableValueType)
  }

  void setUseSemanticNaming(Boolean useSemanticNaming) {
    this.useSemanticNaming.set(useSemanticNaming)
  }

  void setGenerateModelBuilder(Boolean generateModelBuilder) {
    this.generateModelBuilder.set(generateModelBuilder)
  }

  void setUseJavaBeansSemanticNaming(Boolean useJavaBeansSemanticNaming) {
    this.useJavaBeansSemanticNaming.set(useJavaBeansSemanticNaming)
  }

  void setSuppressRawTypesWarning(Boolean suppressRawTypesWarning) {
    this.suppressRawTypesWarning.set(suppressRawTypesWarning)
  }

  void setGenerateKotlinModels(Boolean generateKotlinModels) {
    this.generateKotlinModels.set(generateKotlinModels)
  }

  void setSchemaFilePath(String schemaFilePath) {
    this.schemaFilePath.set(schemaFilePath)
  }

  void setOutputPackageName(String outputPackageName) {
    this.outputPackageName.set(outputPackageName)
  }

  void setCustomTypeMapping(Map customTypeMapping) {
    LinkedHashMap tmp = new LinkedHashMap()
    tmp.putAll(customTypeMapping)
    this.customTypeMapping.set(tmp)
  }
}
