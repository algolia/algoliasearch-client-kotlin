package com.algolia.search.model.response

import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyPage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseSearchDictionaries(
    /**
     * A list of [DictionaryEntry].
     */
    @SerialName(KeyHits) val hits: List<DictionaryEntry>,
    /**
     *  Number of hits.
     */
    @SerialName(KeyNbHits) val nbHits: Int,
    /**
     * Returned page number.
     */
    @SerialName(KeyPage) val page: Int,
    /**
     * Total number of pages.
     */
    @SerialName(KeyNbPages) val nbPages: Int,
)
