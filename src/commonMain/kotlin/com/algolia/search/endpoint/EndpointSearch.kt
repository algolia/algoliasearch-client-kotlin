package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.FacetHits
import com.algolia.search.model.search.SearchResponse


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