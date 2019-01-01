package client.client

import client.data.IndexName
import client.data.Scope
import client.data.Task
import client.data.TaskDelete


class ClientIndices(
    val client: Client,
    override val indexName: IndexName
) : EndpointsIndices {

    override suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>?,
        requestOptions: RequestOptions?
    ): Task {
        return client.copyIndex(indexName, destination, scopes, requestOptions)
    }

    override suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions?): Task {
        return client.moveIndex(indexName, destination, requestOptions = requestOptions)
    }

    override suspend fun deleteIndex(requestOptions: RequestOptions?): TaskDelete {
        return client.deleteIndex(indexName, requestOptions)
    }
}