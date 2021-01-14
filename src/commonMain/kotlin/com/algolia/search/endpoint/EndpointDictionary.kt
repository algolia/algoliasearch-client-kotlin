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
     * Send a batch of dictionary entries.
     */
    public suspend fun saveDictionaryEntries(
        dictionary: Dictionary,
        dictionaryEntries: List<DictionaryEntry>,
        clearExistingDictionaryEntries: Boolean = false,
        requestOptions: RequestOptions? = null,
    ): RevisionIndex

    /**
     *
     */
    public suspend fun replaceDictionaryEntries(
        dictionary: Dictionary,
        dictionaryEntries: List<DictionaryEntry>,
        requestOptions: RequestOptions? = null,
    ): RevisionIndex

    /**
     *
     */
    public suspend fun deleteDictionaryEntries(
        dictionary: Dictionary,
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions? = null,
    ): DeletionIndex

    /**
     *
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
