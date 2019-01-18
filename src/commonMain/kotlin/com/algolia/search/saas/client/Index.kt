package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.endpoint.*


data class Index internal constructor(
    private val client: Client,
    override val indexName: IndexName
) : EndpointSearch by client.endpoints.search ?: ClientSearch(client, indexName),
    EndpointSettings by client.endpoints.settings ?: ClientSettings(client, indexName),
    EndpointAdvanced by client.endpoints.advanced ?: ClientAdvanced(client, indexName),
    EndpointIndex by client.endpoints.index ?: ClientIndex(client, indexName),
    EndpointIndexing by client.endpoints.indexing ?: ClientIndexing(client, indexName)