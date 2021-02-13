// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.test_inline.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.test_inline.GetPage
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class GetPage_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<GetPage.Data> {
  val collectionAdapter: ResponseAdapter<GetPage.Data.Collection> = Collection(customScalarAdapters)

  override fun fromResponse(reader: JsonReader): GetPage.Data {
    var collection: GetPage.Data.Collection? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> collection = collectionAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("collection")
        else -> break
      }
    }
    reader.endObject()
    return GetPage.Data(
      collection = collection!!
    )
  }

  override fun toResponse(writer: JsonWriter, value: GetPage.Data) {
    collectionAdapter.toResponse(writer, value.collection)
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("Collection")),
        responseName = "collection",
        fieldName = "collection",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet("ParticularCollection",
              Collection.ParticularCollectionCollection.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Collection.OtherCollection.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Collection(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<GetPage.Data.Collection> {
    val particularCollectionCollectionAdapter: ParticularCollectionCollection =
        com.example.test_inline.adapter.GetPage_ResponseAdapter.Collection.ParticularCollectionCollection(customScalarAdapters)

    val otherCollectionAdapter: OtherCollection =
        com.example.test_inline.adapter.GetPage_ResponseAdapter.Collection.OtherCollection(customScalarAdapters)

    override fun fromResponse(reader: JsonReader): GetPage.Data.Collection {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "ParticularCollection" -> particularCollectionCollectionAdapter.fromResponse(reader, typename)
        else -> otherCollectionAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: GetPage.Data.Collection) {
      when(value) {
        is GetPage.Data.Collection.ParticularCollectionCollection -> particularCollectionCollectionAdapter.toResponse(writer, value)
        is GetPage.Data.Collection.OtherCollection -> otherCollectionAdapter.toResponse(writer, value)
      }
    }

    class ParticularCollectionCollection(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val itemsAdapter:
          ResponseAdapter<List<GetPage.Data.Collection.ParticularCollectionCollection.Item>> =
          ListResponseAdapter(Item(customScalarAdapters))

      fun fromResponse(reader: JsonReader, __typename: String?):
          GetPage.Data.Collection.ParticularCollectionCollection {
        var __typename: String? = __typename
        var items: List<GetPage.Data.Collection.ParticularCollectionCollection.Item>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> items = itemsAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("items")
            else -> break
          }
        }
        return GetPage.Data.Collection.ParticularCollectionCollection(
          __typename = __typename!!,
          items = items!!
        )
      }

      fun toResponse(writer: JsonWriter,
          value: GetPage.Data.Collection.ParticularCollectionCollection) {
        __typenameAdapter.toResponse(writer, value.__typename)
        itemsAdapter.toResponse(writer, value.items)
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "__typename",
            fieldName = "__typename",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type =
                ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Object("Item")))),
            responseName = "items",
            fieldName = "items",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet("ParticularItem", Item.ParticularItemItem.RESPONSE_FIELDS),
              ResponseField.FieldSet(null, Item.OtherItem.RESPONSE_FIELDS),
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Item(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<GetPage.Data.Collection.ParticularCollectionCollection.Item> {
        val particularItemItemAdapter: ParticularItemItem =
            com.example.test_inline.adapter.GetPage_ResponseAdapter.Collection.ParticularCollectionCollection.Item.ParticularItemItem(customScalarAdapters)

        val otherItemAdapter: OtherItem =
            com.example.test_inline.adapter.GetPage_ResponseAdapter.Collection.ParticularCollectionCollection.Item.OtherItem(customScalarAdapters)

        override fun fromResponse(reader: JsonReader):
            GetPage.Data.Collection.ParticularCollectionCollection.Item {
          reader.beginObject()
          check(reader.nextName() == "__typename")
          val typename = reader.nextString()

          return when(typename) {
            "ParticularItem" -> particularItemItemAdapter.fromResponse(reader, typename)
            else -> otherItemAdapter.fromResponse(reader, typename)
          }
          .also { reader.endObject() }
        }

        override fun toResponse(writer: JsonWriter,
            value: GetPage.Data.Collection.ParticularCollectionCollection.Item) {
          when(value) {
            is GetPage.Data.Collection.ParticularCollectionCollection.Item.ParticularItemItem -> particularItemItemAdapter.toResponse(writer, value)
            is GetPage.Data.Collection.ParticularCollectionCollection.Item.OtherItem -> otherItemAdapter.toResponse(writer, value)
          }
        }

        class ParticularItemItem(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val titleAdapter: ResponseAdapter<String> = stringResponseAdapter

          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          val imageAdapter: ResponseAdapter<String> = stringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              GetPage.Data.Collection.ParticularCollectionCollection.Item.ParticularItemItem {
            var title: String? = null
            var __typename: String? = __typename
            var image: String? = null
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> title = titleAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("title")
                1 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                2 -> image = imageAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("image")
                else -> break
              }
            }
            return GetPage.Data.Collection.ParticularCollectionCollection.Item.ParticularItemItem(
              title = title!!,
              __typename = __typename!!,
              image = image!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: GetPage.Data.Collection.ParticularCollectionCollection.Item.ParticularItemItem) {
            titleAdapter.toResponse(writer, value.title)
            __typenameAdapter.toResponse(writer, value.__typename)
            imageAdapter.toResponse(writer, value.image)
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                responseName = "title",
                fieldName = "title",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = emptyList(),
              ),
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                responseName = "__typename",
                fieldName = "__typename",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = emptyList(),
              ),
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                responseName = "image",
                fieldName = "image",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = emptyList(),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }

        class OtherItem(
          customScalarAdapters: CustomScalarAdapters
        ) {
          val titleAdapter: ResponseAdapter<String> = stringResponseAdapter

          val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

          fun fromResponse(reader: JsonReader, __typename: String?):
              GetPage.Data.Collection.ParticularCollectionCollection.Item.OtherItem {
            var title: String? = null
            var __typename: String? = __typename
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> title = titleAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("title")
                1 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                    UnexpectedNullValue("__typename")
                else -> break
              }
            }
            return GetPage.Data.Collection.ParticularCollectionCollection.Item.OtherItem(
              title = title!!,
              __typename = __typename!!
            )
          }

          fun toResponse(writer: JsonWriter,
              value: GetPage.Data.Collection.ParticularCollectionCollection.Item.OtherItem) {
            titleAdapter.toResponse(writer, value.title)
            __typenameAdapter.toResponse(writer, value.__typename)
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                responseName = "title",
                fieldName = "title",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = emptyList(),
              ),
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                responseName = "__typename",
                fieldName = "__typename",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = emptyList(),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }
        }
      }
    }

    class OtherCollection(
      customScalarAdapters: CustomScalarAdapters
    ) {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val itemsAdapter: ResponseAdapter<List<GetPage.Data.Collection.OtherCollection.Item>> =
          ListResponseAdapter(Item(customScalarAdapters))

      fun fromResponse(reader: JsonReader, __typename: String?):
          GetPage.Data.Collection.OtherCollection {
        var __typename: String? = __typename
        var items: List<GetPage.Data.Collection.OtherCollection.Item>? = null
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> items = itemsAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("items")
            else -> break
          }
        }
        return GetPage.Data.Collection.OtherCollection(
          __typename = __typename!!,
          items = items!!
        )
      }

      fun toResponse(writer: JsonWriter, value: GetPage.Data.Collection.OtherCollection) {
        __typenameAdapter.toResponse(writer, value.__typename)
        itemsAdapter.toResponse(writer, value.items)
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "__typename",
            fieldName = "__typename",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type =
                ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Object("Item")))),
            responseName = "items",
            fieldName = "items",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet(null, Item.RESPONSE_FIELDS)
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Item(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<GetPage.Data.Collection.OtherCollection.Item> {
        val titleAdapter: ResponseAdapter<String> = stringResponseAdapter

        override fun fromResponse(reader: JsonReader):
            GetPage.Data.Collection.OtherCollection.Item {
          var title: String? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> title = titleAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("title")
              else -> break
            }
          }
          reader.endObject()
          return GetPage.Data.Collection.OtherCollection.Item(
            title = title!!
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: GetPage.Data.Collection.OtherCollection.Item) {
          titleAdapter.toResponse(writer, value.title)
        }

        companion object {
          val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField(
              type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
              responseName = "title",
              fieldName = "title",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            )
          )

          val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
        }
      }
    }
  }
}
