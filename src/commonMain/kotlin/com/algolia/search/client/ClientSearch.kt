package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.model.task.TaskStatus
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


class ClientSearch private constructor(
    private val apiWrapper: APIWrapperImpl
) :
    EndpointMultipleIndex by EndpointMultipleIndexImpl(apiWrapper),
    EndpointAPIKey by EndpointAPIKeyImpl(apiWrapper),
    EndpointMultiCluster by EndpointMulticlusterImpl(apiWrapper) {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapperImpl(Configuration(applicationID, apiKey, hosts = null)))

    constructor(
        configuration: Configuration
    ) : this(APIWrapperImpl(configuration))

    constructor(
        configuration: Configuration,
        engine: HttpClientEngine?
    ) : this(APIWrapperImpl(configuration, engine))

    private val indexes = mutableMapOf<IndexName, Index>()

    fun getIndex(indexName: IndexName): Index {
        return indexes.getOrPut(indexName) {
            Index(apiWrapper, indexName)
        }
    }

    suspend fun waitAll(taskIndices: List<TaskIndex>, maxTimeToWait: Long = 10000L): List<TaskStatus> {
        var attempt = 1
        val timeToWait = 10000L

        while (true) {
            coroutineScope {
                taskIndices.map { async { getIndex(it.indexName).getTask(it.taskID) } }.map { it.await().status }
            }.let {
                if (it.all { it == TaskStatus.Published }) return it
            }
            delay((timeToWait * attempt).coerceAtMost(maxTimeToWait))
            attempt++
        }
    }
}