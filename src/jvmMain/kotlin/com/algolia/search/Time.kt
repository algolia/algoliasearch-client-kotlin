package com.algolia.search

import java.util.concurrent.TimeUnit


actual object Time {

    actual fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }

    actual fun getCurrentTimeSeconds(): Long {
        return TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
    }
}