package com.algolia.search.saas.data.search

import com.algolia.search.saas.serialize.KeyAvg
import com.algolia.search.saas.serialize.KeyMax
import com.algolia.search.saas.serialize.KeyMin
import com.algolia.search.saas.serialize.KeySum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FacetStats(
    @SerialName(KeyMin) val min: Int,
    @SerialName(KeyMax) val max: Int,
    @SerialName(KeyAvg) val average: Float,
    @SerialName(KeySum) val sum: Float
)