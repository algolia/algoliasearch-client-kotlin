package com.algolia.search.model.multipleindex

import com.algolia.search.model.IndexName
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import com.algolia.search.toIndexName
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject


@Serializable(BatchOperationIndex.Companion::class)
data class BatchOperationIndex(
    val indexName: IndexName,
    val batchOperation: BatchOperation
) {

    @Serializer(BatchOperationIndex::class)
    companion object : KSerializer<BatchOperationIndex> {

        override fun serialize(encoder: Encoder, obj: BatchOperationIndex) {
            val elements =
                Json.plain.toJson(BatchOperation, obj.batchOperation).jsonObject.content.toMutableMap().also {
                    it[KeyIndexName] = JsonLiteral(obj.indexName.raw)
                }

            encoder.asJsonOutput().encodeJson(JsonObject(elements))
        }

        override fun deserialize(decoder: Decoder): BatchOperationIndex {
            val element = decoder.asJsonInput().jsonObject
            val batchOperation = Json.nonstrict.fromJson(BatchOperation, element)
            val indexName = element.getPrimitive(KeyIndexName).content.toIndexName()

            return BatchOperationIndex(indexName, batchOperation)
        }
    }
}