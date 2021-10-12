package com.algolia.search.client.internal

import com.algolia.search.client.ClientPlaces
import com.algolia.search.configuration.Configuration
import com.algolia.search.endpoint.EndpointPlaces
import com.algolia.search.endpoint.internal.EndpointPlaces
import com.algolia.search.transport.CustomRequest
import com.algolia.search.transport.internal.Transport

internal class ClientPlacesImpl internal constructor(
    internal val transport: Transport,
) : ClientPlaces,
    EndpointPlaces by EndpointPlaces(transport),
    Configuration by transport,
    CustomRequest by transport
