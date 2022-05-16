package com.algolia.search.model.rule

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TimeRange(
    /**
     * Lower bound of the time range (Unix timestamp).
     */
    @SerialName(Key.From) val from: Long,
    /**
     * Upper bound of the time range (Unix timestamp).
     */
    @SerialName(Key.Until) val until: Long
)
