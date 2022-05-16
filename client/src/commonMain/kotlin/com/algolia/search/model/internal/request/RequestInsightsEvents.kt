package com.algolia.search.model.internal.request

import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestInsightsEvents(
    @SerialName(Key.Events) val events: List<InsightsEvent>
)
