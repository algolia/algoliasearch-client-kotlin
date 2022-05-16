package com.algolia.search.model.rule

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Rule defining the sort order of facet values.
 */
@Serializable
public enum class SortRule {

    /** Alphabetical (ascending) */
    @SerialName(Key.Alpha)
    Alpha,

    /** Facet count (descending) */
    @SerialName(Key.Count)
    Count,

    /** Hidden (show only pinned values) */
    @SerialName(Key.Hidden)
    Hidden
}
