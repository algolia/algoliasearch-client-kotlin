package data

import client.data.SortFacetValuesBy.*
import client.serialize.KeyAlpha
import client.serialize.KeyCount
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestSortFacetValuesBy {

    @Test
    fun raw() {
        KeyCount shouldEqual Count.raw
        KeyAlpha shouldEqual Alpha.raw
        unknown shouldEqual Unknown(unknown).raw
    }
}