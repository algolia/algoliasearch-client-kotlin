package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.endpoint.*


data class Index internal constructor(
    private val client: Client,
    override val indexName: IndexName
) : EndpointSearch by ClientSearch(client, indexName),
    EndpointSettings by ClientSettings(client, indexName),
    EndpointAdvanced by ClientAdvanced(client, indexName),
    EndpointIndices by ClientIndices(client, indexName),
    EndpointIndexing by ClientIndexing(client, indexName)