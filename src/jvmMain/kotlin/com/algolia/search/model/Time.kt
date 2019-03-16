package com.algolia.search.model

import java.util.concurrent.TimeUnit


internal actual object Time {

    actual fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}