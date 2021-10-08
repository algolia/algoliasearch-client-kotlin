package com.algolia.search.model.internal.request

import com.algolia.search.model.multipleindex.FacetIndexQuery
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.IndexedQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyDefault
import com.algolia.search.serialize.KeyFacet
import com.algolia.search.serialize.KeyFacetQuery
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyRequests
import com.algolia.search.serialize.KeyStrategy
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.merge
import com.algolia.search.serialize.internal.toJsonNoDefaults
import com.algolia.search.serialize.internal.urlEncode
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Serializable(RequestTypedMultipleQueries.Companion::class)
internal class RequestTypedMultipleQueries(
    @SerialName(KeyRequests) val requests: List<IndexedQuery>,
    @SerialName(KeyStrategy) val strategy: MultipleQueriesStrategy? = null
) {

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(RequestTypedMultipleQueries::class)
    companion object : SerializationStrategy<RequestTypedMultipleQueries> {

        override fun serialize(encoder: Encoder, value: RequestTypedMultipleQueries) {
            val json = buildJsonObject {
                put(
                    KeyRequests,
                    buildJsonArray {
                        value.requests.forEach {
                            add(
                                buildJsonObject {
                                    put(KeyIndexName, it.indexName.raw)
                                    when (it) {
                                        is IndexQuery -> {
                                            put(KeyType, KeyDefault)
                                            it.query.toJsonNoDefaults().urlEncode()?.let { put(KeyParams, it) }
                                        }
                                        is FacetIndexQuery -> {
                                            put(KeyType, KeyFacet)
                                            put(KeyFacet, it.facetAttribute.raw)
                                            paramsFacetQuery(it.query, it.facetQuery)?.let { put(KeyParams, it) }
                                        }
                                    }
                                }
                            )
                        }
                    }
                )
                value.strategy?.let { put(KeyStrategy, it.raw) }
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }

        private fun paramsFacetQuery(query: Query, facetQuery: String? = null): String? {
            val queryJsonObject = query.toJsonNoDefaults()
            val extras = facetQuery?.let { buildJsonObject { put(KeyFacetQuery, it) } }
            return queryJsonObject.mergeExtras(extras).urlEncode()
        }

        private fun JsonObject.mergeExtras(extras: JsonObject? = null): JsonObject {
            if (extras == null) return this
            return merge(extras)
        }
    }
}
