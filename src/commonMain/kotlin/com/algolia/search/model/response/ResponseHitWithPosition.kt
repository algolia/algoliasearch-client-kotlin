package com.algolia.search.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseHitWithPosition(
    val hit: ResponseSearch.Hit,
    val position: Int,
    val page: Int
)
