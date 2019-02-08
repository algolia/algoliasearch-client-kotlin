package com.algolia.search.model.response

import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyPage
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseRules(
    @SerialName(KeyHits) val hits: List<QueryRule>,
    @SerialName(KeyNbHits) val nbHits: Int,
    @Optional @SerialName(KeyPage) val page: Int? = null,
    @Optional @SerialName(KeyNbPages) val nbPages: Int? = null
)