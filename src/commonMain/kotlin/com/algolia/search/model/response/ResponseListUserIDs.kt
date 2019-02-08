package com.algolia.search.model.response

import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyUserIDs
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseListUserIDs(
    @SerialName(KeyUserIDs) val userIDs: List<ResponseUserID>,
    @Optional @SerialName(KeyPage) val page: Int? = null,
    @Optional @SerialName(KeyHitsPerPage) val hitsPerPage: Int? = null
)