package com.algolia.search.model.recommendation

import com.algolia.search.serialize.KeyFacetName
import com.algolia.search.serialize.KeyScore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Configure the importance of facets
 */
@Serializable
public data class FacetScoring(

    /**
     * Attribute name
     */
    @SerialName(KeyFacetName) val facetName: String,
    /**
     * Score for the facet
     */
    @SerialName(KeyScore) val score: Int
)