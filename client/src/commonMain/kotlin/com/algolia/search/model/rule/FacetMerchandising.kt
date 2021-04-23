package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyFacetOrdering
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FacetMerchandising(
    @SerialName(KeyFacetOrdering) public val facetOrdering: FacetOrdering,
)
