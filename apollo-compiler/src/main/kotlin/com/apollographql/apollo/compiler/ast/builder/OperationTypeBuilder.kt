package com.apollographql.apollo.compiler.ast.builder

import com.apollographql.apollo.compiler.ast.*
import com.apollographql.apollo.compiler.escapeKotlinReservedWord
import com.apollographql.apollo.compiler.ir.Operation
import com.apollographql.apollo.internal.QueryDocumentMinifier
import com.apollographql.apollo.compiler.HashingAlgorithms

internal fun Operation.ast(
    operationClassName: String,
    context: Context,
    hashingAlgorithm: HashingAlgorithms
): OperationType {
  val dataTypeRef = context.registerObjectType(
      name = "Data",
      schemaTypeName = "",
      fragmentSpreads = emptyList(),
      inlineFragments = emptyList(),
      fields = fields,
      singularize = false,
      kind = ObjectType.Kind.Object
  )
  val operationType = when {
    isQuery() -> OperationType.Type.QUERY
    isMutation() -> OperationType.Type.MUTATION
    isSubscription() -> OperationType.Type.SUBSCRIPTION
    else -> throw IllegalArgumentException("Unsupported GraphQL operation type: $operationType")
  }
  return OperationType(
      name = operationClassName,
      type = operationType,
      operationName = operationName,
      operationId = hashingAlgorithm.encode(QueryDocumentMinifier.minify(sourceWithFragments)),
      queryDocument = sourceWithFragments,
      variables = InputType(
          name = "Variables",
          description = "",
          fields = variables.map { variable ->
            InputType.Field(
                name = variable.name.decapitalize().escapeKotlinReservedWord(),
                schemaName = variable.name,
                type = resolveFieldType(
                    graphQLType = variable.type,
                    enums = context.enums,
                    customTypeMap = context.customTypeMap,
                    typesPackageName = context.typesPackageName
                ),
                isOptional = variable.optional(),
                defaultValue = null,
                description = ""
            )
          }
      ),
      data = dataTypeRef,
      nestedObjects = context,
      filePath = filePath
  )
}
