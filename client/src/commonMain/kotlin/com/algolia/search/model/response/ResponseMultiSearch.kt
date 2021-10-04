package com.algolia.search.model.response

import com.algolia.search.model.multipleindex.IndexedQuery
import com.algolia.search.serialize.KeyFacetHits
import com.algolia.search.serialize.KeyResults
import com.algolia.search.serialize.internal.asJsonDecoder
import com.algolia.search.serialize.internal.asJsonInput
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.jsonObject

@Serializable
public data class ResponseMultiSearch(
    /**
     * List of result in the order they were submitted, one element for each [IndexedQuery].
     */
    @SerialName(KeyResults) public val results: List<ResultMultiSearch>
)

/**
 * Query response. Can be:
 * - hits: [ResponseSearch]
 * - facets: [ResponseSearchForFacets]
 */
@Serializable(ResultMultiSearch.Companion::class)
public sealed class ResultMultiSearch {
    public data class Hits(public val response: ResponseSearch) : ResultMultiSearch()
    public data class Facets(public val response: ResponseSearchForFacets) : ResultMultiSearch()

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(ResultMultiSearch::class)
    public companion object : DeserializationStrategy<ResultMultiSearch> {

        override fun deserialize(decoder: Decoder): ResultMultiSearch {
            val json = decoder.asJsonDecoder().json
            val element = decoder.asJsonInput().jsonObject
            val hasFacetsHits = element.keys.contains(KeyFacetHits)
            return if (hasFacetsHits) {
                Facets(json.decodeFromJsonElement(ResponseSearchForFacets.serializer(), element))
            } else {
                Hits(json.decodeFromJsonElement(ResponseSearch.serializer(), element))
            }
        }
    }
}
