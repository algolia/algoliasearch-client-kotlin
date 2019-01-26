package com.algolia.search.saas.model.query_rule

import kotlinx.serialization.Serializable


@Serializable
data class TimeRange(val from: Long, val until: Long)