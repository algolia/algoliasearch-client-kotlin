package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.model.Task
import com.algolia.search.saas.model.TaskID
import com.algolia.search.saas.model.TaskStatus


interface EndpointAdvanced {

    val indexName: IndexName

    suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions? = null): TaskStatus

    suspend fun waitTask(taskID: TaskID, requestOptions: RequestOptions? = null): TaskStatus

    suspend fun Task.wait(requestOptions: RequestOptions? = null): TaskStatus
}