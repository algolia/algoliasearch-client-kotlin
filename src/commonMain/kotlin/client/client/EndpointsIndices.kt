package client.client

import client.data.IndexName
import client.data.Scope
import client.data.Task
import client.data.TaskDelete


interface EndpointsIndices {

    val indexName: IndexName

    suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): Task

    suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions? = null): Task

    suspend fun deleteIndex(requestOptions: RequestOptions? = null): TaskDelete
}