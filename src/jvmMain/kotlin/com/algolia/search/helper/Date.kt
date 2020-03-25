package com.algolia.search.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

internal actual object DateISO8601 {

    val dateISO8601 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    val dateISO8601Millis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    actual fun format(timestamp: Long, inMilliseconds: Boolean): String {
        return if (inMilliseconds) dateISO8601Millis.format(Date(timestamp)) else dateISO8601.format(Date(timestamp))
    }

    actual fun parse(date: String, inMilliseconds: Boolean): Long {
        return if (inMilliseconds) dateISO8601Millis.parse(date).time else dateISO8601.parse(date).time
    }
}
