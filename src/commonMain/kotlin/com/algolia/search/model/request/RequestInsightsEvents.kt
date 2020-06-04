package com.algolia.search.model.request

import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.serialize.KeyEvents
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestInsightsEvents(
    @SerialName(KeyEvents) val events: List<InsightsEvent>
)
