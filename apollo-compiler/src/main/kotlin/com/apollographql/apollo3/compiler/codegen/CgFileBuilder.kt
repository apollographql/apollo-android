package com.apollographql.apollo3.compiler.codegen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec

/**
 * KotlinPoet [FileSpec] are non qualified. This is a simple wrapper that carries a package name so that we can write the file
 */
class CgFile(
    val packageName: String,
    val typeSpecs: List<TypeSpec> = emptyList(),
    val fileName: String,
    val propertySpecs: List<PropertySpec> = emptyList()
) {
  constructor(packageName: String, typeSpec: TypeSpec) : this(packageName, listOf(typeSpec), typeSpec.name!!)
}

interface CgFileBuilder {
  fun prepare()
  fun build(): CgFile
}