package com.algolia.search.client

import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationPlaces
import com.algolia.search.configuration.CredentialsImpl
import com.algolia.search.endpoint.EndpointPlaces
import com.algolia.search.endpoint.EndpointPlacesImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.Transport

public class ClientPlaces private constructor(
    private val tranport: Transport
) : EndpointPlaces by EndpointPlacesImpl(tranport),
    Configuration by tranport {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(
        Transport(ConfigurationPlaces(), CredentialsImpl(applicationID, apiKey))
    )

    public constructor(
        configuration: ConfigurationPlaces
    ) : this(Transport(configuration, null))

    public constructor() : this(Transport(ConfigurationPlaces(), null))
}
