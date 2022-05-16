package model.search

import com.algolia.search.model.search.SortFacetsBy.Alpha
import com.algolia.search.model.search.SortFacetsBy.Count
import com.algolia.search.model.search.SortFacetsBy.Other
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestSortFacetsBy {

    @Test
    fun raw() {
        Count.raw shouldEqual Key.Count
        Alpha.raw shouldEqual Key.Alpha
        Other(unknown).raw shouldEqual unknown
    }
}
