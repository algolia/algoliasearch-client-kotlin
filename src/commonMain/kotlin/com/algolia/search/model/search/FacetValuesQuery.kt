package com.algolia.search.model.search


public data class FacetValuesQuery(
    var facetQuery: String? = null,
    var query: Query? = null
)