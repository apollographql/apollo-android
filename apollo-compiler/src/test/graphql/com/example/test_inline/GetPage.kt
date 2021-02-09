// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.test_inline

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.test_inline.adapter.GetPage_ResponseAdapter
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class GetPage : Query<GetPage.Data> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): String = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = GetPage_ResponseAdapter
  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, GetPage_ResponseAdapter.RESPONSE_FIELDS)
  )
  data class Data(
    val collection: Collection
  ) : Operation.Data {
    interface Collection {
      val __typename: String

      val items: List<Item>

      interface Item {
        val title: String
      }

      interface ParticularCollection : Collection {
        override val __typename: String

        override val items: List<Item>

        interface Item : Collection.Item {
          override val title: String

          val __typename: String

          interface ParticularItem : Item {
            override val __typename: String

            val image: String

            override val title: String
          }

          companion object {
            fun Item.asParticularItem(): ParticularItem? = this as? ParticularItem
          }
        }
      }

      data class ParticularCollectionCollection(
        override val __typename: String,
        override val items: List<Item>
      ) : Collection, ParticularCollection {
        interface Item : Collection.Item, ParticularCollection.Item {
          override val __typename: String

          data class ParticularItemItem(
            override val title: String,
            override val __typename: String,
            override val image: String
          ) : ParticularCollection.Item, ParticularCollection.Item.ParticularItem, Item

          data class OtherItem(
            override val title: String,
            override val __typename: String
          ) : Collection.Item, ParticularCollection.Item, Item
        }
      }

      data class OtherCollection(
        override val __typename: String,
        override val items: List<Item>
      ) : Collection {
        data class Item(
          override val title: String
        ) : Collection.Item
      }

      companion object {
        fun Collection.asParticularCollection(): ParticularCollection? = this as?
            ParticularCollection
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "09dd0a176a2233eccc3b2d3a76f25a1083460354f399f8b1aaf172c18cfc202b"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query GetPage {
          |  collection {
          |    __typename
          |    items {
          |      title
          |    }
          |    ... on ParticularCollection {
          |      items {
          |        __typename
          |        ... on ParticularItem {
          |          image
          |        }
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "GetPage"
  }
}
