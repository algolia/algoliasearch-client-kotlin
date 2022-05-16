package com.algolia.search.model.internal.request

import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.toJsonNoDefaults
import com.algolia.search.serialize.internal.urlEncode
import kotlinx.serialization.ExperimentalSerializationApi
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
    @SerialName(Key.Requests) val indexQueries: List<IndexQuery>,
    @SerialName(Key.Strategy) val strategy: MultipleQueriesStrategy? = null
) {

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(RequestMultipleQueries::class)
    companion object : SerializationStrategy<RequestMultipleQueries> {

        override fun serialize(encoder: Encoder, value: RequestMultipleQueries) {
            val json = buildJsonObject {
                put(
                    Key.Requests,
                    buildJsonArray {
                        value.indexQueries.forEach {
                            add(
                                buildJsonObject {
                                    put(Key.IndexName, it.indexName.raw)
                                    it.query.toJsonNoDefaults().urlEncode()?.let { put(Key.Params, it) }
                                }
                            )
                        }
                    }
                )
                value.strategy?.let { put(Key.Strategy, it.raw) }
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }
    }
}
