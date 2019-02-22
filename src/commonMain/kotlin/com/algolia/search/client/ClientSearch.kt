package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.LogType
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyType
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout


class ClientSearch private constructor(
    internal val api: APIWrapperImpl
) :
    EndpointMultipleIndex by EndpointMultipleIndexImpl(api),
    EndpointAPIKey by EndpointAPIKeyImpl(api),
    EndpointMultiCluster by EndpointMulticlusterImpl(api) {

    constructor(
        applicationID: ApplicationID,
        apiKey: APIKey
    ) : this(APIWrapperImpl(Configuration(applicationID, apiKey, hosts = null)))

    constructor(
        configuration: Configuration
    ) : this(APIWrapperImpl(configuration))

    // Todo test this
    constructor(
        configuration: Configuration,
        engine: HttpClientEngine?
    ) : this(APIWrapperImpl(configuration, engine))

    private val indexes = mutableMapOf<IndexName, Index>()

    fun initIndex(indexName: IndexName): Index {
        return indexes.getOrPut(indexName) {
            Index(api, indexName)
        }
    }

    // Todo test this
    suspend fun List<TaskIndex>.waitAll(timeout: Long? = null): List<TaskStatus> {

        suspend fun loop(): List<TaskStatus> {
            while (true) {
                coroutineScope {
                    map { async { initIndex(it.indexName).getTask(it.taskID) } }.map { it.await().status }
                }.let {
                    if (it.all { status -> status == TaskStatus.Published }) return it
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    suspend fun ResponseBatches.waitAll(): List<TaskStatus> {
        return tasks.waitAll()
    }

    // TODO Specify why there is no taskID in a comment
    suspend fun CreationAPIKey.wait(timeout: Long? = null): ResponseAPIKey {

        suspend fun loop(): ResponseAPIKey {
            while (true) {
                try {
                    return getAPIKey(apiKey)
                } catch (exception: BadResponseStatusException) {
                    if (exception.statusCode != HttpStatusCode.NotFound) throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    // TODO Specify why there is no taskID in a comment
    suspend fun DeletionAPIKey.wait(timeout: Long? = null): Boolean {

        suspend fun loop(): Boolean {
            while (true) {
                try {
                    getAPIKey(apiKey)
                } catch (exception: BadResponseStatusException) {
                    if (exception.statusCode == HttpStatusCode.NotFound) return true else throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    suspend fun getLogs(
        offset: Int? = null,
        length: Int? = null,
        logType: LogType? = null,
        requestOptions: RequestOptions? = null
    ): ResponseLogs {
        return api.run {
            retryRead(requestOptions, "/1/logs") { url ->
                httpClient.get<ResponseLogs>(url) {
                    parameter(KeyOffset, offset)
                    parameter(KeyLength, length)
                    parameter(KeyType, logType?.raw)
                    setRequestOptions(requestOptions)
                }
            }
        }
    }
}