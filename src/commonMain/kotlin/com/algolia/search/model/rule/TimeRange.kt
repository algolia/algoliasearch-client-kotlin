package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyFrom
import com.algolia.search.serialize.KeyUntil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeRange(
    /**
     * Lower bound of the time range (Unix timestamp).
     */
    @SerialName(KeyFrom) val from: Long,
    /**
     * Upper bound of the time range (Unix timestamp).
     */
    @SerialName(KeyUntil) val until: Long
)
