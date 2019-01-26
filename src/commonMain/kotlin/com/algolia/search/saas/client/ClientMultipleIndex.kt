package com.algolia.search.saas.client

import com.algolia.search.saas.model.*
import com.algolia.search.saas.model.search.SearchResponse
import com.algolia.search.saas.endpoint.EndpointMultipleIndex
import com.algolia.search.saas.model.indexing.TaskBatchOperations
import com.algolia.search.saas.query.clone
import com.algolia.search.saas.serialize.KeyRequests
import com.algolia.search.saas.serialize.KeyResults
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class ClientMultipleIndex(
    client: Client
) : EndpointMultipleIndex,
    Client by client {

    override suspend fun listIndexes(requestOptions: RequestOptions?): ListIndexes {
        return read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
            httpClient.get<ListIndexes>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy,
        requestOptions: RequestOptions?
    ): List<SearchResponse> {
        val copies = queries.map { IndexQuery(it.indexName, it.query.clone()) }

        return read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/queries") { path ->
            httpClient.post<JsonObject>(path) {
                setRequestOptions(requestOptions)
                setQueries(copies, strategy)
            }
        }.getArrayOrNull(KeyResults)?.let { Json.plain.fromJson(SearchResponse.serializer().list, it) } ?: listOf()
    }

    override suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions?
    ): List<JsonObject?> {
        val json = json { KeyRequests to Json.plain.toJson(RequestObjects.list, requests) }

        return read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/objects") { path ->
            httpClient.post<JsonObject>(path) {
                setRequestOptions(requestOptions)
                body = json.toString()
            }.let {
                it.jsonObject.getArray(KeyResults).map { if (!it.isNull) it.jsonObject else null }
            }
        }
    }

    override suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions?
    ): TaskBatchOperations {
        val requests = Json.plain.toJson(BatchOperationIndex.list, operations)
        val json = json { KeyRequests to requests }

        return write.retry(requestOptions.computedWriteTimeout, "/1/indexes/*/batch") { path ->
            httpClient.post<TaskBatchOperations>(path) {
                setRequestOptions(requestOptions)
                body = json.toString()
            }
        }
    }
}