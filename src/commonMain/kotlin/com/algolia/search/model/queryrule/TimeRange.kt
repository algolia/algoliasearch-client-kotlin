package com.algolia.search.model.queryrule

import com.algolia.search.serialize.KeyFrom
import com.algolia.search.serialize.KeyUntil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TimeRange(
    @SerialName(KeyFrom) val from: Long,
    @SerialName(KeyUntil) val until: Long
)