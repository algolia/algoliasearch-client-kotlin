package client.client

import client.data.IndexName


data class Index(
    val client: Client,
    override val indexName: IndexName
) : EndpointsSearch by ClientSearch(client, indexName),
    EndpointsSettings by ClientSettings(client, indexName),
    EndpointsAdvanced by ClientAdvanced(client, indexName)