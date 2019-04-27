package com.algolia.search.model.response

import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyPage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearchRules(
    /**
     * A list of [Hit].
     */
    @SerialName(KeyHits) val hits: List<ResponseRule>,
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
    @SerialName(KeyNbPages) val nbPages: Int
)