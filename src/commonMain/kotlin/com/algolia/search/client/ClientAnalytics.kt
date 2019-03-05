package com.algolia.search.client

import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.endpoint.EndpointAnalyticsImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID


class ClientAnalytics private constructor(
    private val api: APIWrapperImpl
) : EndpointAnalytics by EndpointAnalyticsImpl(api) {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapperImpl(Configuration(applicationID, apiKey, hosts = listOf("https://analytics.algolia.com"))))

    constructor(
        configuration: Configuration
    ) : this(APIWrapperImpl(configuration))
}