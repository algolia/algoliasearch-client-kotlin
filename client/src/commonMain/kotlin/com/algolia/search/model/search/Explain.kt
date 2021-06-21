package com.algolia.search.model.search

import com.algolia.search.serialize.KeyMatch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Explain(
    @SerialName(KeyMatch) val match: Match
)
