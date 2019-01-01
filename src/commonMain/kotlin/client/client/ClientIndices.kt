package client.client

import client.data.*
import client.serialize.*
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.serialization.json.json


class ClientIndices(
    val client: Client,
    override val indexName: IndexName
) : EndpointsIndices {

    override suspend fun listIndexes(requestOptions: RequestOptions?): ListIndexes {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
                httpClient.get<ListIndexes>(path) {
                    setRequestOptions(requestOptions)
                }
            }
        }
    }

    private suspend fun copyOrMove(destination: IndexName, key: String, scopes: List<Scope>? = null): Task {
        return client.run {
            write.retry(writeTimeout, indexName.pathIndexes("/operation")) { path ->
                httpClient.post<Task>(path) {
                    body = json {
                        KeyOperation to key
                        KeyDestination to destination.raw
                        scopes?.let { KeyScope to Scope.serializeList(it) }
                    }.toString()
                }
            }
        }
    }

    override suspend fun copyIndex(destination: IndexName, scopes: List<Scope>?): Task {
        return copyOrMove(destination, KeyCopy, scopes)
    }

    override suspend fun moveIndex(destination: IndexName): Task {
        return copyOrMove(destination, KeyMove)
    }

    override suspend fun deleteIndex(): TaskDelete {
        return client.run {
            write.retry(writeTimeout, indexName.pathIndexes()) { path ->
                httpClient.delete<TaskDelete>(path)
            }
        }
    }
}