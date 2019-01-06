package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName


data class Index internal constructor(
    val client: AlgoliaClient,
    override val indexName: IndexName
) : EndpointsSearch by ClientSearch(client, indexName),
    EndpointsSettings by ClientSettings(client, indexName),
    EndpointsAdvanced by ClientAdvanced(client, indexName),
    EndpointsIndices by ClientIndices(client, indexName),
    EndpointsIndexing by ClientIndexing(client, indexName)