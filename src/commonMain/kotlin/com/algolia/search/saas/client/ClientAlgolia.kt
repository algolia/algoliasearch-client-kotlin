package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.endpoint.EndpointAPIKey
import com.algolia.search.saas.endpoint.EndpointMultipleIndices
import io.ktor.client.features.logging.LogLevel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


class ClientAlgolia private constructor(
    override val applicationID: ApplicationID,
    override val apiKey: APIKey,
    override val writeTimeout: Long = 30000,
    override val readTimeout: Long = 2000,
    override val logLevel: LogLevel = LogLevel.ALL,
    internal val client: Client
) : Configuration,
    EndpointMultipleIndices by ClientMultipleIndices(client),
    EndpointAPIKey by ClientAPIKey(client) {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey,
        writeTimeout: Long = 30000,
        readTimeout: Long = 2000,
        logLevel: LogLevel = LogLevel.BODY
    ) : this(
        applicationID,
        apiKey,
        writeTimeout,
        readTimeout,
        logLevel,
        ClientKtor(applicationID, apiKey, writeTimeout, readTimeout, logLevel)
    )

    private val indexes = mutableMapOf<IndexName, Index>()

    fun getIndex(indexName: IndexName): Index {
        return indexes.getOrPut(indexName) {
            Index(this, indexName)
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