package com.algolia.search.helper.internal

internal const val dateTimeFormatISO8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
internal const val dateTimeFormatISO8601Millis = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

internal expect object DateISO8601 {

    fun format(timestamp: Long, inMilliseconds: Boolean = false): String

    fun parse(date: String, inMilliseconds: Boolean = false): Long
}
