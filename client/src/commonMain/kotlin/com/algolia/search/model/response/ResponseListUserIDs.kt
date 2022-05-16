package com.algolia.search.model.response

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseListUserIDs(
    /**
     * [ResponseUserID] found for a multi-cluster setup.
     */
    @SerialName(Key.UserIDs) val userIDs: List<ResponseUserID>,
    /**
     * Page which has been requested.
     */
    @SerialName(Key.Page) val pageOrNull: Int,
    /**
     * Number of hits per page requested.
     */
    @SerialName(Key.HitsPerPage) val hitsPerPageOrNull: Int
)
