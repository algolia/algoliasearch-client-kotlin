package com.algolia.search.model.task

import com.algolia.search.endpoint.EndpointAdvanced

/**
 * A server-side task, used by [EndpointAdvanced.waitTask].
 */
interface Task {

    val taskID: TaskID
}
