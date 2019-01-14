package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.data.BatchOperationIndex
import com.algolia.search.saas.data.ListIndexes
import com.algolia.search.saas.data.RequestObjects
import com.algolia.search.saas.data.TaskBatchOperations
import kotlinx.serialization.json.JsonObject


interface EndpointMultipleIndices {

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ListIndexes

    suspend fun getObjects(
        request: RequestObjects,
        vararg additionalRequests: RequestObjects,
        requestOptions: RequestOptions? = null
    ): List<JsonObject?>

    suspend fun batch(
        batchOperation: BatchOperationIndex,
        vararg additionalBatchOperations: BatchOperationIndex,
        requestOptions: RequestOptions? = null
    ): TaskBatchOperations
}