package com.algolia.search.transport.internal

internal expect object Gzip {

    fun invoke(input: String): ByteArray
}

