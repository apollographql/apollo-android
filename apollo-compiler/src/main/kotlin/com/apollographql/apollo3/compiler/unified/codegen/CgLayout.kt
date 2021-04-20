package com.apollographql.apollo3.compiler.unified.codegen

import com.apollographql.apollo3.compiler.PackageNameProvider
import com.apollographql.apollo3.compiler.backend.codegen.capitalizeFirstLetter
import com.apollographql.apollo3.compiler.escapeKotlinReservedWord
import com.apollographql.apollo3.compiler.singularize
import com.apollographql.apollo3.compiler.unified.ir.IrFieldInfo
import com.apollographql.apollo3.compiler.unified.ir.IrListType
import com.apollographql.apollo3.compiler.unified.ir.IrNonNullType
import com.apollographql.apollo3.compiler.unified.ir.IrOperation
import com.apollographql.apollo3.compiler.unified.ir.IrType
import com.apollographql.apollo3.compiler.unified.ir.TypeSet

/**
 * The central place where the names/packages of the different classes are decided and escape rules done.
 *
 * Inputs should always be GraphQL identifiers and outputs are valid Kotlin identifiers.
 *
 * By default, the layout is like:
 *
 * - com.example.TestQuery <- the query
 * - com.example.TestQuery.Data <- the data for the query
 * - com.example.TestQuery.Data.Hero <- nested compound fields as required
 * - com.example.adapter.TestQuery_ResponseAdapter <- a wrapper object to namespace the adapters
 * - com.example.adapter.TestQuery_ResponseAdapter.Data <- the response adapter for TestQuery
 * - com.example.adapter.TestQuery_VariablesAdapter <- the variables adapter for TestQuery
 * - com.example.responsefields.TestQuery_ResponseFields <- the response fields for TestQuery
 *
 * - com.example.fragment.HeroDetails <- the fragment interface
 * - com.example.fragment.HeroDetailsImpl <- the fragment implementation
 * - com.example.fragment.HeroDetailsImpl.Data <- the data for the fragment implementation
 * - com.example.fragment.adapter.HeroDetailsImpl <- a wrapper object to name space the adapters
 * - com.example.fragment.adapter.HeroDetailsImpl.Data <- the response adapter for the fragment
 *
 * - com.example.type.CustomScalars <- all the custom scalars
 * - com.example.type.ReviewInput <- an input type
 * - com.example.type.Episode <- an enum
 */

class CgLayout(
    private val packageNameProvider: PackageNameProvider,
    private val typePackageName: String,
    private val useSemanticNaming: Boolean,
) {

  // ------------------------ FileNames ---------------------------------

  internal fun fragmentInterfaceFileName(name: String) = capitalizedIdentifier(name)

  // ------------------------ PackageNames ---------------------------------

  fun typePackageName() = typePackageName
  fun typeAdapterPackageName() = "$typePackageName.adapter".stripDots()

  fun operationPackageName(filePath: String) = packageNameProvider.operationPackageName(filePath)
  fun operationAdapterPackageName(filePath: String) = "${packageNameProvider.operationPackageName(filePath)}.adapter".stripDots()
  fun operationResponseFieldsPackageName(filePath: String) = "${packageNameProvider.operationPackageName(filePath)}.responsefields".stripDots()

  fun fragmentPackageName(filePath: String?): String {
    return packageNameProvider.fragmentPackageName(filePath)
  }

  fun fragmentAdapterPackageName(filePath: String?) = "${fragmentPackageName(filePath)}.adapter".stripDots()
  fun fragmentResponseFieldsPackageName(filePath: String?) = "${fragmentPackageName(filePath)}.responsefields".stripDots()

  private fun String.stripDots() = this.removePrefix(".").removeSuffix(".")

  // ------------------------ Names ---------------------------------

  internal fun customScalarName(name: String) = capitalizedIdentifier(name)
  internal fun enumName(name: String) = regularIdentifier(name)

  // We used to write upper case enum values but the server can define different values with different cases
  // See https://github.com/apollographql/apollo-android/issues/3035
  internal fun enumValueName(name: String) = regularIdentifier(name)
  internal fun enumResponseAdapterName(name: String) = enumName(name) + "_ResponseAdapter"

  internal fun operationName(operation: IrOperation): String {
    val str = capitalizedIdentifier(operation.name)

    if (!useSemanticNaming) {
      return str
    }

    return if (str.endsWith(operation.operationType.name)) {
      str
    } else {
      "$str${operation.operationType.name}"
    }
  }

  fun operationResponseAdapterWrapperName(operation: IrOperation) = operationName(operation) + "_ResponseAdapter"
  fun operationVariablesAdapterName(operation: IrOperation) = operationName(operation) + "_VariablesAdapter"
  fun operationResponseFieldsName(operation: IrOperation) = operationName(operation) + "_ResponseFields"

  internal fun fragmentName(name: String) = capitalizedIdentifier(name) + "Impl"
  internal fun fragmentResponseAdapterWrapperName(name: String) = fragmentName(name) + "_ResponseAdapter"
  internal fun fragmentVariablesAdapterName(name: String) = fragmentName(name) + "_VariablesAdapter"
  internal fun fragmentResponseFieldsName(name: String) = fragmentName(name) + "_ResponseFields"

  internal fun inputObjectName(name: String) = capitalizedIdentifier(name)
  internal fun inputObjectAdapterName(name: String) = capitalizedIdentifier(name) + "_InputAdapter"

  // variables keep the same case as their declared name
  internal fun variableName(name: String) = regularIdentifier(name)
  internal fun propertyName(name: String) = regularIdentifier(name)
  internal fun customScalarsName() = "CustomScalars"

  // ------------------------ Helpers ---------------------------------
  private fun regularIdentifier(name: String) = name.escapeKotlinReservedWord()
  private fun upperCaseIdentifier(name: String) = name.toUpperCase().escapeKotlinReservedWord()
  private fun capitalizedIdentifier(name: String): String {
    return capitalizeFirstLetter(name).escapeKotlinReservedWord()
  }

  /**
   * A variation of [String.capitalize] that skips initial underscore, especially found in introspection queries
   *
   * There can still be name clashes if a property starts with an upper case letter
   */
  private fun capitalizeFirstLetter(name: String): String {
    val builder = StringBuilder(name.length)
    var isCapitalized = false
    name.forEach {
      builder.append(if (!isCapitalized && it.isLetter()) {
        isCapitalized = true
        it.toUpperCase()
      } else {
        it
      })
    }
    return builder.toString()
  }

  private fun decapitalizeFirstLetter(name: String): String {
    val builder = StringBuilder(name.length)
    var isDecapitalized = false
    name.forEach {
      builder.append(if (!isDecapitalized && it.isLetter()) {
        isDecapitalized = true
        it.toLowerCase()
      } else {
        it
      })
    }
    return builder.toString()
  }

  private fun isFirstLetterUpperCase(name: String): Boolean {
    return name.firstOrNull { it.isLetter() }?.isUpperCase() ?: true
  }

  companion object {
    fun upperCamelCaseIgnoringNonLetters(strings: Collection<String>): String {
      return strings.map {
        capitalizeFirstLetter(it)
      }.joinToString("")
    }

    private fun IrType.isList(): Boolean {
      return when (this) {
        is IrListType -> true
        is IrNonNullType -> ofType.isList()
        else -> false
      }
    }

    fun modelName(info: IrFieldInfo, typeSet: TypeSet, isOther: Boolean): String {
      val responseName = if (info.type.isList()) {
        info.responseName.singularize()
      } else {
        info.responseName
      }
      val name = upperCamelCaseIgnoringNonLetters((typeSet - info.rawTypeName).sorted() + responseName)

      return (if (isOther) "Other" else "") + name
    }
  }
}