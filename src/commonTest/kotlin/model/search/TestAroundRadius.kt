package model.search

import com.algolia.search.model.search.AroundRadius.*
import com.algolia.search.serialize.KeyAll
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestAroundRadius {

    @Test
    fun raw() {
        All.raw shouldEqual KeyAll
        InMeters(10).raw shouldEqual "10"
        Other(unknown).raw shouldEqual unknown
    }
}