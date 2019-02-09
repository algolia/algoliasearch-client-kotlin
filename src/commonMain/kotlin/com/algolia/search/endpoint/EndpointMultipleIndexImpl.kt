package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setQueries
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.request.RequestRequestObjects
import com.algolia.search.model.response.*
import com.algolia.search.query.clone
import com.algolia.search.serialize.JsonNoNulls
import com.algolia.search.serialize.KeyRequests
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class EndpointMultipleIndexImpl(
    api: APIWrapper
) : EndpointMultipleIndex,
    APIWrapper by api {

    private val route = "/1/indexes"

    override suspend fun listIndexes(requestOptions: RequestOptions?): ResponseListIndexes {
        return read.retry(requestOptions.computedReadTimeout, route) { path ->
            httpClient.get<ResponseListIndexes>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy,
        requestOptions: RequestOptions?
    ): ResponseSearches {
        val copies = queries.map { IndexQuery(it.indexName, it.query.clone()) }

        return read.retry(requestOptions.computedReadTimeout, "$route/*/queries") { path ->
            httpClient.post<ResponseSearches>(path) {
                setQueries(copies, strategy)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions?
    ): ResponseObjects {
        val bodyString = JsonNoNulls.stringify(RequestRequestObjects.serializer(), RequestRequestObjects(requests))

        return read.retry(requestOptions.computedReadTimeout, "$route/*/objects") { path ->
            httpClient.post<ResponseObjects>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions?
    ): ResponseBatches {
        val requests = Json.plain.toJson(BatchOperationIndex.list, operations)
        val bodyString = json { KeyRequests to requests }.toString()

        return write.retry(requestOptions.computedWriteTimeout, "$route/*/batch") { path ->
            httpClient.post<ResponseBatches>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun listIndexAPIKeys(requestOptions: RequestOptions?): ResponseListAPIKey {
        return read.retry(readTimeout, "$route/*/keys") { path ->
            httpClient.get<ResponseListAPIKey>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }
}