package com.algolia.search.model.multipleindex

import com.algolia.search.helper.toIndexName
import com.algolia.search.model.IndexName
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject


@Serializable(BatchOperationIndex.Companion::class)
public data class BatchOperationIndex(
    /**
     * [IndexName] targeted by this operation
     */
    val indexName: IndexName,
    /**
     * Type of [BatchOperation] to execute.
     */
    val operation: BatchOperation
) {

    @Serializer(BatchOperationIndex::class)
    companion object : KSerializer<BatchOperationIndex> {

        override fun serialize(encoder: Encoder, obj: BatchOperationIndex) {
            val elements =
                Json.toJson(BatchOperation, obj.operation).jsonObject.content.toMutableMap().also {
                    it[KeyIndexName] = JsonLiteral(obj.indexName.raw)
                }

            encoder.asJsonOutput().encodeJson(JsonObject(elements))
        }

        override fun deserialize(decoder: Decoder): BatchOperationIndex {
            val element = decoder.asJsonInput().jsonObject
            val batchOperation = JsonNonStrict.fromJson(BatchOperation, element)
            val indexName = element.getPrimitive(KeyIndexName).content.toIndexName()

            return BatchOperationIndex(indexName, batchOperation)
        }
    }
}