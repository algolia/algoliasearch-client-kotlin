package com.algolia.search.model.search

import com.algolia.search.serialize.KeyAlternatives
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Match(
    @SerialName(KeyAlternatives) val alternatives: List<Alternative>
)
