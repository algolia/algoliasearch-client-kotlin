package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Rule(
    @SerialName(KeyObjectID) val objectID: ObjectID,
    @SerialName(KeyCondition) val condition: Condition,
    @SerialName(KeyConsequence) val consequence: Consequence,
    @SerialName(KeyEnabled) val enabled: Boolean? = null,
    @SerialName(KeyValidity) val validity: List<TimeRange>? = null,
    @SerialName(KeyDescription) val description: String? = null
)