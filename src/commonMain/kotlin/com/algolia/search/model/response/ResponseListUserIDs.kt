package com.algolia.search.model.response

import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyUserIDs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseListUserIDs(
    @SerialName(KeyUserIDs) val userIDs: List<ResponseUserID>,
    @SerialName(KeyPage) val pageOrNull: Int,
    @SerialName(KeyHitsPerPage) val hitsPerPageOrNull: Int
)