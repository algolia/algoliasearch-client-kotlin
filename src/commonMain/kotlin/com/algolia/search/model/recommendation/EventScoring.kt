package com.algolia.search.model.recommendation

import com.algolia.search.serialize.KeyEventName
import com.algolia.search.serialize.KeyEventType
import com.algolia.search.serialize.KeyScore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Scoring the event
 */
@Serializable
public data class EventScoring(

    /**
     * Name of the event
     */
    @SerialName(KeyEventName) val eventName: String,
    /**
     * Type of the event
     */
    @SerialName(KeyEventType) val eventType: String,
    /**
     * Score of the event
     */
    @SerialName(KeyScore) val score: Int
)
