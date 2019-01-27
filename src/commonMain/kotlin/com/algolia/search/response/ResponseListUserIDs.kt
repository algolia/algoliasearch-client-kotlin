package com.algolia.search.response

import com.algolia.search.serialize.KeyUserIDs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseListUserIDs(
    @SerialName(KeyUserIDs) val userIDs: List<ResponseUserID>
)