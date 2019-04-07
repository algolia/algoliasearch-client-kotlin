package com.algolia.search.model.response

import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyPage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseSearchRules(
    @SerialName(KeyHits) val hits: List<ResponseRule>,
    @SerialName(KeyNbHits) val nbHits: Int,
    @SerialName(KeyPage) val page: Int,
    @SerialName(KeyNbPages) val nbPages: Int
)