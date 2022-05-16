package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseSearchUserID(
    /**
     * List of [ResponseUserID] matching the query.
     */
    @SerialName(Key.Hits) val hits: List<ResponseUserID>,
    /**
     * Number of userIDs matching the query.
     */
    @SerialName(Key.NbHits) val nbHits: Int,
    /**
     * Current page.
     */
    @SerialName(Key.Page) val page: Int,
    /**
     * Number of hits retrieved per page.
     */
    @SerialName(Key.HitsPerPage) val hitsPerPage: Int,
    /**
     * Timestamp of the last update of the index
     */
    @SerialName(Key.UpdatedAt) val updatedAt: ClientDate
)
