@file:Suppress("FunctionName")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientRecommendationImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationRecommendation
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.Region
import com.algolia.search.configuration.internal.Credentials
import com.algolia.search.endpoint.EndpointRecommendation
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.Transport

/**
 * Client for the recommendation API.
 */
public interface ClientRecommendation : EndpointRecommendation, Configuration, Credentials {

    public companion object
}

/**
 * Create a [ClientRecommendation] instance.
 *
 * @param applicationID application ID
 * @param apiKey API Key
 * @param region recommendation region
 */
public fun ClientRecommendation(
    applicationID: ApplicationID,
    apiKey: APIKey,
    region: Region.Recommendation,
): ClientRecommendation = ClientRecommendationImpl(
    Transport(
        ConfigurationRecommendation(applicationID, apiKey, region),
        Credentials(applicationID, apiKey)
    )
)

/**
 * Create a [ClientSearch] instance.
 *
 * @param configuration recommendation configuration
 */
public fun ClientRecommendation(
    configuration: ConfigurationRecommendation,
): ClientRecommendation = ClientRecommendationImpl((Transport(configuration, configuration)))
