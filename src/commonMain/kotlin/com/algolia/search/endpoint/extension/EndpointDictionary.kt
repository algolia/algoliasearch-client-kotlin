package com.algolia.search.endpoint.extension

import com.algolia.search.endpoint.EndpointDictionary
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.response.ResponseSearchDictionaries
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.transport.RequestOptions
import kotlinx.serialization.json.decodeFromJsonElement

/**
 * Search the stopwords dictionary entries.
 */
public suspend fun EndpointDictionary.searchStopwordsEntries(
    query: Query,
    requestOptions: RequestOptions? = null,
): ResponseSearchDictionaries<DictionaryEntry.Stopword> {
    val response = searchDictionaryEntries(Dictionary.Generic(Dictionary.Stopwords.raw), query, requestOptions)
    return ResponseSearchDictionaries(
        hits = response.hits.map { JsonNonStrict.decodeFromJsonElement(it.json) },
        nbHits = response.nbHits,
        page = response.page,
        nbPages = response.nbPages
    )
}

/**
 * Search the plurals dictionary entries.
 */
public suspend fun EndpointDictionary.searchPluralsEntries(
    query: Query,
    requestOptions: RequestOptions? = null,
): ResponseSearchDictionaries<DictionaryEntry.Plural> {
    val response = searchDictionaryEntries(Dictionary.Generic(Dictionary.Stopwords.raw), query, requestOptions)
    return ResponseSearchDictionaries(
        hits = response.hits.map { JsonNonStrict.decodeFromJsonElement(it.json) },
        nbHits = response.nbHits,
        page = response.page,
        nbPages = response.nbPages
    )
}

/**
 * Search the plurals dictionary entries.
 */
public suspend fun EndpointDictionary.searchCompoundEntries(
    query: Query,
    requestOptions: RequestOptions? = null,
): ResponseSearchDictionaries<DictionaryEntry.Compound> {
    val response = searchDictionaryEntries(Dictionary.Generic(Dictionary.Stopwords.raw), query, requestOptions)
    return ResponseSearchDictionaries(
        hits = response.hits.map { JsonNonStrict.decodeFromJsonElement(it.json) },
        nbHits = response.nbHits,
        page = response.page,
        nbPages = response.nbPages
    )
}
