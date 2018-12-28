package data

import client.data.SortFacetValuesBy.*
import client.serialize.KeyAlpha
import client.serialize.KeyCount
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestSortFacetValuesBy {

    @Test
    fun key() {
        assertEquals("count", KeyCount)
        assertEquals("alpha", KeyAlpha)
    }

    @Test
    fun raw() {
        assertEquals(KeyCount, Count.raw)
        assertEquals(KeyAlpha, Alpha.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}