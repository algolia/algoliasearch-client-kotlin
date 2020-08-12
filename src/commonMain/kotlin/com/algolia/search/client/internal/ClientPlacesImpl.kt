package com.algolia.search.client.internal

import com.algolia.search.client.ClientPlaces
import com.algolia.search.configuration.Configuration
import com.algolia.search.endpoint.EndpointPlaces
import com.algolia.search.endpoint.EndpointPlacesImpl
import com.algolia.search.transport.Transport

internal class ClientPlacesImpl internal constructor(
    internal val transport: Transport,
) : ClientPlaces,
    EndpointPlaces by EndpointPlacesImpl(transport),
    Configuration by transport
