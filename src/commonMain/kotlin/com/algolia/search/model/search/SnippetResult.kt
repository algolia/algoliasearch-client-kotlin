package com.algolia.search.model.search

import com.algolia.search.serialize.KeyMatchLevel
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class SnippetResult(
    @SerialName(KeyValue) val value: String,
    @SerialName(KeyMatchLevel) val matchLevel: MatchLevel
)