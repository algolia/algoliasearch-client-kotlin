package com.algolia.search.model.rule

import com.algolia.search.model.Attribute
import kotlinx.serialization.Serializable

@Serializable
public data class FacetMerchandising(
    val order: List<Attribute>,
)
