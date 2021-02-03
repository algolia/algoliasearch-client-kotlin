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
 * from the dictionary.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.saveStopwordEntries(
    dictionaryEntries: List<DictionaryEntry.Stopword>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = saveDictionaryEntries(
    dictionary = Dictionary.Stopwords,
    dictionaryEntries = dictionaryEntries,
    requestOptions = requestOptions
)

/**
 * Save Plurals dictionary entries.
 *
 * @param dictionaryEntries dictionary entries to be saved.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.savePluralEntries(
    dictionaryEntries: List<DictionaryEntry.Plural>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = saveDictionaryEntries(
    dictionary = Dictionary.Plurals,
    dictionaryEntries = dictionaryEntries,
    requestOptions = requestOptions
)

/**
 * Save Compounds dictionary entries.
 *
 * @param dictionaryEntries dictionary entries to be saved.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.saveCompoundEntries(
    dictionaryEntries: List<DictionaryEntry.Compound>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary = saveDictionaryEntries(
    dictionary = Dictionary.Compounds,
    dictionaryEntries = dictionaryEntries,
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
public suspend fun EndpointDictionary.deleteStopwordEntries(
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
public suspend fun EndpointDictionary.deletePluralEntries(
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
public suspend fun EndpointDictionary.deleteCompoundEntries(
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
public suspend fun EndpointDictionary.searchStopwordEntries(
    query: Query,
    requestOptions: RequestOptions? = null,
): ResponseSearchDictionaries<DictionaryEntry.Stopword> {
    return searchDictionaryEntries(Dictionary.Stopwords, query, requestOptions)
}

/**
 * Search the plurals dictionary entries.
 *
 * @param query the [Query] used to search.
 * @param requestOptions Configure request locally with [RequestOptions].
 */
public suspend fun EndpointDictionary.searchPluralEntries(
    query: Query,
    requestOptions: RequestOptions? = null,
): ResponseSearchDictionaries<DictionaryEntry.Plural> {
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
): ResponseSearchDictionaries<DictionaryEntry.Compound> {
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
public suspend fun EndpointDictionary.replaceStopwordEntries(
    dictionaryEntries: List<DictionaryEntry.Stopword>,
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
public suspend fun EndpointDictionary.replacePluralEntries(
    dictionaryEntries: List<DictionaryEntry.Plural>,
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
public suspend fun EndpointDictionary.replaceCompoundEntries(
    dictionaryEntries: List<DictionaryEntry.Compound>,
    requestOptions: RequestOptions? = null,
): ResponseDictionary {
    return replaceDictionaryEntries(Dictionary.Compounds, dictionaryEntries, requestOptions)
}
//endregion
