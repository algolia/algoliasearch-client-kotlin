package com.algolia.search.client

import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationAnalytics
import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.endpoint.EndpointAnalyticsImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport


/**
 * Client to manage [ABTest] for analytics purposes.
 */
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

    /**
     * Browse every [ABTest] on the index and return them as a list.
     *
     * @param hitsPerPage Specify the maximum number of entries to retrieve per request.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun browseAllABTests(
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): List<ResponseABTests> {
        val responses = mutableListOf<ResponseABTests>()
        var page = 0

        while (true) {
            val response = listABTests(page++, hitsPerPage, requestOptions)

            if (response.count == 0) break
            responses += response
        }
        return responses
    }
}