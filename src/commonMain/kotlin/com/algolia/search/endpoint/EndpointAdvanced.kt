package com.algolia.search.endpoint

import com.algolia.search.client.Index
import com.algolia.search.model.IndexName
import com.algolia.search.model.LogType
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/advanced/?language=kotlin]
 */
public interface EndpointAdvanced {

    /**
     * The [IndexName] used by [Index] to perform operations on.
     */
    public val indexName: IndexName

    /**
     * Check the current [TaskStatus] of a given [Task].
     *
     * @param taskID of the indexing [Task].
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun getTask(
        taskID: TaskID,
        requestOptions: RequestOptions? = null
    ): TaskInfo

    /**
     * Wait for a [Task] to complete before executing the next line of code, to synchronize index updates.
     * All write operations in Algolia are asynchronous by design.
     * It means that when you add or update an object to your index, our servers will reply to your request with
     * a [taskID] as soon as they understood the write operation.
     * The actual insert and indexing will be done after replying to your code.
     * You can wait for a task to complete by using the [taskID] and this method.
     *
     * @param taskID of the indexing task to wait for.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun waitTask(
        taskID: TaskID,
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): TaskStatus

    /**
     * @see waitTask
     *
     * @param timeout If specified, the method will throw a [kotlinx.coroutines.TimeoutCancellationException] after the
     * timeout value in milliseconds is elapsed.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun Task.wait(
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): TaskStatus

    /**
     * @see wait
     *
     * Wait for several [Task] in parallel to complete.
     *
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun List<Task>.wait(
        timeout: Long? = null,
        requestOptions: RequestOptions? = null
    ): List<TaskStatus>

    /**
     * Get the logs of the latest search and indexing operations.
     * You can retrieve the logs of your last 1,000 API calls. It is designed for immediate, real-time debugging.
     * All logs older than 7 days will be removed and won’t be accessible anymore from the API.
     * This API is counted in your operation quota but is not logged.
     *
     * @param page Specify the first entry to retrieve (0-based, 0 is the most recent log entry).
     * @param hitsPerPage Specify the maximum number of entries to retrieve starting at the [page].
     * Maximum allowed value: 1,000.
     * @param logType Type of logs to retrieve.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun getLogs(
        page: Int? = null,
        hitsPerPage: Int? = null,
        logType: LogType? = null,
        requestOptions: RequestOptions? = null
    ): ResponseLogs
}
