package client.client

import client.data.IndexName
import client.data.TaskId
import client.data.TaskInfo
import client.data.TaskStatus
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.serialization.json.JsonTreeParser


class ClientAdvanced(
    val client: Client,
    override val indexName: IndexName
) : EndpointsAdvanced {

    suspend fun TaskId.wait(taskId: Long): TaskInfo {
        val timeToWait = 10000L
        var attempt = 1

        while (true) {
            getTask(taskId).let {
                if (it.status == TaskStatus.Published) return it
            }
            delay(timeToWait * attempt)
            attempt++
        }
    }

    override suspend fun getTask(taskId: Long): TaskInfo {
        return client.run {
            read.retry(writeTimeout, indexName.pathIndexes("/task/$taskId")) { path ->
                TaskInfo.deserialize(JsonTreeParser.parse(httpClient.get(path)))!!
            }
        }
    }
}