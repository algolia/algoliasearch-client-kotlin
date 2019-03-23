package com.algolia.search.client

import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationInsights
import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.endpoint.EndpointInsightsImpl
import com.algolia.search.endpoint.EndpointInsightsUser
import com.algolia.search.endpoint.EndpointInsightsUserImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.insights.UserToken
import com.algolia.search.transport.Transport


public class ClientInsights private constructor(
    private val api: Transport
) : EndpointInsights by EndpointInsightsImpl(api),
    Configuration by api {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(Transport(ConfigurationInsights(applicationID, apiKey)))

    public constructor(
        configuration: ConfigurationInsights
    ) : this(Transport(configuration))

    public inner class User(
        val userToken: UserToken
    ) : EndpointInsightsUser by EndpointInsightsUserImpl(this, userToken)
}