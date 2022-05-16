package com.algolia.search.model.search

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SnippetResult(
    /**
     * Markup text with occurrences highlighted. The tags used for highlighting are specified via
     * [Query.highlightPreTag] and [Query.highlightPostTag].
     * The text used to indicate ellipsis is specified via [Query.snippetEllipsisText].
     */
    @SerialName(Key.Value) val value: String,
    /**
     * Indicates how well the attribute matched the search query.
     */
    @SerialName(Key.MatchLevel) val matchLevel: MatchLevel
)
