package com.algolia.search.model.internal

import kotlin.js.Date
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

internal actual object Time {
    @OptIn(ExperimentalTime::class)
    actual fun getCurrentTimeMillis(): Long {
        return Duration.milliseconds(Date.now()).toLong(DurationUnit.MILLISECONDS)
    }
}
