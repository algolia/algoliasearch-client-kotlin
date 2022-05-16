package com.algolia.search.model.response

import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseSearchDictionaries<T : DictionaryEntry>(
    /**
     * A list of [DictionaryEntry].
     */
    @SerialName(Key.Hits) val hits: List<T>,
    /**
     *  Number of hits.
     */
    @SerialName(Key.NbHits) val nbHits: Int,
    /**
     * Returned page number.
     */
    @SerialName(Key.Page) val page: Int,
    /**
     * Total number of pages.
     */
    @SerialName(Key.NbPages) val nbPages: Int,
)
