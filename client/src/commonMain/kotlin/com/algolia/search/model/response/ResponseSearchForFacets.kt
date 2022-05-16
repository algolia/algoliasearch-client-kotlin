package com.algolia.search.model.response

import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KSerializerFacetList
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseSearchForFacets(
    /**
     * The list of [Facet].
     */
    @SerialName(Key.FacetHits) @Serializable(KSerializerFacetList::class) val facets: List<Facet>,
    /**
     * Whether the count returned for each [facets] is exhaustive.
     */
    @SerialName(Key.ExhaustiveFacetsCount) val exhaustiveFacetsCount: Boolean,
    /**
     * Processing time.
     */
    @SerialName(Key.ProcessingTimeMS) val processingTimeMS: Long
) : ResultSearch
