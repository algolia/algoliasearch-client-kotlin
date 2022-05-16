package com.algolia.search.model.personalization

import com.algolia.search.serialize.internal.Key
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
    @SerialName(Key.EventsScoring) val eventsScoring: List<EventScoring>,
    /**
     * Facets scoring saved on the API
     */
    @SerialName(Key.FacetsScoring) val facetsScoring: List<FacetScoring>,

    /**
     * Personalization impact
     */
    @SerialName(Key.PersonalizationImpact) val personalizationImpact: Int
)
