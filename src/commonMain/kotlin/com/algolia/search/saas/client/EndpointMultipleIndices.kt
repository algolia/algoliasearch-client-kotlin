package com.algolia.search.saas.client

import com.algolia.search.saas.data.BatchWriteIndex
import com.algolia.search.saas.data.ListIndexes
import com.algolia.search.saas.data.RequestObjects
import com.algolia.search.saas.data.TaskBatchWriteIndex
import kotlinx.serialization.json.JsonObject


interface EndpointMultipleIndices {

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ListIndexes

    suspend fun getObjects(
        request: RequestObjects,
        vararg additionalRequests: RequestObjects,
        requestOptions: RequestOptions? = null
    ): List<JsonObject>

    suspend fun batchWrite(
        batchWrite: BatchWriteIndex,
        vararg additionalBatchWrites: BatchWriteIndex,
        requestOptions: RequestOptions? = null
    ): TaskBatchWriteIndex
}