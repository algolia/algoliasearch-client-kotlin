package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.retryWrite
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.request.RequestInsightsEvents
import com.algolia.search.serialize.noDefaults
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import kotlinx.serialization.json.Json


internal class EndpointInsightsImpl(
    val api: APIWrapper
) : EndpointInsights,
    APIWrapper by api {

    private val route = "/1/events"

    override suspend fun sendEvent(event: InsightsEvent, requestOptions: RequestOptions?): HttpResponse {
        return sendEvents(listOf(event))
    }

    override suspend fun sendEvents(events: List<InsightsEvent>, requestOptions: RequestOptions?): HttpResponse {
        return retryWrite(requestOptions, route) { url ->
            httpClient.post<HttpResponse>(url) {
                body = Json.noDefaults.stringify(
                    RequestInsightsEvents.serializer(),
                    RequestInsightsEvents(events)
                )
                setRequestOptions(requestOptions)
            }
        }
    }
}