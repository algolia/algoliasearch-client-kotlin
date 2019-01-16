package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.endpoint.EndpointAPIKey
import com.algolia.search.saas.endpoint.EndpointMultipleIndices
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


class ClientAlgolia private constructor(
    private val apiWrapper: APIWrapper
) :
    EndpointMultipleIndices by ClientMultipleIndices(apiWrapper),
    EndpointAPIKey by ClientAPIKey(apiWrapper) {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapper(Configuration(applicationID, apiKey)))

    constructor(configuration: Configuration) : this(APIWrapper(configuration))

    constructor(configuration: Configuration, engine: HttpClientEngine?) : this(APIWrapper(configuration, engine))

    private val indexes = mutableMapOf<IndexName, Index>()

    fun getIndex(indexName: IndexName): Index {
        return indexes.getOrPut(indexName) {
            Index(apiWrapper, indexName)
        }
    }

    suspend fun waitAll(taskIndices: List<TaskIndex>, maxTimeToWait: Long = 10000L): List<TaskInfo> {
        var attempt = 1
        val timeToWait = 10000L

        while (true) {
            coroutineScope {
                taskIndices.map { async { getIndex(it.indexName).getTask(it.taskID) } }.map { it.await() }
            }.let {
                if (it.all { it.status == TaskStatus.Published }) {
                    return it
                }
            }
            delay((timeToWait * attempt).coerceAtMost(maxTimeToWait))
            attempt++
        }
    }
}