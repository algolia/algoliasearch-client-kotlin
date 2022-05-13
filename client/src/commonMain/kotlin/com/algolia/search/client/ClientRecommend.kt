@file:Suppress("FunctionName")

package com.algolia.search.client

import com.algolia.search.client.internal.ClientRecommendImpl
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationRecommend
import com.algolia.search.configuration.Credentials
import com.algolia.search.configuration.internal.Credentials
import com.algolia.search.configuration.internal.DEFAULT_LOG_LEVEL
import com.algolia.search.endpoint.EndpointRecommend
import com.algolia.search.logging.LogLevel
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.internal.Transport

public interface ClientRecommend : Configuration, EndpointRecommend, Credentials, CustomRequester {

    public companion object
}

public fun ClientRecommend(
    applicationID: ApplicationID,
    apiKey: APIKey,
    logLevel: LogLevel = DEFAULT_LOG_LEVEL,
): ClientRecommend = ClientRecommendImpl(
    Transport(
        ConfigurationRecommend(applicationID = applicationID, apiKey = apiKey, logLevel = logLevel),
        Credentials(applicationID = applicationID, apiKey = apiKey)
    )
)

public fun ClientRecommend(configuration: ConfigurationRecommend): ClientRecommend = ClientRecommendImpl(
    Transport(configuration, configuration)
)
