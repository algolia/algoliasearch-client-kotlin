package com.algolia.search.saas.data

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class HighlightResult(
    val value: String,
    val matchLevel: MatchLevel,
    val matchedWords: List<String>,
    @Optional val fullyHighlighted: Boolean? = null
)