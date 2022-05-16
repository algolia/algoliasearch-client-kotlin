package com.algolia.search.model.personalization

import com.algolia.search.serialize.internal.Key
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
    @SerialName(Key.EventName) val eventName: String,
    /**
     * Type of the event
     */
    @SerialName(Key.EventType) val eventType: String,
    /**
     * Score of the event
     */
    @SerialName(Key.Score) val score: Int
)
