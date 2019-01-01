package client.client

import client.data.IndexName
import client.data.TaskId
import client.data.TaskInfo
import client.data.TaskStatus
import io.ktor.client.request.get
import kotlinx.coroutines.delay


class ClientAdvanced(
    val client: Client,
    override val indexName: IndexName
) : EndpointsAdvanced {

    override val maxTimeToWait = 10000L

    override suspend fun TaskId.wait(timeToWait: Long): TaskInfo {
        var attempt = 1

        while (true) {
            getTask(taskID).let {
                if (it.status == TaskStatus.Published) return it
            }
            delay((timeToWait * attempt).coerceAtMost(maxTimeToWait))
            attempt++
        }
    }

    override suspend fun getTask(taskId: Long): TaskInfo {
        return client.run {
            read.retry(readTimeout, indexName.pathIndexes("/task/$taskId")) { path ->
                httpClient.get<TaskInfo>(path)
            }
        }
    }

    override suspend fun getTask(taskId: TaskId): TaskInfo {
        return getTask(taskId.taskID)
    }
}