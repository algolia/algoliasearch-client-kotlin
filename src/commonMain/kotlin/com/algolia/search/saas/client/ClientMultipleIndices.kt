package com.algolia.search.saas.client

import com.algolia.search.saas.data.BatchOperationIndex
import com.algolia.search.saas.data.ListIndexes
import com.algolia.search.saas.data.RequestObjects
import com.algolia.search.saas.data.TaskBatchOperations
import com.algolia.search.saas.serialize.KeyRequests
import com.algolia.search.saas.serialize.KeyResults
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class ClientMultipleIndices(
    client: Client
) : EndpointMultipleIndices,
    Client by client {

    override suspend fun listIndexes(requestOptions: RequestOptions?): ListIndexes {
        return read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
            httpClient.get<ListIndexes>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getObjects(
        request: RequestObjects,
        vararg additionalRequests: RequestObjects,
        requestOptions: RequestOptions?
    ): List<JsonObject?> {
        val requests = Json.plain.toJson(listOf(request) + additionalRequests, RequestObjects.list)
        val json = json { KeyRequests to requests }

        return read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/objects") { path ->
            httpClient.post<JsonObject>(path) {
                setRequestOptions(requestOptions)
                body = json.toString()
            }.let {
                it.jsonObject.getArray(KeyResults).map { if (!it.isNull) it.jsonObject else null }
            }
        }
    }

    override suspend fun batch(
        batchOperation: BatchOperationIndex,
        vararg additionalBatchOperations: BatchOperationIndex,
        requestOptions: RequestOptions?
    ): TaskBatchOperations {
        val operations = (listOf(batchOperation) + additionalBatchOperations)
        val requests = Json.plain.toJson(operations, BatchOperationIndex.list)
        val json = json { KeyRequests to requests }

        return write.retry(requestOptions.computedWriteTimeout, "/1/indexes/*/batch") { path ->
            httpClient.post<TaskBatchOperations>(path) {
                setRequestOptions(requestOptions)
                body = json.toString()
            }
        }
    }
}