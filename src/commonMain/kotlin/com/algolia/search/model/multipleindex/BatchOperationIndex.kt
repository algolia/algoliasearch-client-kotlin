package com.algolia.search.model.multipleindex

import com.algolia.search.helper.toIndexName
import com.algolia.search.model.IndexName
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlin.collections.set

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
    public companion object : KSerializer<BatchOperationIndex> {

        override fun serialize(encoder: Encoder, value: BatchOperationIndex) {
            val elements =
                Json.toJson(BatchOperation, value.operation).jsonObject.content.toMutableMap().also {
                    it[KeyIndexName] = JsonLiteral(value.indexName.raw)
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
