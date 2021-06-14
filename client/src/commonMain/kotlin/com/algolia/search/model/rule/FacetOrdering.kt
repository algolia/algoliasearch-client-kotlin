package com.algolia.search.model.rule

import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyFacetValues
import com.algolia.search.serialize.KeyFacets
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FacetOrdering(
    /**
     * The ordering of facets.
     */
    @SerialName(KeyFacets) public val facets: FacetsOrder,
    /**
     * The ordering of facet values, within an individual list.
     */
    @SerialName(KeyFacetValues) public val values: Map<Attribute, FacetValuesOrder>
)
