@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.internal.request.RequestInsightsEvents
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Route
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod

internal class EndpointInsightsImpl(
    private val transport: Transport,
) : EndpointInsights {

    override suspend fun sendEvent(event: InsightsEvent, requestOptions: RequestOptions?): HttpResponse {
        return sendEvents(listOf(event), requestOptions)
    }

    override suspend fun sendEvents(events: List<InsightsEvent>, requestOptions: RequestOptions?): HttpResponse {
        val body = JsonNoDefaults.encodeToString(RequestInsightsEvents.serializer(), RequestInsightsEvents(events))

        return transport.request(HttpMethod.Post, CallType.Write, Route.EventsV1, requestOptions, body)
    }
}

/**
 * Create an [EndpointInsights] instance.
 */
internal fun EndpointInsights(
    transport: Transport,
): EndpointInsights = EndpointInsightsImpl(transport)
