package com.algolia.search.client

import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.endpoint.EndpointAnalyticsImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import io.ktor.client.engine.HttpClientEngine


public class ClientAnalytics private constructor(
    internal val api: APIWrapperImpl
) : EndpointAnalytics by EndpointAnalyticsImpl(api),
    ConfigurationInterface by api {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapperImpl(Configuration(applicationID, apiKey, hosts = listOf("https://analytics.algolia.com"))))

    public constructor(
        configuration: Configuration
    ) : this(APIWrapperImpl(configuration))

    public constructor(
        configuration: Configuration,
        engine: HttpClientEngine?
    ) : this(APIWrapperImpl(configuration, engine))
}