package model.search

import com.algolia.search.model.search.SortFacetValuesBy.*
import com.algolia.search.serialize.KeyAlpha
import com.algolia.search.serialize.KeyCount
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestSortFacetValuesBy {

    @Test
    fun raw() {
        Count.raw shouldEqual KeyCount
        Alpha.raw shouldEqual KeyAlpha
        Other(unknown).raw shouldEqual unknown
    }
}