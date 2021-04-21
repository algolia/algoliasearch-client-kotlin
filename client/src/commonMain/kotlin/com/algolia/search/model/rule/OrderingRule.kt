package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyHide
import com.algolia.search.serialize.KeyOrder
import com.algolia.search.serialize.KeySortBy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class OrderingRule(
    @SerialName(KeyOrder) public val order: List<String>? = listOf("*"),
    @SerialName(KeyHide) public val hide: List<String>? = null,
    @SerialName(KeySortBy) public val sortBy: SortRule? = null,
)
