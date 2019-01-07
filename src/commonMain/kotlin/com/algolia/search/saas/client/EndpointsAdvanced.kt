package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Task
import com.algolia.search.saas.data.TaskID
import com.algolia.search.saas.data.TaskInfo


interface EndpointsAdvanced {

    val maxTimeToWait: Long

    val indexName: IndexName

    suspend fun getTask(taskID: TaskID): TaskInfo

    suspend fun Task.wait(timeToWait: Long = maxTimeToWait): TaskInfo
}