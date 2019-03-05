package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearchUserID(
    @SerialName(KeyHits) val hits: List<ResponseUserID>,
    @SerialName(KeyNbHits) val nbHits: Int,
    @SerialName(KeyPage) val page: Int,
    @SerialName(KeyHitsPerPage) val hitsPerPage: Int,
    @SerialName(KeyUpdatedAt) val updatedAt: ClientDate
)