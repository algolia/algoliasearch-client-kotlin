package model.search

import com.algolia.search.model.search.AroundRadius.*
import com.algolia.search.serialize.KeyAll
import kotlin.test.Test
import shouldEqual
import unknown


internal class TestAroundRadius {

    @Test
    fun raw() {
        All.raw shouldEqual KeyAll
        InMeters(10).raw shouldEqual "10"
        Other(unknown).raw shouldEqual unknown
    }
}