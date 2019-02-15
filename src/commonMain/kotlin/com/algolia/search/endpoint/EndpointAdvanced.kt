package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.enums.LogType
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus


interface EndpointAdvanced {

    val indexName: IndexName

    suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions? = null): TaskInfo

    suspend fun waitTask(taskID: TaskID, requestOptions: RequestOptions? = null): TaskStatus

    suspend fun Task.wait(requestOptions: RequestOptions? = null): TaskStatus

    suspend fun List<Task>.wait(requestOptions: RequestOptions? = null): List<TaskStatus>

    suspend fun getLogs(
        offset: Int? = null,
        length: Int? = null,
        logType: LogType? = null,
        requestOptions: RequestOptions? = null
    ): ResponseLogs
}