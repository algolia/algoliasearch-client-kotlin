package com.algolia.search

import java.text.SimpleDateFormat
import java.util.*


val dateISO8601 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").apply {
    timeZone = TimeZone.getTimeZone("UTC")
}

val dateISO8601Millis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").apply {
    timeZone = TimeZone.getTimeZone("UTC")
}