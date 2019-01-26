package com.algolia.search.saas.model.search

import kotlinx.serialization.Serializable


@Serializable
data class FacetHits(
    val facetHits: List<Hit>,
    val exhaustiveFacetsCount: Boolean,
    val processingTimeMS: Long
) {

    @Serializable
    data class Hit(
        val value: String,
        val highlighted: String,
        val count: Int
    )
}