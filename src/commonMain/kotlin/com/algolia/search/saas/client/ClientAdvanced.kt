package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.endpoint.EndpointAdvanced
import io.ktor.client.request.get
import kotlinx.coroutines.delay


internal class ClientAdvanced(
    val client: Client,
    override val indexName: IndexName
) : EndpointAdvanced,
    Client by client {

    override suspend fun Task.wait(requestOptions: RequestOptions?): TaskInfo {
        return waitTask(taskID)
    }

    override suspend fun waitTask(taskID: TaskID, requestOptions: RequestOptions?): TaskInfo {
        while (true) {
            getTask(taskID, requestOptions).let {
                if (it.status == TaskStatus.Published) return it
            }
            delay(2000L)
        }
    }

    override suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions?): TaskInfo {
        return read.retry(readTimeout, indexName.pathIndexes("/task/$taskID")) { path ->
            httpClient.get<TaskInfo>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }
}