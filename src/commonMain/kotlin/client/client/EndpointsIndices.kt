package client.client

import client.data.*


interface EndpointsIndices {

    val indexName: IndexName

    suspend fun listIndexes(requestOptions: RequestOptions? = null): ListIndexes

    suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): Task

    suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions? = null): Task

    suspend fun deleteIndex(requestOptions: RequestOptions? = null): TaskDelete
}