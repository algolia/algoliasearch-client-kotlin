@file:Suppress("FunctionName")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientInsightsImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationInsights
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.internal.Credentials
import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.endpoint.EndpointInsightsUser
import com.algolia.search.endpoint.internal.EndpointInsightsUser
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.insights.UserToken
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.internal.Transport

/**
 * Client to manage [InsightsEvent].
 */
public interface ClientInsights : EndpointInsights, Configuration, Credentials, CustomRequester {

    /**
     * Create a [User] instance.
     *
     * @param userToken user token.
     */
    public fun User(userToken: UserToken): User

    /**
     * Represents an Insights User.
     *
     * @param insights insights endpoint
     * @param userToken user token
     */
    public class User(
        public val insights: EndpointInsights,
        public val userToken: UserToken,
    ) : EndpointInsightsUser by EndpointInsightsUser(insights, userToken)

    public companion object
}

/**
 * Create a [ClientInsights] instance.
 *
 * @param applicationID application ID
 * @param apiKey API Key
 */
public fun ClientInsights(
    applicationID: ApplicationID,
    apiKey: APIKey,
): ClientInsights = ClientInsightsImpl(
    Transport(
        ConfigurationInsights(applicationID, apiKey),
        Credentials(applicationID, apiKey)
    )
)

/**
 * Create a [ClientSearch] instance.
 *
 * @param configuration insights configuration
 */
public fun ClientInsights(
    configuration: ConfigurationInsights,
): ClientInsights = ClientInsightsImpl(Transport(configuration, configuration))
