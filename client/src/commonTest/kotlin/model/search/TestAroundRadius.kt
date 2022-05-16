package model.search

import com.algolia.search.model.search.AroundRadius.All
import com.algolia.search.model.search.AroundRadius.InMeters
import com.algolia.search.model.search.AroundRadius.Other
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestAroundRadius {

    @Test
    fun raw() {
        All.raw shouldEqual Key.All
        InMeters(10).raw shouldEqual "10"
        Other(unknown).raw shouldEqual unknown
    }
}
