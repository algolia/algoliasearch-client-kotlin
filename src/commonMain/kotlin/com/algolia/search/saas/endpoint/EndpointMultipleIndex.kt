package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.data.*
import kotlinx.serialization.json.JsonObject


interface EndpointMultipleIndex {

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ListIndexes

    suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy = MultipleQueriesStrategy.None,
        requestOptions: RequestOptions? = null
    ): MultipleHits

    suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions? = null
    ): List<JsonObject?>

    suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperations
}