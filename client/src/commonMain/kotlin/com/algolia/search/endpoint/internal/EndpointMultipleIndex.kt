@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.exception.EmptyListException
import com.algolia.search.model.internal.request.RequestRequestObjects
import com.algolia.search.model.internal.request.RequestTypedMultipleQueries
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.IndexedQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.ResponseListIndices
import com.algolia.search.model.response.ResponseMultiSearch
import com.algolia.search.model.response.ResponseObjects
import com.algolia.search.model.response.ResponseSearches
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.serialize.internal.toBody
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.buildJsonObject

internal class EndpointMultipleIndexImpl(
    private val transport: Transport,
) : EndpointMultipleIndex {

    override suspend fun listIndices(requestOptions: RequestOptions?): ResponseListIndices {
        return transport.request(HttpMethod.Get, CallType.Read, Route.IndexesV1, requestOptions)
    }

    override suspend fun listIndexAPIKeys(requestOptions: RequestOptions?): ResponseListAPIKey {
        return transport.request(HttpMethod.Get, CallType.Read, "${Route.IndexesV1}/*/keys", requestOptions)
    }

    override suspend fun multipleQueries(
        queries: List<IndexQuery>,
        strategy: MultipleQueriesStrategy?,
        requestOptions: RequestOptions?,
    ): ResponseSearches {
        val body = queries.toBody(strategy)

        return transport.request(HttpMethod.Post, CallType.Read, "${Route.IndexesV1}/*/queries", requestOptions, body)
    }

    override suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions?,
    ): ResponseObjects {
        val body = JsonNoDefaults.encodeToString(RequestRequestObjects.serializer(), RequestRequestObjects(requests))

        return transport.request(HttpMethod.Post, CallType.Read, "${Route.IndexesV1}/*/objects", requestOptions, body)
    }

    override suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions?,
    ): ResponseBatches {
        if (operations.isEmpty()) throw EmptyListException("operations")
        val requests = Json.encodeToJsonElement(ListSerializer(BatchOperationIndex), operations)
        val body = buildJsonObject { put(Key.Requests, requests) }.toString()

        return transport.request(HttpMethod.Post, CallType.Write, "${Route.IndexesV1}/*/batch", requestOptions, body)
    }

    override suspend fun search(
        requests: List<IndexedQuery>,
        strategy: MultipleQueriesStrategy?,
        requestOptions: RequestOptions?
    ): ResponseMultiSearch {
        val body = JsonNoDefaults.encodeToString(
            RequestTypedMultipleQueries,
            RequestTypedMultipleQueries(requests, strategy)
        )
        return transport.request(HttpMethod.Post, CallType.Read, "${Route.IndexesV1}/*/queries", requestOptions, body)
    }
}

/**
 * Create an [EndpointMultipleIndex] instance.
 */
internal fun EndpointMultipleIndex(
    transport: Transport,
): EndpointMultipleIndex = EndpointMultipleIndexImpl(transport)
