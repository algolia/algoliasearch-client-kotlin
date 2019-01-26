package com.algolia.search.saas.model.search

import com.algolia.search.saas.serialize.KeyFullyHighlighted
import com.algolia.search.saas.serialize.KeyMatchLevel
import com.algolia.search.saas.serialize.KeyMatchedWords
import com.algolia.search.saas.serialize.KeyValue
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Highlight(
    @SerialName(KeyValue) val value: String,
    @SerialName(KeyMatchLevel) val matchLevel: MatchLevel,
    @SerialName(KeyMatchedWords) val matchedWords: List<String>,
    @Optional @SerialName(KeyFullyHighlighted) val fullyHighlighted: Boolean? = null
)