package com.algolia.search.transport.internal

import com.algolia.search.model.filter.internal.Converter
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

internal actual val isGzipSupported: Boolean = true

internal actual object Gzip : Converter<String, ByteArray> {

    override fun invoke(input: String): ByteArray {
        return ByteArrayOutputStream().use { bos ->
            GZIPOutputStream(bos).bufferedWriter().use { gzip -> gzip.write(input) }
            bos.toByteArray()
        }
    }
}
