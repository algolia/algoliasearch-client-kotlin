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


@Serializable(BatchWriteIndex.Companion::class)
data class BatchWriteIndex(
    val indexName: IndexName,
    val batchWrite: BatchWrite
) {

    @Serializer(BatchWriteIndex::class)
    internal companion object : KSerializer<BatchWriteIndex> {

        override fun serialize(encoder: Encoder, obj: BatchWriteIndex) {
            val elements = Json.plain.toJson(obj.batchWrite, BatchWrite).jsonObject.content.toMutableMap().also {
                it[KeyIndexName] = JsonLiteral(obj.indexName.raw)
            }

            encoder.asJsonOutput().encodeJson(JsonObject(elements))
        }

        override fun deserialize(decoder: Decoder): BatchWriteIndex {
            val element = decoder.asJsonInput().jsonObject
            val batchWrite = Json.nonstrict.fromJson(element, BatchWrite)
            val indexName = element[KeyIndexName].content.toIndexName()

            return BatchWriteIndex(indexName, batchWrite)
        }
    }
}