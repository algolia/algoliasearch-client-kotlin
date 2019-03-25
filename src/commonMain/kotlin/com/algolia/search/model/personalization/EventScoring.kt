package com.algolia.search.model.personalization

import com.algolia.search.serialize.KeyScore
import com.algolia.search.serialize.KeyType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class EventScoring(
    @SerialName(KeyType) val eventType: EventType,
    @SerialName(KeyScore) val score: Int
)