package com.algolia.search.model


internal actual object Time {

    actual fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}