package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.request.RequestRequestObjects
import com.algolia.search.model.response.*
import com.algolia.search.serialize.KeyRequests
import com.algolia.search.serialize.RouteIndexesV1
import com.algolia.search.serialize.noDefaults
import com.algolia.search.serialize.toBody
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class EndpointMultipleIndexImpl(
    private val transport: Transport
) : EndpointMultipleIndex {

    override suspend fun listIndices(requestOptions: RequestOptions?): ResponseListIndices {
        return transport.request(HttpMethod.Get, CallType.Read, RouteIndexesV1, requestOptions)
    }

    override suspend fun listIndexAPIKeys(requestOptions: RequestOptions?): ResponseListAPIKey {
        return transport.request(HttpMethod.Get, CallType.Read, "$RouteIndexesV1/*/keys", requestOptions)
    }

    override suspend fun multipleQueries(
        queries: List<IndexQuery>,
        strategy: MultipleQueriesStrategy,
        requestOptions: RequestOptions?
    ): ResponseSearches {
        val body = queries.toBody(strategy)

        return transport.request(HttpMethod.Post, CallType.Read, "$RouteIndexesV1/*/queries", requestOptions, body)
    }

    override suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions?
    ): ResponseObjects {
        val body = Json.noDefaults.stringify(RequestRequestObjects.serializer(), RequestRequestObjects(requests))

        return transport.request(HttpMethod.Post, CallType.Read, "$RouteIndexesV1/*/objects", requestOptions, body)
    }

    override suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions?
    ): ResponseBatches {
        val requests = Json.plain.toJson(BatchOperationIndex.list, operations)
        val body = json { KeyRequests to requests }.toString()

        return transport.request(HttpMethod.Post, CallType.Write, "$RouteIndexesV1/*/batch", requestOptions, body)
    }
}