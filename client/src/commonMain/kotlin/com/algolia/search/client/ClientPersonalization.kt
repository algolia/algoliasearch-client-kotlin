@file:Suppress("FunctionName", "DEPRECATION")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientPersonalizationImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationPersonalization
import com.algolia.search.configuration.ConfigurationRecommendation
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.Region
import com.algolia.search.configuration.internal.Credentials
import com.algolia.search.configuration.internal.extension.toPersonalization
import com.algolia.search.endpoint.EndpointPersonalization
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.Transport

/**
 * Client for the recommendation API.
 */
public interface ClientPersonalization : EndpointPersonalization, Configuration, Credentials {

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
    region: Region.Personalization,
): ClientPersonalization = ClientPersonalizationImpl(
    Transport(
        ConfigurationPersonalization(applicationID, apiKey, region),
        Credentials(applicationID, apiKey)
    )
)

/**
 * Create a [ClientPersonalization] instance.
 *
 * @param configuration personalization configuration
 */
public fun ClientPersonalization(
    configuration: ConfigurationPersonalization,
): ClientPersonalization = ClientPersonalizationImpl((Transport(configuration, configuration)))

/**
 * Client for the personalization API.
 */
@Deprecated("use ClientPersonalization instead", replaceWith = ReplaceWith("ClientPersonalization"))
public typealias ClientRecommendation = ClientPersonalization

/**
 * Create a [ClientPersonalization] instance.
 *
 * @param applicationID application ID
 * @param apiKey API Key
 * @param region personalization region
 */
@Deprecated(
    "use ClientPersonalization instead",
    replaceWith = ReplaceWith("ClientPersonalization(applicationID, apiKey, region)")
)
public fun ClientRecommendation(
    applicationID: ApplicationID,
    apiKey: APIKey,
    region: Region.Recommendation,
): ClientRecommendation = ClientPersonalization(applicationID, apiKey, region.toPersonalization())

/**
 * Create a [ClientRecommendation] instance.
 *
 * @param configuration personalization configuration
 */
@Deprecated("use ClientPersonalization instead", replaceWith = ReplaceWith("ClientPersonalization(configuration)"))
public fun ClientRecommendation(
    configuration: ConfigurationRecommendation,
): ClientRecommendation = ClientPersonalization(configuration)
