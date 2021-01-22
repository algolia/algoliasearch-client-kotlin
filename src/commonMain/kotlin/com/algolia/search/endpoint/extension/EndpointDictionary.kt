package com.algolia.search.endpoint.extension

import com.algolia.search.endpoint.EndpointDictionary
import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.response.ResponseDictionary
import com.algolia.search.model.response.ResponseSearchDictionaries
import com.algolia.search.model.search.Query
import com.algolia.search.transport.RequestOptions

//region Dictionary entry save extensions
/**
 * Save Stopwords dictionary entries.
 *
 * @param dictionaryEntries dictionary entries to be saved.
 * @param clearExistingDictionaryEntries when `true`, start the batch by removing all the custom entries
 * from the dictionary.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.saveStopwordsEntries(
    dictionaryEntries: List<DictionaryEntry<Dictionary.Stopwords>>,
    clearExistingDictionaryEntries: Boolean = false,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = saveDictionaryEntries(
    dictionary = Dictionary.Stopwords,
    dictionaryEntries = dictionaryEntries,
    clearExistingDictionaryEntries = clearExistingDictionaryEntries,
    requestOptions = requestOptions
)

/**
 * Save Plurals dictionary entries.
 *
 * @param dictionaryEntries dictionary entries to be saved.
 * @param clearExistingDictionaryEntries when `true`, start the batch by removing all the custom entries
 * from the dictionary.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.savePluralsEntries(
    dictionaryEntries: List<DictionaryEntry<Dictionary.Plurals>>,
    clearExistingDictionaryEntries: Boolean = false,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = saveDictionaryEntries(
    dictionary = Dictionary.Plurals,
    dictionaryEntries = dictionaryEntries,
    clearExistingDictionaryEntries = clearExistingDictionaryEntries,
    requestOptions = requestOptions
)

/**
 * Save Compounds dictionary entries.
 *
 * @param dictionaryEntries dictionary entries to be saved.
 * @param clearExistingDictionaryEntries when `true`, start the batch by removing all the custom entries
 * from the dictionary.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.saveCompoundsEntries(
    dictionaryEntries: List<DictionaryEntry<Dictionary.Compounds>>,
    clearExistingDictionaryEntries: Boolean = false,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = saveDictionaryEntries(
    dictionary = Dictionary.Compounds,
    dictionaryEntries = dictionaryEntries,
    clearExistingDictionaryEntries = clearExistingDictionaryEntries,
    requestOptions = requestOptions
)
//endregion

//region Dictionary entry delete extensions
/**
 * Delete Stopwords dictionary entries.
 *
 * @param objectIDs list of entries' IDs to delete.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.deleteStopwordsEntries(
    objectIDs: List<ObjectID>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = deleteDictionaryEntries(
    dictionary = Dictionary.Stopwords,
    objectIDs = objectIDs,
    requestOptions = requestOptions
)

/**
 * Delete Plurals dictionary entries.
 *
 * @param objectIDs list of entries' IDs to delete.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.deletePluralsEntries(
    objectIDs: List<ObjectID>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = deleteDictionaryEntries(
    dictionary = Dictionary.Plurals,
    objectIDs = objectIDs,
    requestOptions = requestOptions
)

/**
 * Delete Compounds dictionary entries.
 *
 * @param objectIDs list of entries' IDs to delete.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.deleteCompoundsEntries(
    objectIDs: List<ObjectID>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = deleteDictionaryEntries(
    dictionary = Dictionary.Compounds,
    objectIDs = objectIDs,
    requestOptions = requestOptions
)
//endregion

//region Dictionary Search Extensions
/**
 * Search the stopwords dictionary entries.
 *
 * @param query the [Query] used to search.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.searchStopwordsEntries(
    query: Query,
    requestOptions: RequestOptions? = null,
): ResponseSearchDictionaries<Dictionary.Stopwords> {
    return searchDictionaryEntries(Dictionary.Stopwords, query, requestOptions)
}

/**
 * Search the plurals dictionary entries.
 *
 * @param query the [Query] used to search.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.searchPluralsEntries(
    query: Query,
    requestOptions: RequestOptions? = null,
): ResponseSearchDictionaries<Dictionary.Plurals> {
    return searchDictionaryEntries(Dictionary.Plurals, query, requestOptions)
}

/**
 * Search the plurals dictionary entries.
 *
 * @param query the [Query] used to search.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.searchCompoundEntries(
    query: Query,
    requestOptions: RequestOptions? = null,
): ResponseSearchDictionaries<Dictionary.Compounds> {
    return searchDictionaryEntries(Dictionary.Compounds, query, requestOptions)
}
//endregion

//region Dictionary replace save extensions
/**
 * Replace stopwords dictionary entries.
 *
 * @param dictionaryEntries dictionary entries to be replaced.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.replaceStopwordsEntries(
    dictionaryEntries: List<DictionaryEntry<Dictionary.Stopwords>>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary {
    return replaceDictionaryEntries(Dictionary.Stopwords, dictionaryEntries, requestOptions)
}

/**
 * Replace plurals dictionary entries.
 *
 * @param dictionaryEntries dictionary entries to be replaced.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.replacePluralsEntries(
    dictionaryEntries: List<DictionaryEntry<Dictionary.Plurals>>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary {
    return replaceDictionaryEntries(Dictionary.Plurals, dictionaryEntries, requestOptions)
}

/**
 * Replace compounds dictionary entries.
 *
 * @param dictionaryEntries dictionary entries to be replaced.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.replaceCompoundsEntries(
    dictionaryEntries: List<DictionaryEntry<Dictionary.Compounds>>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary {
    return replaceDictionaryEntries(Dictionary.Compounds, dictionaryEntries, requestOptions)
}
//endregion
