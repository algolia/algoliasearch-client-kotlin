package com.algolia.search.saas.client

import com.algolia.search.saas.data.*


interface EndpointsSearch {

    val indexName: IndexName

    suspend fun search(requestOptions: RequestOptions? = null): Hits

    suspend fun search(query: Query? = null, requestOptions: RequestOptions? = null): Hits

    suspend fun browse(query: Query? = null, requestOptions: RequestOptions? = null): Hits

    suspend fun browse(cursor: String, requestOptions: RequestOptions? = null): Hits

    suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy = MultipleQueriesStrategy.None,
        requestOptions: RequestOptions? = null
    ): MultipleHits

    suspend fun searchForFacetValue(
        attribute: Attribute,
        query: Query? = null,
        facetQuery: String? = null,
        maxFacetHits: Int? = null,
        requestOptions: RequestOptions? = null
    ): FacetHits
}