package com.algolia.search.transport.internal

import com.algolia.search.model.filter.internal.Converter

internal actual val isGzipSupported: Boolean = false

internal actual object Gzip : Converter<String, ByteArray> {

    override fun invoke(input: String): ByteArray {
        throw UnsupportedOperationException("gzip compress is unsupported")
    }
}
