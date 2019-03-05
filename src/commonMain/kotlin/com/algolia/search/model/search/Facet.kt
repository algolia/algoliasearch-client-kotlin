package com.algolia.search.model.search

import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyHighlighted
import com.algolia.search.serialize.KeyName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
public data class Facet(
    @SerialName(KeyName) val name: String,
    @SerialName(KeyCount) val count: Int,
    @SerialName(KeyHighlighted) val highlightedOrNull: String? = null
) {

    @Transient
    val highlighted: String
        get() = highlightedOrNull!!
}