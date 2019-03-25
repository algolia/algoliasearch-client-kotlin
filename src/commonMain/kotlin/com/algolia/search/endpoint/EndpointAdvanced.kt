package com.algolia.search.endpoint

import com.algolia.search.model.IndexName
import com.algolia.search.model.LogType
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.transport.RequestOptions


public interface EndpointAdvanced {

    val indexName: IndexName

    suspend fun getTask(
        taskID: TaskID,
        requestOptions: RequestOptions? = null
    ): TaskInfo

    suspend fun waitTask(
        taskID: TaskID,
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): TaskStatus

    suspend fun Task.wait(
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): TaskStatus

    suspend fun List<Task>.wait(
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): List<TaskStatus>

    suspend fun getLogs(
        page: Int? = null,
        hitsPerPage: Int? = null,
        logType: LogType? = null,
        requestOptions: RequestOptions? = null
    ): ResponseLogs
}