package com.algolia.search.client.internal

import com.algolia.search.client.ClientAnalytics
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.endpoint.internal.EndpointAnalytics
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport

internal class ClientAnalyticsImpl internal constructor(
    internal val transport: Transport,
) : ClientAnalytics,
    EndpointAnalytics by EndpointAnalytics(transport),
    Configuration by transport,
    Credentials by transport.credentials,
    CustomRequester by transport {

    override suspend fun browseAllABTests(
        hitsPerPage: Int?,
        requestOptions: RequestOptions?,
    ): List<ResponseABTests> {
        val responses = mutableListOf<ResponseABTests>()
        var page = 0

        while (true) {
            val response = listABTests(page, hitsPerPage, requestOptions)

            if (response.count == response.total || response.count == 0) break
            page += response.count
            responses += response
        }
        return responses
    }
}
