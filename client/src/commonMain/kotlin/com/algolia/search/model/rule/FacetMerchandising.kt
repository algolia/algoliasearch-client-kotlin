package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyFacetOrder
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FacetMerchandising(
    @SerialName(KeyFacetOrder) public val facetOrder: List<AttributedFacets> = emptyList(),
)
