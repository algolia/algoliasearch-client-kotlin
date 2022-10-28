@file:Suppress("FunctionName")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientPlacesImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationPlaces
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.internal.Credentials
import com.algolia.search.endpoint.EndpointPlaces
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.internal.Transport

/**
 * Client for the places API.
 */
@Deprecated("Algolia Places has reached end of life.")
public interface ClientPlaces : EndpointPlaces, Configuration, CustomRequester {

    public companion object
}

/**
 * Create a [ClientPlaces] instance.
 *
 * @param applicationID application ID
 * @param apiKey API Key
 */
public fun ClientPlaces(
    applicationID: ApplicationID,
    apiKey: APIKey,
): ClientPlaces = ClientPlacesImpl(
    Transport(ConfigurationPlaces(), Credentials(applicationID, apiKey))
)

/**
 * Create a [ClientPlaces] instance.
 *
 * @param configuration places configuration
 * @param credentials used by a client for authenticated request
 */
public fun ClientPlaces(
    configuration: ConfigurationPlaces = ConfigurationPlaces(),
    credentials: Credentials? = null,
): ClientPlaces = ClientPlacesImpl(Transport(configuration, credentials))
