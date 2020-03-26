package com.algolia.search.model.personalization

import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.serialize.KeyScore
import com.algolia.search.serialize.KeyType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class EventScoring(
    /**
     * Indicates which type of [InsightsEvent] the [score] should be associated with.
     */
    @SerialName(KeyType) val eventType: EventType,
    /**
     * The score.
     */
    @SerialName(KeyScore) val score: Int
)
