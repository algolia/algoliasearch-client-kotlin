package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.TaskId
import com.algolia.search.saas.data.TaskInfo


interface EndpointsAdvanced {

    val maxTimeToWait: Long

    val indexName: IndexName

    suspend fun getTask(taskId: Long): TaskInfo

    suspend fun getTask(taskId: TaskId): TaskInfo

    suspend fun TaskId.wait(timeToWait: Long = maxTimeToWait): TaskInfo
}