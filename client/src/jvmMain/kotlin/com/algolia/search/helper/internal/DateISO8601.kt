package com.algolia.search.helper.internal

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

internal actual object DateISO8601 {

    private val localDateISO8601 = dateFormatOf("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val localDateISO8601Millis = dateFormatOf("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    val dateISO8601: DateFormat get() = localDateISO8601.get()
    val dateISO8601Millis: DateFormat get() = localDateISO8601Millis.get()

    actual fun format(timestamp: Long, inMilliseconds: Boolean): String {
        return if (inMilliseconds) dateISO8601Millis.format(Date(timestamp)) else dateISO8601.format(Date(timestamp))
    }

    actual fun parse(date: String, inMilliseconds: Boolean): Long {
        return if (inMilliseconds) dateISO8601Millis.parse(date).time else dateISO8601.parse(date).time
    }

    /** Create [ThreadLocal] instance for [DateFormat] **/
    private fun dateFormatOf(pattern: String, timeZoneId: String = "UTC") = object : ThreadLocal<DateFormat>() {
        override fun initialValue() = SimpleDateFormat(pattern).apply {
            timeZone = TimeZone.getTimeZone(timeZoneId)
        }
    }
}
