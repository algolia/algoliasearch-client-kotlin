package com.algolia.search.model.rule

import kotlinx.serialization.Serializable

@Serializable
public data class FacetOrdering(
    public val facets: OrderingRule,
    public val facetValues: Map<String, OrderingRule>
)

