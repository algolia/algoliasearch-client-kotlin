package com.algolia.search.endpoint

import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.dictionary.DictionarySettings
import com.algolia.search.model.response.ResponseSearchDictionaries
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.search.Query
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
    ): RevisionIndex

    /**
     * Replace dictionary entries.
     */
    public suspend fun <T : Dictionary> replaceDictionaryEntries(
        dictionary: T,
        dictionaryEntries: List<DictionaryEntry<T>>,
        requestOptions: RequestOptions? = null,
    ): RevisionIndex

    /**
     * Delete dictionary entries.
     */
    public suspend fun deleteDictionaryEntries(
        dictionary: Dictionary,
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions? = null,
    ): DeletionIndex

    /**
     * Clear dictionary entries.
     */
    public suspend fun clearDictionaryEntries(
        dictionary: Dictionary,
        requestOptions: RequestOptions? = null,
    ): DeletionIndex

    /**
     * Search the dictionary entries.
     */
    public suspend fun searchDictionaryEntries(
        dictionary: Dictionary,
        query: Query, // TODO: params
        requestOptions: RequestOptions? = null,
    ): ResponseSearchDictionaries

    /**
     * Update some index settings.
     * Only specified settings are overridden; unspecified settings are left unchanged.
     * Specifying `null` for a setting resets it to its default value.
     */
    public suspend fun setDictionarySettings(
        dictionarySettings: DictionarySettings,
        requestOptions: RequestOptions? = null,
    ): RevisionIndex

    /**
     * Retrieve dictionaries settings.
     */
    public suspend fun getDictionarySettings(requestOptions: RequestOptions? = null): DictionarySettings
}
