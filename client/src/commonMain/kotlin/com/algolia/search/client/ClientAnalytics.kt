@file:Suppress("FunctionName")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientAnalyticsImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationAnalytics
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.Region
import com.algolia.search.configuration.internal.Credentials
import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport

/**
 * Client to manage [ABTest] for analytics purposes.
 */
public interface ClientAnalytics : EndpointAnalytics, Configuration, Credentials, CustomRequester {

    /**
     * Browse every [ABTest] on the index and return them as a list.
     *
     * @param hitsPerPage Specify the maximum number of entries to retrieve per request.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun browseAllABTests(
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null,
    ): List<ResponseABTests>

    public companion object
}

/**
 * Create a [ClientAnalytics] instance.
 *
 * @param applicationID application ID
 * @param apiKey API Key
 * @param region analytics region
 */
public fun ClientAnalytics(
    applicationID: ApplicationID,
    apiKey: APIKey,
    region: Region.Analytics,
): ClientAnalytics = ClientAnalyticsImpl(
    Transport(
        ConfigurationAnalytics(applicationID, apiKey, region),
        Credentials(applicationID, apiKey)
    )
)

/**
 * Create a [ClientAnalytics] instance.
 *
 * @param configuration analytics configuration
 */
public fun ClientAnalytics(
    configuration: ConfigurationAnalytics,
): ClientAnalytics = ClientAnalyticsImpl(Transport(configuration, configuration))
