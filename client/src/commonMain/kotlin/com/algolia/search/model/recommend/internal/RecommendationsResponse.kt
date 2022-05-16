package com.algolia.search.model.recommend.internal

import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RecommendationsResponse(
    @SerialName(Key.Results) val results: List<ResponseSearch>
)
