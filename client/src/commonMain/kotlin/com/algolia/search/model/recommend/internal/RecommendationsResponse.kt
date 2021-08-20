package com.algolia.search.model.recommend.internal

import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.serialize.KeyResults
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RecommendationsResponse(
    @SerialName(KeyResults) val results: List<ResponseSearch>
)
