package model.search

import com.algolia.search.model.search.MatchLevel.Full
import com.algolia.search.model.search.MatchLevel.None
import com.algolia.search.model.search.MatchLevel.Other
import com.algolia.search.model.search.MatchLevel.Partial
import com.algolia.search.serialize.KeyFull
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyPartial
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestMatchLevel {

    @Test
    fun raw() {
        None.raw shouldEqual KeyNone
        Partial.raw shouldEqual KeyPartial
        Full.raw shouldEqual KeyFull
        Other(unknown).raw shouldEqual unknown
    }
}
