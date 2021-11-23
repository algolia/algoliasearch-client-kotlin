package com.algolia.search.helper.internal

import com.algolia.search.platform.fractional
import com.algolia.search.platform.toMillis
import kotlinx.cinterop.UnsafeNumber
import platform.Foundation.NSDate
import platform.Foundation.NSISO8601DateFormatWithFractionalSeconds
import platform.Foundation.NSISO8601DateFormatWithInternetDateTime
import platform.Foundation.NSISO8601DateFormatter
import platform.Foundation.create
import platform.Foundation.timeIntervalSince1970

@ThreadLocal
internal actual object DateISO8601 {

    private val dateISO8601 = NSISO8601DateFormatter()

    @OptIn(UnsafeNumber::class)
    internal val dateISO8601Millis = NSISO8601DateFormatter().apply {
        formatOptions = NSISO8601DateFormatWithInternetDateTime xor NSISO8601DateFormatWithFractionalSeconds
    }

    actual fun format(timestamp: Long, inMilliseconds: Boolean): String {
        val date = NSDate.create(timeIntervalSince1970 = timestamp.fractional())
        val formatter = if (inMilliseconds) dateISO8601Millis else dateISO8601
        return formatter.stringFromDate(date)
    }

    actual fun parse(date: String, inMilliseconds: Boolean): Long {
        val formatter = if (inMilliseconds) dateISO8601Millis else dateISO8601
        return formatter.dateFromString(date)?.timeIntervalSince1970?.toMillis()
            ?: throw IllegalArgumentException("unable to parse $date")
    }

    fun parseToNSDate(date: String): NSDate {
        return when (date.length) {
            20 -> dateISO8601.dateFromString(date)
            24 -> dateISO8601Millis.dateFromString(date)
            else -> NSDate()
        } ?: throw IllegalArgumentException("unable to parse $date")
    }
}
