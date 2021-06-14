package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyOrder
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FacetsOrder(
    @SerialName(KeyOrder) public val order: List<String>? = null,
)
