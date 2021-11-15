package com.algolia.search.transport.internal

import com.algolia.search.model.filter.internal.Converter
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

internal actual object Gzip : Converter<String, ByteArray> {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override fun invoke(input: String): ByteArray {
        return ByteArrayOutputStream(input.length).use { bos ->
            GZIPOutputStream(bos).use { gzip ->
                gzip.write(input.toByteArray())
            }
            bos.toByteArray()
        }
    }
}

internal actual val isGzipSupported: Boolean
    get() = true
