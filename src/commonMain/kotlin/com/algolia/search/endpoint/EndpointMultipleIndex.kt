package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.multipleindex.*
import kotlinx.serialization.json.JsonObject


interface EndpointMultipleIndex {

    suspend fun listIndexes(requestOptions: RequestOptions? = null): MultipleIndexResponse.GetList

    suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy = MultipleQueriesStrategy.None,
        requestOptions: RequestOptions? = null
    ): MultipleIndexResponse.Search

    suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions? = null
    ): List<JsonObject?>

    suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions? = null
    ): MultipleIndexResponse.Batch
}