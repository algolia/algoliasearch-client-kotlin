package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyAlpha
import com.algolia.search.serialize.KeyCount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SortRule {
    @SerialName(KeyAlpha)
    Alpha,
    @SerialName(KeyCount)
    Count
}
