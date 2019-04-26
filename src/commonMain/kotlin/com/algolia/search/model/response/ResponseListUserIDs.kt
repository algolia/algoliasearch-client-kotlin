package com.algolia.search.model.response

import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyUserIDs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseListUserIDs(
    /**
     * [ResponseUserID] found for a multi-cluster setup.
     */
    @SerialName(KeyUserIDs) val userIDs: List<ResponseUserID>,
    /**
     * Page which has been requested.
     */
    @SerialName(KeyPage) val pageOrNull: Int,
    /**
     * Number of hits per page requested.
     */
    @SerialName(KeyHitsPerPage) val hitsPerPageOrNull: Int
)