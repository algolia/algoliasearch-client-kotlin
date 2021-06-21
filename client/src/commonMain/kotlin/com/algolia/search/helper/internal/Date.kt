package com.algolia.search.helper.internal

internal expect object DateISO8601 {

    fun format(timestamp: Long, inMilliseconds: Boolean = false): String

    fun parse(date: String, inMilliseconds: Boolean = false): Long
}
