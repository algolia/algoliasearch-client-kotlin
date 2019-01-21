package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Task
import com.algolia.search.saas.data.TaskID
import com.algolia.search.saas.data.TaskStatus


interface EndpointAdvanced {

    val indexName: IndexName

    suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions? = null): TaskStatus

    suspend fun waitTask(taskID: TaskID, requestOptions: RequestOptions? = null): TaskStatus

    suspend fun Task.wait(requestOptions: RequestOptions? = null): TaskStatus
}