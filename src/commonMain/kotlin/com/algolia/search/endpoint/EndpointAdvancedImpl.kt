package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.requestOptionsBuilder
import com.algolia.search.model.IndexName
import com.algolia.search.model.LogType
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.RouteLogs
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

internal class EndpointAdvancedImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointAdvanced {

    override suspend fun List<Task>.wait(timeout: Long?, requestOptions: RequestOptions?): List<TaskStatus> {

        suspend fun loop(): List<TaskStatus> {
            return coroutineScope {
                map { async { it.wait(requestOptions = requestOptions) } }.map { it.await() }
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    override suspend fun Task.wait(timeout: Long?, requestOptions: RequestOptions?): TaskStatus {
        return waitTask(taskID, timeout, requestOptions)
    }

    override suspend fun waitTask(taskID: TaskID, timeout: Long?, requestOptions: RequestOptions?): TaskStatus {

        suspend fun loop(): TaskStatus {
            while (true) {
                getTask(taskID, requestOptions).status.let {
                    if (it == TaskStatus.Published) return it
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    override suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions?): TaskInfo {
        return transport.request(HttpMethod.Get, CallType.Read, indexName.toPath("/task/$taskID"), requestOptions)
    }

    override suspend fun getLogs(
        page: Int?,
        hitsPerPage: Int?,
        logType: LogType?,
        requestOptions: RequestOptions?
    ): ResponseLogs {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyIndexName, indexName.raw)
            parameter(KeyOffset, page)
            parameter(KeyLength, hitsPerPage)
            parameter(KeyType, logType?.raw)
        }
        return transport.request(HttpMethod.Get, CallType.Read, RouteLogs, options)
    }
}
