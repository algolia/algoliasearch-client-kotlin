package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearchUserID(
    /**
     * List of [ResponseUserID] matching the query.
     */
    @SerialName(KeyHits) val hits: List<ResponseUserID>,
    /**
     * Number of userIDs matching the query.
     */
    @SerialName(KeyNbHits) val nbHits: Int,
    /**
     * Current page.
     */
    @SerialName(KeyPage) val page: Int,
    /**
     * Number of hits retrieved per page.
     */
    @SerialName(KeyHitsPerPage) val hitsPerPage: Int,
    /**
     * Timestamp of the last update of the index
     */
    @SerialName(KeyUpdatedAt) val updatedAt: ClientDate
)