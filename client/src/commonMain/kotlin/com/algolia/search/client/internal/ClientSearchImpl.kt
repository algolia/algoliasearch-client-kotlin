package com.algolia.search.client.internal

import com.algolia.search.client.ClientSearch
import com.algolia.search.client.Index
import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.Credentials
import com.algolia.search.dsl.internal.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointAPIKey
import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.endpoint.EndpointDictionary
import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.endpoint.internal.EndpointAPIKey
import com.algolia.search.endpoint.internal.EndpointDictionary
import com.algolia.search.endpoint.internal.EndpointMulticluster
import com.algolia.search.endpoint.internal.EndpointMultipleIndex
import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.model.IndexName
import com.algolia.search.model.LogType
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.ResponseDictionary
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.task.AppTaskID
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.transport.CustomRequester
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

internal class ClientSearchImpl internal constructor(
    internal val transport: Transport,
) : ClientSearch,
    EndpointMultipleIndex by EndpointMultipleIndex(transport),
    EndpointAPIKey by EndpointAPIKey(transport),
    EndpointMultiCluster by EndpointMulticluster(transport),
    EndpointDictionary by EndpointDictionary(transport),
    Configuration by transport,
    Credentials by transport.credentials,
    CustomRequester by transport {

    /**
     *  Initialize an [Index] configured with [ConfigurationSearch].
     */
    override fun initIndex(indexName: IndexName): Index {
        return Index(transport, indexName)
    }

    /**
     * Wait on multiple [TaskIndex] operations. To be used with [multipleBatchObjects].
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    override suspend fun List<TaskIndex>.waitAll(timeout: Long?): List<TaskStatus> {

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

    /**
     * Convenience method similar to [waitAll] but with a [ResponseBatches] as receiver.
     */
    override suspend fun ResponseBatches.waitAll(timeout: Long?): List<TaskStatus> {
        return tasks.waitAll(timeout)
    }

    /**
     * Wait on a [CreationAPIKey] operation.
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    override suspend fun CreationAPIKey.wait(timeout: Long?): ResponseAPIKey {

        suspend fun loop(): ResponseAPIKey {
            while (true) {
                try {
                    return getAPIKey(apiKey)
                } catch (exception: AlgoliaApiException) {
                    if (exception.httpErrorCode != HttpStatusCode.NotFound.value) throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    /**
     * Wait on a [DeletionAPIKey] operation.
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    override suspend fun DeletionAPIKey.wait(timeout: Long?): Boolean {

        suspend fun loop(): Boolean {
            while (true) {
                try {
                    getAPIKey(apiKey)
                } catch (exception: AlgoliaApiException) {
                    if (exception.httpErrorCode == HttpStatusCode.NotFound.value) return true else throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    override suspend fun ResponseDictionary.wait(timeout: Long?, requestOptions: RequestOptions?): TaskStatus {
        return waitTask(taskID, timeout, requestOptions)
    }

    override suspend fun waitTask(
        taskID: AppTaskID,
        timeout: Long?,
        requestOptions: RequestOptions?,
    ): TaskStatus {
        suspend fun loop(): TaskStatus {
            while (true) {
                val taskStatus = getTask(taskID, requestOptions).status
                if (TaskStatus.Published == taskStatus) return taskStatus
                delay(1000L)
            }
        }
        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    override suspend fun getTask(taskID: AppTaskID, requestOptions: RequestOptions?): TaskInfo {
        return transport.request(
            HttpMethod.Get,
            CallType.Read,
            "${Route.Task}/$taskID",
            requestOptions
        )
    }

    /**
     * Convenience methods to get the logs directly from the [ClientSearch] without instantiating an [Index].
     *
     * @see EndpointAdvanced.getLogs
     */
    override suspend fun getLogs(
        page: Int?,
        hitsPerPage: Int?,
        logType: LogType?,
        requestOptions: RequestOptions?,
    ): ResponseLogs {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.Offset, page)
            parameter(Key.Length, hitsPerPage)
            parameter(Key.Type, logType?.raw)
        }

        return transport.request(HttpMethod.Get, CallType.Read, Route.Logs, options)
    }
}
