package com.algolia.search.model.response

import com.algolia.search.model.multipleindex.IndexedQuery
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonDecoder
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

/**
 * Response for multi search operation.
 */
@Serializable
public data class ResponseMultiSearch(
    /**
     * List of result in the order they were submitted, one element for each [IndexedQuery].
     */
    @SerialName(Key.Results) public val results: List<ResultMultiSearch<*>>
)

/**
 * Multi search query response.
 */
@Serializable(ResultMultiSearchDeserializer::class)
public sealed interface ResultMultiSearch<T : ResultSearch> {

    /** Actual search response */
    public val response: T

    /** Response for hits search */
    public data class Hits(override val response: ResponseSearch) : ResultMultiSearch<ResponseSearch>

    /** Response for facets search */
    public data class Facets(override val response: ResponseSearchForFacets) :
        ResultMultiSearch<ResponseSearchForFacets>
}

/**
 * [ResultMultiSearch] serializer.
 */
internal class ResultMultiSearchDeserializer<T : ResultSearch>(dataSerializer: KSerializer<ResultSearch>) :
    KSerializer<ResultMultiSearch<T>> {

    override val descriptor = dataSerializer.descriptor

    override fun deserialize(decoder: Decoder): ResultMultiSearch<T> {
        val json = decoder.asJsonDecoder().json
        val jsonObject = decoder.asJsonInput().jsonObject
        return multiSearchResult(json, jsonObject)
    }

    @Suppress("UNCHECKED_CAST")
    private fun multiSearchResult(json: Json, jsonObject: JsonObject): ResultMultiSearch<T> {
        return if (jsonObject.keys.contains(Key.FacetHits)) {
            ResultMultiSearch.Facets(json.decodeFromJsonElement(ResponseSearchForFacets.serializer(), jsonObject))
        } else {
            ResultMultiSearch.Hits(json.decodeFromJsonElement(ResponseSearch.serializer(), jsonObject))
        } as ResultMultiSearch<T>
    }

    override fun serialize(encoder: Encoder, value: ResultMultiSearch<T>) {
        val json = encoder.asJsonOutput().json
        when (value) {
            is ResultMultiSearch.Hits -> json.encodeToString(ResponseSearch.serializer(), value.response)
            is ResultMultiSearch.Facets -> json.encodeToString(ResponseSearchForFacets.serializer(), value.response)
        }
    }
}
