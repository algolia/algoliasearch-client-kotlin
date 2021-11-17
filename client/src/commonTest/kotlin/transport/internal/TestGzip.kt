package transport.internal

import com.algolia.search.transport.internal.Gzip
import com.algolia.search.transport.internal.isGzipSupported
import kotlin.test.Test
import kotlin.test.assertEquals

class TestGzip {

    @Test
    fun test() {
        if (!isGzipSupported) return
        val json = "{ \"id\": 123456, \"name\": \"john doe\", \"books\": [ \"harry potter\", \"dune\" ] }"
        val compressed = Gzip(json).toList()
        val expected = arrayOf(31, -117, 8, 0, 0, 0, 0, 0, 0, 0, -85, 86, 80, -54, 76, 81, -78, 82, 48, 52, 50, 54, 49, 53, -45, 81, 80, -54, 75, -52, 77, 5, -14, -107, -78, -14, 51, -14, 20, 82, -14, 83, -107, -128, 98, 73, -7, -7, -39, -59, 64, -63, 104, 5, -91, -116, -60, -94, -94, 74, -123, -126, -4, -110, -110, -44, 34, -112, 84, 74, 105, 94, -86, -110, 66, -84, 66, 45, 0, 51, 83, -88, -81, 73, 0, 0, 0).map { it.toByte() }
        assertEquals(expected, compressed)
    }
}
