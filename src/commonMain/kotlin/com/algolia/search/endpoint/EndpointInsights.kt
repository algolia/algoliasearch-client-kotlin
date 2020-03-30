package com.algolia.search.endpoint

import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.transport.RequestOptions
import io.ktor.client.statement.HttpResponse

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/insights/?language=kotlin]
 */
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
