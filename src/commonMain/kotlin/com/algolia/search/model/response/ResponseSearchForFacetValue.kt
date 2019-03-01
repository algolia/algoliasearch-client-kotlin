package com.algolia.search.model.response

import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KSerializerFacetList
import com.algolia.search.serialize.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.KeyFacetHits
import com.algolia.search.serialize.KeyProcessingTimeMS
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearchForFacetValue(
    @SerialName(KeyFacetHits) @Serializable(KSerializerFacetList::class) val facets: List<Facet>,
    @SerialName(KeyExhaustiveFacetsCount) val exhaustiveFacetsCount: Boolean,
    @SerialName(KeyProcessingTimeMS) val processingTimeMS: Long
)