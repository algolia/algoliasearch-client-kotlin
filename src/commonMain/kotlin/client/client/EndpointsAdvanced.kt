package client.client

import client.data.IndexName
import client.data.TaskInfo


interface EndpointsAdvanced {

    val indexName: IndexName

    suspend fun getTask(taskId: Long): TaskInfo
}