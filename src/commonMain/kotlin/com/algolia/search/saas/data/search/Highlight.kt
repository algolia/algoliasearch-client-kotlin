package com.algolia.search.saas.data.search

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class Highlight(
    val value: String,
    val matchLevel: MatchLevel,
    val matchedWords: List<String>,
    @Optional val fullyHighlighted: Boolean? = null
)