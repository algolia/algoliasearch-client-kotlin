package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.indexing.TaskBatchOperations
import com.algolia.search.model.multiple_index.*
import com.algolia.search.model.search.SearchResponse
import kotlinx.serialization.json.JsonObject


interface EndpointMultipleIndex {

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ListIndexes

    suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy = MultipleQueriesStrategy.None,
        requestOptions: RequestOptions? = null
    ): List<SearchResponse>

    suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions? = null
    ): List<JsonObject?>

    suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperations
}