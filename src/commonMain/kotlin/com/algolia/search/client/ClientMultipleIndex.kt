package com.algolia.search.client

import com.algolia.search.apikey.APIKeyResponse
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.model.multipleindex.*
import com.algolia.search.query.clone
import com.algolia.search.serialize.KeyRequests
import com.algolia.search.serialize.KeyResults
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

    override suspend fun listIndexes(requestOptions: RequestOptions?): MultipleIndexResponse.GetList {
        return read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
            httpClient.get<MultipleIndexResponse.GetList>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy,
        requestOptions: RequestOptions?
    ): MultipleIndexResponse.Search {
        val copies = queries.map {
            IndexQuery(
                it.indexName,
                it.query.clone()
            )
        }

        return read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/queries") { path ->
            httpClient.post<MultipleIndexResponse.Search>(path) {
                setRequestOptions(requestOptions)
                setQueries(copies, strategy)
            }
        }
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
    ): MultipleIndexResponse.Batch {
        val requests = Json.plain.toJson(BatchOperationIndex.list, operations)
        val json = json { KeyRequests to requests }

        return write.retry(requestOptions.computedWriteTimeout, "/1/indexes/*/batch") { path ->
            httpClient.post<MultipleIndexResponse.Batch>(path) {
                setRequestOptions(requestOptions)
                body = json.toString()
            }
        }
    }

    override suspend fun listIndexAPIKeys(): APIKeyResponse.GetList {
        return read.retry(readTimeout, "/1/indexes/*/keys") { path ->
            httpClient.get<APIKeyResponse.GetList>(path)
        }
    }
}