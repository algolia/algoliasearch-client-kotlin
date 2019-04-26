package com.algolia.search.endpoint

import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.transport.RequestOptions
import io.ktor.client.response.HttpResponse


public interface EndpointInsights {

    /**
     * Send one [InsightsEvent].
     */
    suspend fun sendEvent(event: InsightsEvent, requestOptions: RequestOptions? = null): HttpResponse

    /**
     * Send multiple [InsightsEvent].
     */
    suspend fun sendEvents(events: List<InsightsEvent>, requestOptions: RequestOptions? = null): HttpResponse
}