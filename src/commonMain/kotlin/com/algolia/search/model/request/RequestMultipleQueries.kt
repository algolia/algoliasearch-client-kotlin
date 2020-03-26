package com.algolia.search.model.request

import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyRequests
import com.algolia.search.serialize.KeyStrategy
import com.algolia.search.serialize.asJsonOutput
import com.algolia.search.serialize.toJsonNoDefaults
import com.algolia.search.serialize.urlEncode
import kotlinx.serialization.Encoder
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray

@Serializable(RequestMultipleQueries.Companion::class)
internal class RequestMultipleQueries(
    @SerialName(KeyRequests) val indexQueries: List<IndexQuery>,
    @SerialName(KeyStrategy) val strategy: MultipleQueriesStrategy? = null
) {

    @Serializer(RequestMultipleQueries::class)
    companion object : SerializationStrategy<RequestMultipleQueries> {

        override fun serialize(encoder: Encoder, obj: RequestMultipleQueries) {
            val json = json {
                KeyRequests to jsonArray {
                    obj.indexQueries.forEach {
                        +json {
                            KeyIndexName to it.indexName.raw
                            it.query.toJsonNoDefaults().urlEncode()?.let { KeyParams to it }
                        }
                    }
                }
                obj.strategy?.let { KeyStrategy to it.raw }
            }
            encoder.asJsonOutput().encodeJson(json)
        }
    }
}
