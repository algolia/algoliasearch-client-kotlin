package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.request.RequestInsightsEvents
import com.algolia.search.serialize.RouteEventsV1
import com.algolia.search.serialize.noDefaults
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json


internal class EndpointInsightsImpl(
    private val transport: Transport
) : EndpointInsights {

    override suspend fun sendEvent(event: InsightsEvent, requestOptions: RequestOptions?): HttpResponse {
        return sendEvents(listOf(event))
    }

    override suspend fun sendEvents(events: List<InsightsEvent>, requestOptions: RequestOptions?): HttpResponse {
        val body = Json.noDefaults.stringify(RequestInsightsEvents.serializer(), RequestInsightsEvents(events))

        return transport.request(HttpMethod.Post, CallType.Write, RouteEventsV1, requestOptions, body)
    }
}