package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.insights.InsightsEvent
import io.ktor.client.response.HttpResponse


interface EndpointInsights {

    suspend fun sendEvent(event: InsightsEvent, requestOptions: RequestOptions? = null): HttpResponse

    suspend fun sendEvents(events: List<InsightsEvent>, requestOptions: RequestOptions? = null): HttpResponse
}