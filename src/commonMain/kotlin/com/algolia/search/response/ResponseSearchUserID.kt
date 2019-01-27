package com.algolia.search.response

import com.algolia.search.model.Datable
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseSearchUserID(
    @SerialName(KeyHits) val hits: List<ResponseUserID>,
    @SerialName(KeyNbHits) val nbHits: Int,
    @SerialName(KeyPage) val page: Int,
    @SerialName(KeyHitsPerPage) val hitsPerPage: Int,
    @SerialName(KeyUpdatedAt) override val date: String
) : Datable