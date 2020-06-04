package model.search

import com.algolia.search.model.search.SortFacetsBy.Alpha
import com.algolia.search.model.search.SortFacetsBy.Count
import com.algolia.search.model.search.SortFacetsBy.Other
import com.algolia.search.serialize.KeyAlpha
import com.algolia.search.serialize.KeyCount
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestSortFacetsBy {

    @Test
    fun raw() {
        Count.raw shouldEqual KeyCount
        Alpha.raw shouldEqual KeyAlpha
        Other(unknown).raw shouldEqual unknown
    }
}
