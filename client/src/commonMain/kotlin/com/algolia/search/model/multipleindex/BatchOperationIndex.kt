package com.algolia.search.model.multipleindex

import com.algolia.search.helper.toIndexName
import com.algolia.search.model.IndexName
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

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

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(BatchOperationIndex::class)
    public companion object : KSerializer<BatchOperationIndex> {

        override fun serialize(encoder: Encoder, value: BatchOperationIndex) {
            val elements =
                Json.encodeToJsonElement(BatchOperation, value.operation).jsonObject.toMutableMap().also {
                    it[Key.IndexName] = JsonPrimitive(value.indexName.raw)
                }

            encoder.asJsonOutput().encodeJsonElement(JsonObject(elements))
        }

        override fun deserialize(decoder: Decoder): BatchOperationIndex {
            val element = decoder.asJsonInput().jsonObject
            val batchOperation = JsonNonStrict.decodeFromJsonElement(BatchOperation, element)
            val indexName = element.getValue(Key.IndexName).jsonPrimitive.content.toIndexName()

            return BatchOperationIndex(indexName, batchOperation)
        }
    }
}
