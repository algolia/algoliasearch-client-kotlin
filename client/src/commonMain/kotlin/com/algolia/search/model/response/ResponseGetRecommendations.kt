package com.algolia.search.model.response

import com.algolia.search.serialize.KeyResults
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseGetRecommendations(
    @SerialName(KeyResults) val results: List<ResponseSearch>
)
