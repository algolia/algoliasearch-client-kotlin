package com.algolia.search.response

import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseSearchFacetValue(
    @SerialName(KeyFacetHits) val facetHits: List<Hit>,
    @SerialName(KeyExhaustiveFacetsCount) val exhaustiveFacetsCount: Boolean,
    @SerialName(KeyProcessingTimeMS) val processingTimeMS: Long
) {

    @Serializable
    data class Hit(
        @SerialName(KeyValue) val value: String,
        @SerialName(KeyHighlighted) val highlighted: String,
        @SerialName(KeyCount) val count: Int
    )
}