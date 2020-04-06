package com.algolia.search.client

import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationRecommendation
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.CredentialsImpl
import com.algolia.search.configuration.Region
import com.algolia.search.endpoint.EndpointRecommendation
import com.algolia.search.endpoint.EndpointRecommendationImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.Transport

/**
 * Client for the recommendation API.
 */
public class ClientRecommendation private constructor(
    private val transport: Transport
) : EndpointRecommendation by EndpointRecommendationImpl(transport),
    Configuration by transport,
    Credentials by transport.credentials {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey,
        region: Region
    ) : this(
        Transport(
            ConfigurationRecommendation(applicationID, apiKey, region),
            CredentialsImpl(applicationID, apiKey)
        )
    )

    public constructor(
        configuration: ConfigurationRecommendation
    ) : this(Transport(configuration, configuration))
}
