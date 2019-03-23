package model.search

import com.algolia.search.model.search.SortFacetValuesBy.*
import com.algolia.search.serialize.KeyAlpha
import com.algolia.search.serialize.KeyCount
import kotlin.test.Test
import shouldEqual
import unknown


internal class TestSortFacetValuesBy {

    @Test
    fun raw() {
        Count.raw shouldEqual KeyCount
        Alpha.raw shouldEqual KeyAlpha
        Other(unknown).raw shouldEqual unknown
    }
}