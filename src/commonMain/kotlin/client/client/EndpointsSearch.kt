package client.client

import client.RequestOptions
import client.data.*


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
        facetName: String,
        query: Query? = null,
        facetQuery: String? = null,
        maxFacetHits: Int? = null,
        requestOptions: RequestOptions? = null
    ): FacetHits
}