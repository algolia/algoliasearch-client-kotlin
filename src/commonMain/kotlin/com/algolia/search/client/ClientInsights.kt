package com.algolia.search.client

import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.endpoint.EndpointInsightsImpl
import com.algolia.search.endpoint.EndpointInsightsUser
import com.algolia.search.endpoint.EndpointInsightsUserImpl
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.insights.UserToken


class ClientInsights private constructor(
    private val api: APIWrapperImpl
) : EndpointInsights by EndpointInsightsImpl(api) {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapperImpl(Configuration(applicationID, apiKey, hosts = listOf("https://insights.algolia.io"))))

    constructor(
        configuration: Configuration
    ) : this(APIWrapperImpl(configuration))

    inner class User(val userToken: UserToken) : EndpointInsightsUser by EndpointInsightsUserImpl(this, userToken)
}