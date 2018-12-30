package client.client

import client.RequestOptions
import client.data.IndexName
import client.data.ListIndexes


interface EndpointsIndices {

    val indexName: IndexName

    suspend fun getListIndexes(requestOptions: RequestOptions? = null): ListIndexes
}