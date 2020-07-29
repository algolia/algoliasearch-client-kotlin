package com.algolia.search.model.multipleindex

import com.algolia.search.helper.toIndexName
import com.algolia.search.model.IndexName
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
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
                Json.encodeToJsonElement(BatchOperation, value.operation).jsonObject.toMutableMap().also {
                    it[KeyIndexName] = JsonPrimitive(value.indexName.raw)
                }

            encoder.asJsonOutput().encodeJsonElement(JsonObject(elements))
        }

        override fun deserialize(decoder: Decoder): BatchOperationIndex {
            val element = decoder.asJsonInput().jsonObject
            val batchOperation = JsonNonStrict.decodeFromJsonElement(BatchOperation, element)
            val indexName = element.getValue(KeyIndexName).jsonPrimitive.content.toIndexName()

            return BatchOperationIndex(indexName, batchOperation)
        }
    }
}
