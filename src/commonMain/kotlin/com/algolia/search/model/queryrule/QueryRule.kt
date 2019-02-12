package com.algolia.search.model.queryrule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class QueryRule(
    @SerialName(KeyObjectID) val objectID: ObjectID,
    @SerialName(KeyCondition) val condition: Condition,
    @SerialName(KeyConsequence) val consequence: Consequence,
    @Optional @SerialName(KeyDescription) val description: String? = null,
    @Optional @SerialName(KeyEnabled) val enabled: Boolean? = null,
    @Optional @SerialName(KeyValidity) val validity: List<TimeRange>? = null,
    @Optional @SerialName(Key_HighlightResult) val highlight: JsonObject? = null
)