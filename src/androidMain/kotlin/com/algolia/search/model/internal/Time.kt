package com.algolia.search.model.internal

internal actual object Time {

    actual fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}
