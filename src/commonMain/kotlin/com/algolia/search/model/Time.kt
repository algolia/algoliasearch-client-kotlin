package com.algolia.search.model


expect object Time {

    fun getCurrentTimeMillis(): Long

    fun getCurrentTimeSeconds(): Long
}