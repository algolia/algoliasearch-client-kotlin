package com.algolia.search.endpoint

import com.algolia.search.apikey.APIKeyEndpoint


data class ConfigurableEndpoints(
    val advanced: EndpointAdvanced? = null,
    val apiKey: APIKeyEndpoint? = null,
    val index: EndpointIndex? = null,
    val indexing: EndpointIndexing? = null,
    val multipleIndex: EndpointMultipleIndex? = null,
    val search: EndpointSearch? = null,
    val settings: EndpointSettings? = null,
    val synonym: EndpointSynonym? = null,
    val queryRule: EndpointQueryRule? = null,
    val multiCluster: EndpointMultiCluster? = null
)