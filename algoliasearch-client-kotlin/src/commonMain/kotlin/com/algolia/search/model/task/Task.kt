package com.algolia.search.model.task

import com.algolia.search.endpoint.EndpointAdvanced

/**
 * A server-side task, used by [EndpointAdvanced.waitTask].
 */
public interface Task {

    public val taskID: TaskID
}
