package com.algolia.search.model.queryrule

import kotlinx.serialization.Serializable


@Serializable
data class TimeRange(val from: Long, val until: Long)