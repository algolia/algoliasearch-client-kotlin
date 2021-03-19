package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyFacetMerchandising
import com.algolia.search.serialize.KeyRedirect
import com.algolia.search.serialize.KeyUserData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class RenderingContent(
    @SerialName(KeyRedirect) public val redirect: Redirect?,
    @SerialName(KeyFacetMerchandising) public val facetMerchandising: FacetMerchandising?,
    @SerialName(KeyUserData) public val userData: List<JsonObject>? = null,
)
