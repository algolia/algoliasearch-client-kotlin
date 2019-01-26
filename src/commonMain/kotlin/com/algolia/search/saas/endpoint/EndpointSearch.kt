package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.data.*
import com.algolia.search.saas.data.search.SearchResponse


interface EndpointSearch {

    val indexName: IndexName

    suspend fun search(query: Query? = null, requestOptions: RequestOptions? = null): SearchResponse

    suspend fun browse(query: Query? = null, requestOptions: RequestOptions? = null): SearchResponse

    suspend fun browse(cursor: Cursor, requestOptions: RequestOptions? = null): SearchResponse

    suspend fun searchForFacetValue(
        attribute: Attribute,
        facetQuery: String? = null,
        query: Query? = null,
        maxFacetHits: Int? = null,
        requestOptions: RequestOptions? = null
    ): FacetHits
}