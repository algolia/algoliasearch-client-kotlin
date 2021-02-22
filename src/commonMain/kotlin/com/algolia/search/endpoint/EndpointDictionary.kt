package com.algolia.search.endpoint

import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.dictionary.DictionarySettings
import com.algolia.search.model.response.ResponseDictionary
import com.algolia.search.model.response.ResponseSearchDictionaries
import com.algolia.search.model.search.Query
import com.algolia.search.transport.RequestOptions

/**
 * The Dictionary API enables users to customize linguistic resources provided by Algolia such as stop words, plurals,
 * and segmentation.
 *
 * [Documentation](https://www.algolia.com/doc/rest-api/search/#dictionaries-endpoints)
 */
public interface EndpointDictionary {

    /**
     * Save dictionary entries.
     *
     * @param dictionary target dictionary.
     * @param dictionaryEntries dictionary entries to be saved.
     * from the dictionary.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T : DictionaryEntry> saveDictionaryEntries(
        dictionary: Dictionary<T>,
        dictionaryEntries: List<T>,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Replace dictionary entries.
     *
     * @param dictionary target dictionary.
     * @param dictionaryEntries dictionary entries to be replaced.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T : DictionaryEntry> replaceDictionaryEntries(
        dictionary: Dictionary<T>,
        dictionaryEntries: List<T>,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Delete dictionary entries.
     *
     * @param dictionary target dictionary.
     * @param objectIDs list of entries' IDs to delete.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T : DictionaryEntry> deleteDictionaryEntries(
        dictionary: Dictionary<T>,
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Clear all dictionary entries.
     *
     * @param dictionary target dictionary.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T : DictionaryEntry> clearDictionaryEntries(
        dictionary: Dictionary<T>,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Search the dictionary entries.
     *
     * @param dictionary target dictionary.
     * @param query the [Query] used to search.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T : DictionaryEntry> searchDictionaryEntries(
        dictionary: Dictionary<T>,
        query: Query,
        requestOptions: RequestOptions? = null,
    ): ResponseSearchDictionaries<T>

    /**
     * Update dictionary settings.
     * Only specified settings are overridden; unspecified settings are left unchanged.
     * Specifying `null` for a setting resets it to its default value.
     *
     * @param dictionarySettings settings to be applied.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun setDictionarySettings(
        dictionarySettings: DictionarySettings,
        requestOptions: RequestOptions? = null,
    ): ResponseDictionary

    /**
     * Retrieve dictionaries settings.
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun getDictionarySettings(requestOptions: RequestOptions? = null): DictionarySettings
}
