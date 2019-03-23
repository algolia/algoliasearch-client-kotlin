package model.search

import com.algolia.search.model.search.MatchLevel.*
import com.algolia.search.serialize.KeyFull
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyPartial
import kotlin.test.Test
import shouldEqual
import unknown


internal class TestMatchLevel {

    @Test
    fun raw() {
        None.raw shouldEqual KeyNone
        Partial.raw shouldEqual KeyPartial
        Full.raw shouldEqual KeyFull
        Other(unknown).raw shouldEqual unknown
    }
}