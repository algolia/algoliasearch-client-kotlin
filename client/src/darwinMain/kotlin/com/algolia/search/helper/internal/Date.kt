package com.algolia.search.helper.internal

import com.algolia.search.platform.toMillis
import kotlinx.cinterop.UnsafeNumber
import platform.Foundation.NSDate
import platform.Foundation.NSISO8601DateFormatWithFractionalSeconds
import platform.Foundation.NSISO8601DateFormatter
import platform.Foundation.create

@ThreadLocal
internal actual object DateISO8601 {

    private val dateISO8601 = NSISO8601DateFormatter()

    @OptIn(UnsafeNumber::class)
    internal val dateISO8601Millis = NSISO8601DateFormatter().apply {
        setFormatOptions(NSISO8601DateFormatWithFractionalSeconds)
    }

    actual fun format(timestamp: Long, inMilliseconds: Boolean): String {
        return if (inMilliseconds) formatInMilliseconds(timestamp) else formatInSeconds(timestamp)
    }

    private fun formatInSeconds(timestamp: Long): String {
        val date = NSDate.create(timeIntervalSince1970 = timestamp.toDouble())
        return dateISO8601.stringFromDate(date)
    }

    private fun formatInMilliseconds(timestamp: Long): String {
        val date = NSDate.create(timeIntervalSince1970 = timestamp / 1000.0)
        return dateISO8601Millis.stringFromDate(date)
    }

    actual fun parse(date: String, inMilliseconds: Boolean): Long {
        val formatter = if (inMilliseconds) dateISO8601Millis else dateISO8601
        return formatter.dateFromString(date)?.timeIntervalSinceReferenceDate?.toMillis()
            ?: throw IllegalArgumentException("unable to parse $date")
    }

    fun parse(date: String): NSDate {
        return when (date.length) {
            20 -> dateISO8601.dateFromString(date)
            24 -> dateISO8601Millis.dateFromString(date)
            else -> NSDate()
        } ?: throw IllegalArgumentException("unable to parse $date")
    }
}
