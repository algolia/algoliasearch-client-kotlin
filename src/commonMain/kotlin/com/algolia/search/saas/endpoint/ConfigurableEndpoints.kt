package com.algolia.search.saas.endpoint


data class ConfigurableEndpoints(
    val advanced: EndpointAdvanced? = null,
    val apiKey: EndpointAPIKey? = null,
    val index: EndpointIndex? = null,
    val indexing: EndpointIndexing? = null,
    val multipleIndex: EndpointMultipleIndex? = null,
    val search: EndpointSearch? = null,
    val settings: EndpointSettings? = null
)