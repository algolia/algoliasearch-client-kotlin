package com.algolia.search.model.recommend.internal

import com.algolia.search.model.recommend.RecommendationsOptions
import kotlinx.serialization.Serializable

@Serializable
internal data class RecommendationsRequests<T : RecommendationsOptions>(
    val requests: List<T>
)
