package com.algolia.search.model.personalization

import com.algolia.search.serialize.KeyScore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FacetScoring(
    /**
     * The score to associate with a facet.
     */
    @SerialName(KeyScore) val score: Int
)
