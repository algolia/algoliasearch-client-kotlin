package com.algolia.search.model.rule

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KeyAttribute
import com.algolia.search.serialize.KeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class AttributedFacets(
    @SerialName(KeyAttribute) public val attribute: Attribute,
    @SerialName(KeyValues) public val facets: List<Facet>
)
