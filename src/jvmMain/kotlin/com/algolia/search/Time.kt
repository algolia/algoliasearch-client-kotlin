package com.algolia.search


actual object Time {

    actual fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}