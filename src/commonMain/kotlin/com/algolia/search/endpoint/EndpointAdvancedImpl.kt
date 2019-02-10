package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout


internal class EndpointAdvancedImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointAdvanced,
    APIWrapper by api {

    override suspend fun List<Task>.wait(timeout: Long, requestOptions: RequestOptions?): List<TaskStatus> {
        return coroutineScope {
            map { async { it.wait(timeout, requestOptions) } }.map { it.await() }
        }
    }

    override suspend fun Task.wait(timeout: Long, requestOptions: RequestOptions?): TaskStatus {
        return withTimeout(timeout) {
            waitTask(taskID, requestOptions)
        }
    }

    override suspend fun waitTask(taskID: TaskID, requestOptions: RequestOptions?): TaskStatus {
        while (true) {
            getTask(taskID, requestOptions).status.let {
                if (it == TaskStatus.Published) return it
            }
            delay(2000L)
        }
    }

    override suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions?): TaskInfo {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/task/$taskID")) { path ->
            httpClient.get<TaskInfo>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }
}