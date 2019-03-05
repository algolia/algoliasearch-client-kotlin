package com.algolia.search.model


internal expect object Time {

    fun getCurrentTimeMillis(): Long

    fun getCurrentTimeSeconds(): Long
}