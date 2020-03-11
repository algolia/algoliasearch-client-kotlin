package com.algolia.search.model.personalization

import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.serialize.KeyScore
import com.algolia.search.serialize.KeyType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Deprecated(
    message = "Models are deprecated please use models located in com.algolia.search.model.recommendation.",
    level = DeprecationLevel.WARNING
)
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
