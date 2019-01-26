package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import com.algolia.search.model.common.TaskStatus


interface EndpointAdvanced {

    val indexName: IndexName

    suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions? = null): TaskStatus

    suspend fun waitTask(taskID: TaskID, requestOptions: RequestOptions? = null): TaskStatus

    suspend fun Task.wait(requestOptions: RequestOptions? = null): TaskStatus
}