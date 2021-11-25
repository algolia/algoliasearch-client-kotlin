package transport.internal

import com.algolia.search.transport.internal.Gzip
import com.algolia.search.transport.internal.isGzipSupported
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestGzip {

    @Test
    fun test() {
        if (!isGzipSupported) return
        val json = "{ \"id\": 123456, \"name\": \"john doe\", \"books\": [ \"harry potter\", \"dune\" ] }"
        val compressed = Gzip(json).toList().map { it.toUByte().toInt() }
        val compressedData = listOf(
            171, 86, 80, 202, 76, 81, 178, 82, 48, 52, 50, 54, 49, 53, 211, 81, 80,
            202, 75, 204, 77, 5, 242, 149, 178, 242, 51, 242, 20, 82, 242, 83, 149, 128, 98, 73, 249, 249, 217, 197, 64,
            193, 104, 5, 165, 140, 196, 162, 162, 74, 133, 130, 252, 146, 146, 212, 34, 144, 84, 74, 105, 94, 170, 146,
            66, 172, 66, 45, 0
        )
        println(compressed)
        assertEquals(listOf(0x1f, 0x8b), compressed.slice(0..1)) // two-byte Gzip ID.
        assertEquals(0x08, compressed[2]) // deflate compression method.
        assertEquals(0x00, compressed[3]) // no flags.
        assertEquals(0x00, compressed[4]) // no modification time
        assertEquals(0x00, compressed[5]) // no extra flags
        assertTrue { compressed[6] == 0x00 || compressed[6] == 0xFF } // no os
        assertEquals(compressedData, compressed.slice(10..78)) // compressed data
        assertEquals(listOf(51, 83, 168, 175), compressed.slice(79..82)) // CRC-32 checksum
        assertEquals(listOf(73, 0, 0, 0), compressed.slice(83..86)) // uncompressed data size value in bytes
    }
}
