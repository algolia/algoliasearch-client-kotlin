package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.IndexName


data class Index internal constructor(
    private val client: Client,
    override val indexName: IndexName
) : EndpointSearch by ClientSearch(client, indexName),
    EndpointSettings by ClientSettings(client, indexName),
    EndpointAdvanced by ClientAdvanced(client, indexName),
    EndpointIndex by ClientIndex(client, indexName),
    EndpointIndexing by ClientIndexing(client, indexName),
    EndpointSynonym by ClientSynonym(client, indexName),
    EndpointQueryRule by ClientQueryRule(client, indexName),
    EndpointAPIKeyIndex by ClientAPIKeyIndex(client, indexName)