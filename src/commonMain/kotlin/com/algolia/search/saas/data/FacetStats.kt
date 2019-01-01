package com.algolia.search.saas.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FacetStats(
    val min: Int,
    val max: Int,
    @SerialName("avg") val average: Float,
    val sum: Float
)