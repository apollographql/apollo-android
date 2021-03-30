package com.apollographql.apollo3.compiler.unified.codegen

import com.apollographql.apollo3.api.Fragment
import com.apollographql.apollo3.compiler.backend.codegen.makeDataClass
import com.apollographql.apollo3.compiler.unified.ClassLayout
import com.apollographql.apollo3.compiler.unified.IrNamedFragment
import com.apollographql.apollo3.compiler.unified.codegen.adapter.dataResponseAdapterTypeSpecs
import com.apollographql.apollo3.compiler.unified.codegen.adapter.inputAdapterTypeSpec
import com.apollographql.apollo3.compiler.unified.codegen.helpers.maybeAddDescription
import com.apollographql.apollo3.compiler.unified.codegen.helpers.toNamedType
import com.apollographql.apollo3.compiler.unified.codegen.helpers.toParameterSpec
import com.apollographql.apollo3.compiler.unified.codegen.helpers.typeSpecs
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName

fun IrNamedFragment.qualifiedTypeSpecs(
    layout: ClassLayout,
    generateFilterNotNull: Boolean,
    generateFragmentImplementations: Boolean,
): List<ApolloFileSpec> {
  val list = mutableListOf<ApolloFileSpec>()

  list.add(
      ApolloFileSpec(
          packageName = layout.fragmentPackageName(),
          interfaceTypeSpecs(layout),
          layout.fragmentInterfaceFileName(name)
      )
  )
  if (generateFragmentImplementations) {
    list.add(ApolloFileSpec(layout.fragmentPackageName(), implementationTypeSpec(layout, generateFilterNotNull)))
  }
  if (variables.isNotEmpty()) {
    list.add(ApolloFileSpec(layout.fragmentAdapterPackageName(), variablesAdapterTypeSpec(layout)))
  }
  list.add(ApolloFileSpec(layout.fragmentAdapterPackageName(), responseAdapterTypeSpec(layout)))
  list.add(ApolloFileSpec(layout.fragmentResponseFieldsPackageName(), responseFieldsTypeSpec(layout)))

  return list
}

private fun IrNamedFragment.implementationTypeSpec(layout: ClassLayout, generateFilterNotNull: Boolean): TypeSpec {
  return TypeSpec.classBuilder(layout.fragmentName(name))
      .addSuperinterface(superInterfaceType(layout))
      .maybeAddDescription(description)
      .makeDataClass(variables.map { it.toNamedType().toParameterSpec(layout) })
      .addFunction(serializeVariablesFunSpec(layout))
      .addFunction(adapterFunSpec(layout))
      .addFunction(responseFieldsFunSpec(layout))
      .addTypes(dataTypeSpecs(layout)) // Fragments can have multiple data shapes
      .build()
      .maybeAddFilterNotNull(generateFilterNotNull)
}

private fun IrNamedFragment.interfaceTypeSpecs(layout: ClassLayout): List<TypeSpec> {
  return interfaceField.typeSpecs(layout, true)
}


private fun IrNamedFragment.responseFieldsFunSpec(layout: ClassLayout): FunSpec {
  return responseFieldsFunSpec(layout.fragmentResponseFieldsClassName(name))
}

private fun IrNamedFragment.variablesAdapterTypeSpec(layout: ClassLayout): TypeSpec {
  return variables.map { it.toNamedType() }
      .inputAdapterTypeSpec(
          layout = layout,
          adapterName = layout.fragmentVariablesAdapterName(name),
          adaptedTypeName = layout.fragmentImplementationClassName(name)
      )
}

private fun IrNamedFragment.responseAdapterTypeSpec(layout: ClassLayout): TypeSpec {
  return TypeSpec.objectBuilder(layout.fragmentResponseAdapterWrapperName(name))
      .addTypes(dataResponseAdapterTypeSpecs(layout, dataField))
      .build()
}

private fun IrNamedFragment.responseFieldsTypeSpec(layout:ClassLayout): TypeSpec {
  return dataResponseFieldsItemSpec(layout.fragmentResponseFieldsName(name), dataField)
}

private fun IrNamedFragment.serializeVariablesFunSpec(layout: ClassLayout): FunSpec = serializeVariablesFunSpec(
    adapterPackageName = layout.fragmentAdapterPackageName(),
    adapterName = layout.fragmentVariablesAdapterName(name),
    isEmpty = variables.isEmpty(),
    emptyMessage = "// This fragment doesn't have variables",
)

private fun IrNamedFragment.adapterFunSpec(layout: ClassLayout): FunSpec {
  check(dataField.typeFieldSet != null) // data is always a compound type

  return adapterFunSpec(
      adapterTypeName = layout.fieldSetAdapterClassName(dataField.typeFieldSet),
      adaptedTypeName = layout.fieldSetClassName(dataField.typeFieldSet)
  )
}

private fun IrNamedFragment.dataTypeSpecs(layout: ClassLayout): List<TypeSpec> {
  return dataField.typeSpecs(layout, false).map {
    it.toBuilder()
        .addSuperinterface(Fragment.Data::class)
        .build()
  }
}

private fun IrNamedFragment.superInterfaceType(layout: ClassLayout): TypeName {
  check(dataField.typeFieldSet != null) // data is always a compound type

  return Fragment::class.asTypeName().parameterizedBy(
      layout.fieldSetClassName(dataField.typeFieldSet)
  )
}
