@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.exception.EmptyListException
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.request.RequestRequestObjects
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.ResponseListIndices
import com.algolia.search.model.response.ResponseObjects
import com.algolia.search.model.response.ResponseSearches
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KeyRequests
import com.algolia.search.serialize.RouteIndexesV1
import com.algolia.search.serialize.toBody
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.buildJsonObject

internal class EndpointMultipleIndexImpl(
    private val transport: Transport,
) : EndpointMultipleIndex {

    override suspend fun listIndices(requestOptions: RequestOptions?): ResponseListIndices {
        return transport.request(HttpMethod.Get, CallType.Read, RouteIndexesV1, requestOptions)
    }

    override suspend fun listIndexAPIKeys(requestOptions: RequestOptions?): ResponseListAPIKey {
        return transport.request(HttpMethod.Get, CallType.Read, "$RouteIndexesV1/*/keys", requestOptions)
    }

    override suspend fun multipleQueries(
        queries: List<IndexQuery>,
        strategy: MultipleQueriesStrategy?,
        requestOptions: RequestOptions?,
    ): ResponseSearches {
        val body = queries.toBody(strategy)

        return transport.request(HttpMethod.Post, CallType.Read, "$RouteIndexesV1/*/queries", requestOptions, body)
    }

    override suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions?,
    ): ResponseObjects {
        val body = JsonNoDefaults.encodeToString(RequestRequestObjects.serializer(), RequestRequestObjects(requests))

        return transport.request(HttpMethod.Post, CallType.Read, "$RouteIndexesV1/*/objects", requestOptions, body)
    }

    override suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions?,
    ): ResponseBatches {
        if (operations.isEmpty()) throw EmptyListException("operations")
        val requests = Json.encodeToJsonElement(ListSerializer(BatchOperationIndex), operations)
        val body = buildJsonObject { put(KeyRequests, requests) }.toString()

        return transport.request(HttpMethod.Post, CallType.Write, "$RouteIndexesV1/*/batch", requestOptions, body)
    }
}

/**
 * Create an [EndpointMultipleIndex] instance.
 */
internal fun EndpointMultipleIndex(
    transport: Transport,
): EndpointMultipleIndex = EndpointMultipleIndexImpl(transport)
