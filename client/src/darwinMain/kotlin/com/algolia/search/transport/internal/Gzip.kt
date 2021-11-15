package com.algolia.search.transport.internal

import com.algolia.search.model.filter.internal.Converter
import io.ktor.utils.io.core.toByteArray

internal actual object Gzip : Converter<String, ByteArray> {

    override fun invoke(input: String): ByteArray {
        // TODO: support Gzip compression
        return input.toByteArray()
    }
}

internal actual val isGzipSupported: Boolean = false
