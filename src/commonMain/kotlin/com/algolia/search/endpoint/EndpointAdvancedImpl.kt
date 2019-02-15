package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.enums.LogType
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyType
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout


internal class EndpointAdvancedImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointAdvanced,
    APIWrapper by api {

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
        return read.retry(requestOptions.computedReadTimeout, indexName.toPath("/task/$taskID")) { url ->
            httpClient.get<TaskInfo>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getLogs(
        offset: Int?,
        length: Int?,
        logType: LogType?,
        requestOptions: RequestOptions?
    ): ResponseLogs {
        return read.retry(requestOptions.computedReadTimeout, "/1/logs") { url ->
            httpClient.get<ResponseLogs>(url) {
                parameter(KeyIndexName, indexName.raw)
                parameter(KeyOffset, offset)
                parameter(KeyLength, length)
                parameter(KeyType, logType?.raw)
                setRequestOptions(requestOptions)
            }
        }
    }
}