package com.algolia.search.model.request

import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(RequestMultipleQueries.Companion::class)
internal class RequestMultipleQueries(
    @SerialName(KeyRequests) val indexQueries: List<IndexQuery>,
    @SerialName(KeyStrategy) val strategy: MultipleQueriesStrategy
) {

    @Serializer(RequestMultipleQueries::class)
    internal companion object : SerializationStrategy<RequestMultipleQueries> {

        override fun serialize(encoder: Encoder, obj: RequestMultipleQueries) {
            val json = json {
                KeyRequests to jsonArray {
                    obj.indexQueries.forEach {
                        +json {
                            KeyIndexName to it.indexName.raw
                            KeyParams to it.query.toJsonNoDefaults().urlEncode()
                        }
                    }
                }
                KeyStrategy to obj.strategy.raw
            }
            encoder.asJsonOutput().encodeJson(json)
        }
    }
}