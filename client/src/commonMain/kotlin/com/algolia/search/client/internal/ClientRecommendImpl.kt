package com.algolia.search.client.internal

import com.algolia.search.client.ClientRecommend
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.endpoint.EndpointRecommend
import com.algolia.search.endpoint.internal.EndpointRecommend
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.internal.Transport

internal class ClientRecommendImpl(
    internal val transport: Transport
) : ClientRecommend,
    EndpointRecommend by EndpointRecommend(transport),
    Configuration by transport,
    Credentials by transport.credentials,
    CustomRequester by transport
