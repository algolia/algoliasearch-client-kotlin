package com.algolia.search.response

import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseRules(
    @SerialName(KeyHits) val hits: List<QueryRule>,
    @SerialName(KeyNbHits) val nbHits: Int
)