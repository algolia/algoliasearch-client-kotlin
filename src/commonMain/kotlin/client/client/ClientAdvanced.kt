package client.client

import client.data.IndexName
import client.data.TaskId
import client.data.TaskInfo


class ClientAdvanced(
    val client: Client,
    override val indexName: IndexName
) : EndpointsAdvanced {

    override suspend fun TaskId.wait(): TaskInfo {
        return client.wait(indexName, this)
    }

    override suspend fun getTask(taskId: Long): TaskInfo {
        return client.getTask(indexName, taskId)
    }

    override suspend fun getTask(taskId: TaskId): TaskInfo {
        return client.getTask(indexName, taskId.taskID)
    }
}