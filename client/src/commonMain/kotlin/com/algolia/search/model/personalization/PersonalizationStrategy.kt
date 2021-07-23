package com.algolia.search.model.personalization

import com.algolia.search.serialize.KeyEventsScoring
import com.algolia.search.serialize.KeyFacetsScoring
import com.algolia.search.serialize.KeyPersonalizationImpact
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *  Strategy Payload
 */
@Serializable
public data class PersonalizationStrategy(
    /**
     * Events scoring saved on the API
     */
    @SerialName(KeyEventsScoring) val eventsScoring: List<EventScoring>,
    /**
     * Facets scoring saved on the API
     */
    @SerialName(KeyFacetsScoring) val facetsScoring: List<FacetScoring>,

    /**
     * Personalization impact
     */
    @SerialName(KeyPersonalizationImpact) val personalizationImpact: Int
)
