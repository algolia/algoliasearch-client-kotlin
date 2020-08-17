package com.algolia.search.model.internal.request

import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyRequests
import com.algolia.search.serialize.KeyStrategy
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.toJsonNoDefaults
import com.algolia.search.serialize.internal.urlEncode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Serializable(RequestMultipleQueries.Companion::class)
internal class RequestMultipleQueries(
    @SerialName(KeyRequests) val indexQueries: List<IndexQuery>,
    @SerialName(KeyStrategy) val strategy: MultipleQueriesStrategy? = null
) {

    @Serializer(RequestMultipleQueries::class)
    companion object : SerializationStrategy<RequestMultipleQueries> {

        override fun serialize(encoder: Encoder, value: RequestMultipleQueries) {
            val json = buildJsonObject {
                put(
                    KeyRequests,
                    buildJsonArray {
                        value.indexQueries.forEach {
                            add(
                                buildJsonObject {
                                    put(KeyIndexName, it.indexName.raw)
                                    it.query.toJsonNoDefaults().urlEncode()?.let { put(KeyParams, it) }
                                }
                            )
                        }
                    }
                )
                value.strategy?.let { put(KeyStrategy, it.raw) }
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }
    }
}
