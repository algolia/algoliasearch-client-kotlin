package com.algolia.search.model.personalization

import com.algolia.search.serialize.internal.Key
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
    @SerialName(Key.FacetName) val facetName: String,
    /**
     * Score for the facet
     */
    @SerialName(Key.Score) val score: Int
)
