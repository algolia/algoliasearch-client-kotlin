package com.algolia.search.client

import com.algolia.search.endpoint.*
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.enums.LogType
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyIndexName
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

    suspend fun List<TaskIndex>.waitAll(): List<TaskStatus> {
        while (true) {
            coroutineScope {
                map { async { getIndex(it.indexName).getTask(it.taskID) } }.map { it.await().status }
            }.let {
                if (it.all { it == TaskStatus.Published }) return it
            }
            delay(2000L)
        }
    }

    suspend fun ResponseBatches.waitAll(): List<TaskStatus> {
        return tasks.waitAll()
    }

    suspend fun CreationAPIKey.wait(): ResponseAPIKey {
        while (true) {
            try {
                return getAPIKey(apiKey)
            } catch (exception: BadResponseStatusException) {
                if (exception.statusCode != HttpStatusCode.NotFound) throw exception
            }
            delay(1000L)
        }
    }

    suspend fun DeletionAPIKey.wait(): Boolean {
        while (true) {
            try {
                getAPIKey(apiKey)
            } catch (exception: BadResponseStatusException) {
                if (exception.statusCode == HttpStatusCode.NotFound) return true else throw exception
            }
            delay(1000L)
        }
    }

    suspend fun getLogs(
        offset: Int? = null,
        length: Int? = null,
        indexName: IndexName? = null,
        logType: LogType? = null,
        requestOptions: RequestOptions? = null
    ): ResponseLogs {
        return apiWrapper.run {
            read.retry(requestOptions.computedReadTimeout, "/1/logs") { path ->
                httpClient.get<ResponseLogs>(path) {
                    parameter(KeyOffset, offset)
                    parameter(KeyLength, length)
                    parameter(KeyIndexName, indexName?.raw)
                    parameter(KeyType, logType?.raw)
                    setRequestOptions(requestOptions)
                }
            }
        }
    }
}