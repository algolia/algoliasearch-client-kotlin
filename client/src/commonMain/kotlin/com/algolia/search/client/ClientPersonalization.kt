@file:Suppress("FunctionName", "DEPRECATION")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientPersonalizationImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationPersonalization
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.Region
import com.algolia.search.configuration.internal.Credentials
import com.algolia.search.endpoint.EndpointPersonalization
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.internal.Transport

/**
 * Client for the personalization API.
 */
public interface ClientPersonalization : EndpointPersonalization, Configuration, Credentials, CustomRequester {

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
