package com.algolia.search.helper.internal

import platform.Foundation.NSDate
import platform.Foundation.NSISO8601DateFormatter
import platform.Foundation.create

internal actual object DateISO8601 {

    private val formater = NSISO8601DateFormatter() // TODO: Not thread safe

    actual fun format(timestamp: Long, inMilliseconds: Boolean): String {
        //val formatter = NSISO8601DateFormatter()
        //formater.formatOptions
        //val date = NSDate.create(timestamp.toDouble())
        //formater.stringFromDate(date)
        return TODO()
    }

    actual fun parse(date: String, inMilliseconds: Boolean): Long {
        return TODO()
    }
}
