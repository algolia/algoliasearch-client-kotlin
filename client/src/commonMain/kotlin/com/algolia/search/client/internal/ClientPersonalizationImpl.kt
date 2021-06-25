package com.algolia.search.client.internal

import com.algolia.search.client.ClientPersonalization
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.endpoint.EndpointRecommendation
import com.algolia.search.endpoint.internal.EndpointPersonalization
import com.algolia.search.transport.internal.Transport

internal class ClientPersonalizationImpl internal constructor(
    internal val transport: Transport,
) : ClientPersonalization,
    EndpointRecommendation by EndpointPersonalization(transport),
    Configuration by transport,
    Credentials by transport.credentials
