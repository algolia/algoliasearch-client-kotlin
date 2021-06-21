package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyAlpha
import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyHidden
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Rule defining the sort order of facet values.
 */
@Serializable
public enum class SortRule {

    /** Alphabetical (ascending) */
    @SerialName(KeyAlpha)
    Alpha,

    /** Facet count (descending) */
    @SerialName(KeyCount)
    Count,

    /** Hidden (show only pinned values) */
    @SerialName(KeyHidden)
    Hidden
}
