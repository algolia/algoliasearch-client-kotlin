package client.client

import client.data.IndexName
import client.data.TaskId
import client.data.TaskInfo


interface EndpointsAdvanced {

    val maxTimeToWait: Long

    val indexName: IndexName

    suspend fun getTask(taskId: Long): TaskInfo

    suspend fun getTask(taskId: TaskId): TaskInfo

    suspend fun TaskId.wait(timeToWait: Long = maxTimeToWait): TaskInfo
}