package com.algolia.search.model.task

import com.algolia.search.client.ClientSearch

/**
 * A numeric value (up to 64bits) used to identify a task at the application level.
 * It can be used to perform a [ClientSearch.waitTask] operation.
 */
public interface AppTaskID {

    public val raw: Long
}
