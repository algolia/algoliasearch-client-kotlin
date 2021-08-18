package com.algolia.search.model.response

import kotlinx.serialization.Serializable

@Serializable
public data class ResponseGetRecommendations(
    val results: List<ResponseSearch>
)
