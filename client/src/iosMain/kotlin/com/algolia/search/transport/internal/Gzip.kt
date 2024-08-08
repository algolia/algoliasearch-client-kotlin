package com.algolia.search.transport.internal

import com.algolia.search.model.filter.internal.Converter
import com.algolia.search.platform.asNSData
import io.ktor.utils.io.core.toByteArray
import kotlinx.cinterop.UnsafeNumber
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.plus
import kotlinx.cinterop.ptr
import kotlinx.cinterop.refTo
import kotlinx.cinterop.reinterpret
import platform.Foundation.NSData
import platform.Foundation.NSMutableData
import platform.Foundation.create
import platform.Foundation.dataWithLength
import platform.Foundation.increaseLengthBy
import platform.posix.memcpy
import platform.zlib.MAX_MEM_LEVEL
import platform.zlib.Z_DEFAULT_COMPRESSION
import platform.zlib.Z_DEFAULT_STRATEGY
import platform.zlib.Z_DEFLATED
import platform.zlib.Z_FINISH
import platform.zlib.Z_OK
import platform.zlib.deflate
import platform.zlib.deflateEnd
import platform.zlib.deflateInit2
import platform.zlib.uBytefVar
import platform.zlib.z_stream

internal actual object Gzip : (String) -> ByteArray {

    override fun invoke(input: String): ByteArray {
        if (input.isEmpty()) return byteArrayOf()
        return input.asNSData()?.compress()?.toByteArray() ?: throw IllegalStateException("unable to compress data")
    }
}

internal const val CHUNK = 16384u // 16K chunks for expansion

@OptIn(UnsafeNumber::class)
private fun NSData.compress(): NSData? {
    return memScoped {
        val stream: z_stream = alloc()
        val compressed: NSMutableData
        try {
            stream.zalloc = null
            stream.zfree = null
            stream.opaque = null
            stream.avail_in = length.convert()
            stream.next_in = bytes!!.reinterpret()
            stream.total_out = 0u
            stream.avail_out = 0u

            if (deflateInit2(
                    strm = stream.ptr,
                    level = Z_DEFAULT_COMPRESSION,
                    method = Z_DEFLATED,
                    windowBits = 31, // (15+16) for gzip
                    memLevel = MAX_MEM_LEVEL,
                    strategy = Z_DEFAULT_STRATEGY
                ) != Z_OK
            ) return null

            compressed = NSMutableData.dataWithLength(CHUNK.convert())!!
            do {
                if (stream.total_out >= compressed.length) compressed.increaseLengthBy(CHUNK.convert())
                stream.next_out = compressed.mutableBytes!!.reinterpret<uBytefVar>() + stream.total_out.toInt()
                val avail = compressed.length - stream.total_out
                stream.avail_out = avail.convert()
                deflate(stream.ptr, Z_FINISH)
            } while (stream.avail_out == 0u)
        } finally {
            deflateEnd(stream.ptr)
        }

        NSData.create(data = compressed)
    }
}

@OptIn(UnsafeNumber::class)
private fun NSData.toByteArray(): ByteArray = memScoped {
    val size = length.toInt()
    val nsData = ByteArray(size)
    memcpy(nsData.refTo(0), bytes, size.convert())
    return nsData
}
