package com.algolia.search.model.personalization

import com.algolia.search.model.Attribute
import com.algolia.search.model.insights.EventName
import kotlinx.serialization.Serializable


@Serializable
public data class PersonalizationStrategy(
    val eventsScoring: Map<EventName, EventScoring>,
    val facetsScoring: Map<Attribute, FacetScoring>
)