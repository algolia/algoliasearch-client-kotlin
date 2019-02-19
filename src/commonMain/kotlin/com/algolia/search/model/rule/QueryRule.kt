package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QueryRule(
    @SerialName(KeyObjectID) val objectID: ObjectID,
    @SerialName(KeyCondition) val condition: Condition,
    @SerialName(KeyConsequence) val consequence: Consequence,
    @Optional @SerialName(KeyEnabled) val enabled: Boolean? = null,
    @Optional @SerialName(KeyValidity) val validity: List<TimeRange>? = null,
    @Optional @SerialName(KeyDescription) val description: String? = null
)