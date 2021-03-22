package com.algolia.search.model.rule

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class RenderingContent(
    val redirect: Redirect,
    val facetMerchandising: FacetMerchandising,
    val userData: JsonObject? = null,
)
