package com.algolia.search.model.search

import com.algolia.search.serialize.KeyFullyHighlighted
import com.algolia.search.serialize.KeyMatchLevel
import com.algolia.search.serialize.KeyMatchedWords
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HighlightResult(
    @SerialName(KeyValue) val value: String,
    @SerialName(KeyMatchLevel) val matchLevel: MatchLevel,
    @SerialName(KeyMatchedWords) val matchedWords: List<String>,
    @Optional @SerialName(KeyFullyHighlighted) val fullyHighlighted: Boolean? = null
)