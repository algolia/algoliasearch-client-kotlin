package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import io.ktor.client.request.get
import kotlinx.coroutines.delay


internal class ClientAdvanced(
    val client: Client,
    override val indexName: IndexName
) : EndpointsAdvanced,
    Client by client {

    override val maxTimeToWait = 10000L

    override suspend fun Task.wait(timeToWait: Long): TaskInfo {
        var attempt = 1

        while (true) {
            getTask(taskID).let {
                if (it.status == TaskStatus.Published) return it
            }
            delay((timeToWait * attempt).coerceAtMost(maxTimeToWait))
            attempt++
        }
    }

    override suspend fun getTask(taskID: TaskID): TaskInfo {
        return read.retry(readTimeout, indexName.pathIndexes("/task/$taskID")) { path ->
            httpClient.get<TaskInfo>(path)
        }
    }
}