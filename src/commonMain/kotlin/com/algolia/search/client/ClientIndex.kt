package com.algolia.search.client

import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import com.algolia.search.model.common.TaskDelete
import com.algolia.search.model.common.TaskUpdate
import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.serialize.*
import io.ktor.client.request.delete
import io.ktor.client.request.post
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class ClientIndex(
    val client: Client,
    override val indexName: IndexName
) : EndpointIndex,
    Client by client {

    private suspend fun copyOrMove(
        destination: IndexName,
        key: String,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions?
    ): TaskUpdate {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/operation")) { path ->
            httpClient.post<TaskUpdate>(path) {
                setRequestOptions(requestOptions)
                body = json {
                    KeyOperation to key
                    KeyDestination to destination.raw
                    scopes?.let { KeyScope to Json.stringify(Scope.list, it) }
                }.toString()
            }
        }
    }

    override suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>?,
        requestOptions: RequestOptions?
    ): TaskUpdate {
        return copyOrMove(destination, KeyCopy, scopes, requestOptions)
    }

    override suspend fun moveIndex(destination: IndexName, requestOptions: RequestOptions?): TaskUpdate {
        return copyOrMove(destination, KeyMove, null, requestOptions)
    }

    override suspend fun deleteIndex(requestOptions: RequestOptions?): TaskDelete {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
            httpClient.delete<TaskDelete>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clear(requestOptions: RequestOptions?): TaskUpdate {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/clear")) { path ->
            httpClient.post<TaskUpdate>(path) {
                setRequestOptions(requestOptions)
                body = ""
            }
        }
    }
}