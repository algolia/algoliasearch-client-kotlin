package data

import client.data.SortFacetValuesBy
import client.serialize.KeyAlpha
import client.serialize.KeyCount
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestSortFacetValuesBy {

    @Test
    fun key() {
        assertEquals("count", KeyCount)
        assertEquals("alpha", KeyAlpha)
    }

    @Test
    fun raw() {
        assertEquals(KeyCount, SortFacetValuesBy.Count.raw)
        assertEquals(KeyAlpha, SortFacetValuesBy.Alpha.raw)
        assertEquals(unknown, SortFacetValuesBy.Unknown(unknown).raw)
    }
}