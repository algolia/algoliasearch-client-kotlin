package com.algolia.search.model.search


public data class FacetValuesQuery(
    var facetQuery: String? = null,
    val query: Query? = null
)