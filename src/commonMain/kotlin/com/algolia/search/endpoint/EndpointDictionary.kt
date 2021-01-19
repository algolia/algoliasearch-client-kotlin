package com.algolia.search.endpoint

import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.dictionary.DictionarySettings
import com.algolia.search.model.response.ResponseDictionary
import com.algolia.search.model.response.ResponseSearchDictionaries
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.DictionaryTaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation](https://www.algolia.com/doc/rest-api/search/#dictionaries-endpoints)
 */
public interface EndpointDictionary {

    /**
     * Save dictionary entries.
     */
    public suspend fun <T : Dictionary> saveDictionaryEntries(
        dictionary: T,
        dictionaryEntries: List<DictionaryEntry<T>>,
        clearExistingDictionaryEntries: Boolean = false,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Replace dictionary entries.
     */
    public suspend fun <T : Dictionary> replaceDictionaryEntries(
        dictionary: T,
        dictionaryEntries: List<DictionaryEntry<T>>,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Delete dictionary entries.
     */
    public suspend fun deleteDictionaryEntries(
        dictionary: Dictionary,
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Clear dictionary entries.
     */
    public suspend fun clearDictionaryEntries(
        dictionary: Dictionary,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Search the dictionary entries.
     */
    public suspend fun <T : Dictionary> searchDictionaryEntries(
        dictionary: T,
        query: Query,
        requestOptions: RequestOptions? = null,
    ): ResponseSearchDictionaries<T>

    /**
     * Update some index settings.
     * Only specified settings are overridden; unspecified settings are left unchanged.
     * Specifying `null` for a setting resets it to its default value.
     */
    public suspend fun setDictionarySettings(
        dictionarySettings: DictionarySettings,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Wait for dictionary task to finish.
     */
    public suspend fun ResponseDictionary.wait(
        timeout: Long? = null,
        requestOptions: RequestOptions? = null,
    ): TaskStatus

    /**
     * Wait for a [DictionaryTaskID] to complete before executing the next line of code.
     *
     * @param taskID of the indexing task to wait for.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun waitTask(
        taskID: DictionaryTaskID,
        timeout: Long? = null,
        requestOptions: RequestOptions? = null,
    ): TaskStatus

    /**
     * Check the current [TaskStatus] of a given [DictionaryTaskID].
     */
    public suspend fun getTask(
        taskID: DictionaryTaskID,
        requestOptions: RequestOptions? = null,
    ): TaskInfo

    /**
     * Retrieve dictionaries settings.
     */
    public suspend fun getDictionarySettings(requestOptions: RequestOptions? = null): DictionarySettings
}
