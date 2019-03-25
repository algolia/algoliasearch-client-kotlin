package com.algolia.search.model.response

import com.algolia.search.model.Attribute
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.personalization.EventScoring
import com.algolia.search.model.personalization.FacetScoring
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.KeyEventsScoring
import com.algolia.search.serialize.KeyFacetsScoring
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponsePersonalizationStrategy(
    @SerialName(KeyEventsScoring) val eventsScoring: Map<EventName, EventScoring>,
    @SerialName(KeyFacetsScoring) val facetsScoring: Map<Attribute, FacetScoring>,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task