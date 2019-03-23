package com.algolia.search.client

import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationAnalytics
import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.endpoint.EndpointAnalyticsImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport

public class ClientAnalytics private constructor(
    private val api: Transport
) : EndpointAnalytics by EndpointAnalyticsImpl(api),
    Configuration by api {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(
        Transport(ConfigurationAnalytics(applicationID, apiKey))
    )

    public constructor(
        configuration: ConfigurationAnalytics
    ) : this(Transport(configuration))

    public suspend fun browseAllABTests(
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null,
        block: suspend ResponseABTests.(Int) -> Unit
    ) {
        var page = 0

        while (true) {
            val response = listABTests(page, hitsPerPage, requestOptions)

            if (response.count == 0) break
            block(response, page++)
        }
    }
}