package com.algolia.search.transport.internal

import com.algolia.search.model.filter.internal.Converter
import cocoapods.GZIP.*
import com.algolia.search.platform.asNSData
import kotlinx.cinterop.UnsafeNumber
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.refTo
import platform.Foundation.NSData
import platform.posix.memcpy

internal actual object Gzip : Converter<String, ByteArray> {

    override fun invoke(input: String): ByteArray {
        return input.asNSData()!!.gzippedData()!!.toByteArray()
    }
}

@OptIn(UnsafeNumber::class)
private fun NSData.toByteArray(): ByteArray = memScoped {
    val size = length.toInt()
    val nsData = ByteArray(size)
    memcpy(nsData.refTo(0), bytes, size.convert())
    return nsData
}
