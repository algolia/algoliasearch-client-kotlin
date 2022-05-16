package model.search

import com.algolia.search.model.search.MatchLevel.Full
import com.algolia.search.model.search.MatchLevel.None
import com.algolia.search.model.search.MatchLevel.Other
import com.algolia.search.model.search.MatchLevel.Partial
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestMatchLevel {

    @Test
    fun raw() {
        None.raw shouldEqual Key.None
        Partial.raw shouldEqual Key.Partial
        Full.raw shouldEqual Key.Full
        Other(unknown).raw shouldEqual unknown
    }
}
