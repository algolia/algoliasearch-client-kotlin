package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.IndexName
import com.algolia.search.endpoint.EndpointAPIKeyIndex


data class Index internal constructor(
    private val client: Client,
    override val indexName: IndexName
) : EndpointSearch by client.endpoints.search ?: ClientSearch(client, indexName),
    EndpointSettings by client.endpoints.settings ?: ClientSettings(client, indexName),
    EndpointAdvanced by client.endpoints.advanced ?: ClientAdvanced(client, indexName),
    EndpointIndex by client.endpoints.index ?: ClientIndex(client, indexName),
    EndpointIndexing by client.endpoints.indexing ?: ClientIndexing(client, indexName),
    EndpointSynonym by client.endpoints.synonym ?: ClientSynonym(client, indexName),
    EndpointQueryRule by client.endpoints.queryRule ?: ClientQueryRule(client, indexName),
    EndpointAPIKeyIndex by ClientAPIKeyIndex(
        client,
        indexName
    )