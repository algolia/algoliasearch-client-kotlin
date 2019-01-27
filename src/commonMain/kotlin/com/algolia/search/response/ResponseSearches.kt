package com.algolia.search.response

import com.algolia.search.serialize.KeyResults
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseSearches(
    @SerialName(KeyResults) val results: List<ResponseSearch>
)