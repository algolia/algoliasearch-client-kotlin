package com.algolia.search.client.internal

import com.algolia.search.client.ClientInsights
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.endpoint.internal.EndpointInsights
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.insights.UserToken
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.internal.Transport

/**
 * Client to manage [InsightsEvent].
 */
internal class ClientInsightsImpl internal constructor(
    internal val transport: Transport,
) : ClientInsights,
    EndpointInsights by EndpointInsights(transport),
    Configuration by transport,
    Credentials by transport.credentials,
    CustomRequester by transport {

    override fun User(userToken: UserToken): ClientInsights.User {
        return ClientInsights.User(insights = this, userToken = userToken)
    }
}
