package com.algolia.search.saas.model.queryRule

import kotlinx.serialization.Serializable


@Serializable
data class TimeRange(val from: Long, val until: Long)