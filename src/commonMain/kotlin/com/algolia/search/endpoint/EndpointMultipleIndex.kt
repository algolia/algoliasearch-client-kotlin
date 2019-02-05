package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.response.*


interface EndpointMultipleIndex {

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ResponseListIndexes

    suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy = MultipleQueriesStrategy.None,
        requestOptions: RequestOptions? = null
    ): ResponseSearches

    suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions? = null
    ): ResponseObjects

    suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions? = null
    ): ResponseBatches

    suspend fun listIndexAPIKeys(requestOptions: RequestOptions? = null): ResponseListAPIKey
}