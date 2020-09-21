package com.algolia.search.model.internal

internal expect object Time {

    fun getCurrentTimeMillis(): Long
}
