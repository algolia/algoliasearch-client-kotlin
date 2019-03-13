package com.algolia.search.client

import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.endpoint.EndpointAnalyticsImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.response.ResponseABTests
import io.ktor.client.engine.HttpClientEngine


public class ClientAnalytics private constructor(
    internal val api: APIWrapperImpl
) : EndpointAnalytics by EndpointAnalyticsImpl(api),
    Configuration by api {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapperImpl(ConfigurationImpl(applicationID, apiKey, hosts = listOf("https://analytics.algolia.com"))))

    public constructor(
        configuration: ConfigurationImpl
    ) : this(APIWrapperImpl(configuration))

    public constructor(
        configuration: ConfigurationImpl,
        engine: HttpClientEngine?
    ) : this(APIWrapperImpl(configuration, engine))


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