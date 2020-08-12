package com.algolia.search.client.internal

import com.algolia.search.client.ClientRecommendation
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.endpoint.EndpointRecommendation
import com.algolia.search.endpoint.EndpointRecommendationImpl
import com.algolia.search.transport.Transport

internal class ClientRecommendationImpl internal constructor(
    internal val transport: Transport,
) : ClientRecommendation,
    EndpointRecommendation by EndpointRecommendationImpl(transport),
    Configuration by transport,
    Credentials by transport.credentials
