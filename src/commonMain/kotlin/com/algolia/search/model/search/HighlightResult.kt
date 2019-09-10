package com.algolia.search.model.search

import com.algolia.search.serialize.KeyFullyHighlighted
import com.algolia.search.serialize.KeyMatchLevel
import com.algolia.search.serialize.KeyMatchedWords
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads


@Serializable
public data class HighlightResult @JvmOverloads constructor(
    /**
     * Markup text with occurrences highlighted. The tags used for highlighting are specified via [Query.highlightPreTag]
     * and [Query.highlightPostTag]
     */
    @SerialName(KeyValue) val value: String,
    /**
     * Indicates how well the value matched the search query.
     */
    @SerialName(KeyMatchLevel) val matchLevel: MatchLevel,
    /**
     * List of words from the query that matched the object.
     */
    @SerialName(KeyMatchedWords) val matchedWords: List<String>,
    /**
     * Whether the entire value is highlighted.
     */
    @SerialName(KeyFullyHighlighted) val fullyHighlighted: Boolean? = null
)