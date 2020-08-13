package com.algolia.search.client.internal

import com.algolia.search.client.ClientRecommendation
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.endpoint.EndpointRecommendation
import com.algolia.search.endpoint.internal.EndpointRecommendation
import com.algolia.search.transport.internal.Transport

internal class ClientRecommendationImpl internal constructor(
    internal val transport: Transport,
) : ClientRecommendation,
    EndpointRecommendation by EndpointRecommendation(transport),
    Configuration by transport,
    Credentials by transport.credentials
