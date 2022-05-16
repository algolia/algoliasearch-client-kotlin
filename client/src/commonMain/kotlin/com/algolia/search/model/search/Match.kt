package com.algolia.search.model.search

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Match(
    @SerialName(Key.Alternatives) val alternatives: List<Alternative>
)
