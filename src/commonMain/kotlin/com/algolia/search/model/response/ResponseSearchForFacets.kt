package com.algolia.search.model.response

import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KSerializerFacetList
import com.algolia.search.serialize.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.KeyFacetHits
import com.algolia.search.serialize.KeyProcessingTimeMS
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearchForFacets(
    /**
     * The list of [Facet].
     */
    @SerialName(KeyFacetHits) @Serializable(KSerializerFacetList::class) val facets: List<Facet>,
    /**
     * Whether the count returned for each [facets] is exhaustive.
     */
    @SerialName(KeyExhaustiveFacetsCount) val exhaustiveFacetsCount: Boolean,
    /**
     * Processing time.
     */
    @SerialName(KeyProcessingTimeMS) val processingTimeMS: Long
)