package com.algolia.search.saas.model

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class QueryRule(
    val objectID: ObjectID,
    val condition: Condition,
    val consequence: Consequence,
    @Optional val description: String? = null,
    @Optional val enabled: Boolean? = null,
    @Optional val validity: List<TimeRange>? = null
)