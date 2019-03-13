package com.algolia.search.client

import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.endpoint.EndpointInsightsImpl
import com.algolia.search.endpoint.EndpointInsightsUser
import com.algolia.search.endpoint.EndpointInsightsUserImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.insights.UserToken
import io.ktor.client.engine.HttpClientEngine


public class ClientInsights private constructor(
    internal val api: APIWrapperImpl
) : EndpointInsights by EndpointInsightsImpl(api),
    Configuration by api {

    public constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapperImpl(ConfigurationImpl(applicationID, apiKey, hosts = listOf("https://insights.algolia.io"))))

    public constructor(
        configuration: ConfigurationImpl
    ) : this(APIWrapperImpl(configuration))

    public constructor(
        configuration: ConfigurationImpl,
        engine: HttpClientEngine?
    ) : this(APIWrapperImpl(configuration, engine))

    public inner class User(
        val userToken: UserToken
    ) : EndpointInsightsUser by EndpointInsightsUserImpl(this, userToken)
}