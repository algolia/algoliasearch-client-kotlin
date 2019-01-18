package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.endpoint.ConfigurableEndpoints
import com.algolia.search.saas.endpoint.EndpointAPIKey
import com.algolia.search.saas.endpoint.EndpointMultipleIndex
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


class ClientAlgolia private constructor(
    private val apiWrapper: APIWrapper
) :
    EndpointMultipleIndex by apiWrapper.endpoints.multipleIndex ?: ClientMultipleIndex(apiWrapper),
    EndpointAPIKey by apiWrapper.endpoints.apiKey ?: ClientAPIKey(apiWrapper) {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey,
        endpoints: ConfigurableEndpoints = ConfigurableEndpoints()
    ) : this(APIWrapper(Configuration(applicationID, apiKey), endpoints = endpoints))

    constructor(
        configuration: Configuration,
        endpoints: ConfigurableEndpoints = ConfigurableEndpoints()
    ) : this(APIWrapper(configuration, endpoints = endpoints))

    constructor(
        configuration: Configuration,
        engine: HttpClientEngine?,
        endpoints: ConfigurableEndpoints = ConfigurableEndpoints()
    ) : this(APIWrapper(configuration, engine, endpoints))

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