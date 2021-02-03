package com.algolia.search.model.task

/**
 * A server-side task, used by [EndpointDictionary.waitTask].
 */
public interface DictionaryTask {

    public val taskID: DictionaryTaskID
}
