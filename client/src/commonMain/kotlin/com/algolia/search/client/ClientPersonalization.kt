@file:Suppress("FunctionName")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientPersonalizationImpl
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
public interface ClientPersonalization : EndpointRecommendation, Configuration, Credentials {

    public companion object
}

/**
 * Create a [ClientPersonalization] instance.
 *
 * @param applicationID application ID
 * @param apiKey API Key
 * @param region personalization region
 */
public fun ClientPersonalization(
    applicationID: ApplicationID,
    apiKey: APIKey,
    region: Region.Recommendation,
): ClientPersonalization = ClientPersonalizationImpl(
    Transport(
        ConfigurationRecommendation(applicationID, apiKey, region),
        Credentials(applicationID, apiKey)
    )
)

/**
 * Create a [ClientSearch] instance.
 *
 * @param configuration personalization configuration
 */
public fun ClientPersonalization(
    configuration: ConfigurationRecommendation,
): ClientPersonalization = ClientPersonalizationImpl((Transport(configuration, configuration)))

/**
 * Client for the personalization API.
 */
@Deprecated("use ClientPersonalization instead", replaceWith = ReplaceWith("ClientPersonalization"))
public typealias ClientRecommendation = ClientPersonalization
