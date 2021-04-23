package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyFacetValues
import com.algolia.search.serialize.KeyFacets
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FacetOrdering(
    @SerialName(KeyFacets) public val facets: OrderingRule,
    @SerialName(KeyFacetValues) public val facetValues: Map<String, OrderingRule>
)
