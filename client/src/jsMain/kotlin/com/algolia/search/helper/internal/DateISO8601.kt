package com.algolia.search.helper.internal

internal actual object DateISO8601 {
    actual fun format(timestamp: Long, inMilliseconds: Boolean): String {
        throw UnsupportedOperationException()
    }

    actual fun parse(date: String, inMilliseconds: Boolean): Long {
        throw UnsupportedOperationException()
    }
}
