package com.algolia.search.model.search

import com.algolia.search.endpoint.EndpointSearch

/**
 * Query to be used with [EndpointSearch.searchForFacets].
 */
public data class FacetQuery(
    var facetQuery: String? = null,
    var query: Query = Query()
)