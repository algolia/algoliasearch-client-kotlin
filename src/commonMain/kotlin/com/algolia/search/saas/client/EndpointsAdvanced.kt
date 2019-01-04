package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Task
import com.algolia.search.saas.data.TaskId
import com.algolia.search.saas.data.TaskInfo


interface EndpointsAdvanced {

    val maxTimeToWait: Long

    val indexName: IndexName

    suspend fun getTask(taskId: TaskId): TaskInfo

    suspend fun Task.wait(timeToWait: Long = maxTimeToWait): TaskInfo
}