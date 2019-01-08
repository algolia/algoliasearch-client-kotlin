package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyIndexName
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.toIndexName
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content


@Serializable(BatchOperationIndex.Companion::class)
data class BatchOperationIndex(
    val indexName: IndexName,
    val batchOperation: BatchOperation
) {

    @Serializer(BatchOperationIndex::class)
    internal companion object : KSerializer<BatchOperationIndex> {

        override fun serialize(encoder: Encoder, obj: BatchOperationIndex) {
            val elements =
                Json.plain.toJson(obj.batchOperation, BatchOperation).jsonObject.content.toMutableMap().also {
                    it[KeyIndexName] = JsonLiteral(obj.indexName.raw)
                }

            encoder.asJsonOutput().encodeJson(JsonObject(elements))
        }

        override fun deserialize(decoder: Decoder): BatchOperationIndex {
            val element = decoder.asJsonInput().jsonObject
            val batchWrite = Json.nonstrict.fromJson(element, BatchOperation)
            val indexName = element[KeyIndexName].content.toIndexName()

            return BatchOperationIndex(indexName, batchWrite)
        }
    }
}