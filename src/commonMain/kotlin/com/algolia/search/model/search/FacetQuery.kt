package com.algolia.search.model.search


public data class FacetQuery(
    var facetQuery: String? = null,
    var query: Query = Query()
)