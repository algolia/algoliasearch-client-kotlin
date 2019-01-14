package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.endpoint.*


data class Index internal constructor(
    private val client: ClientAlgolia,
    override val indexName: IndexName
) : EndpointSearch by ClientSearch(client.client, indexName),
    EndpointSettings by ClientSettings(client.client, indexName),
    EndpointAdvanced by ClientAdvanced(client.client, indexName),
    EndpointIndices by ClientIndices(client.client, indexName),
    EndpointIndexing by ClientIndexing(client.client, indexName)