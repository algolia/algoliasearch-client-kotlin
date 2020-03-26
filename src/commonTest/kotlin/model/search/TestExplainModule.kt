package model.search

import com.algolia.search.model.search.ExplainModule.MatchAlternatives
import com.algolia.search.model.search.ExplainModule.Other
import com.algolia.search.serialize.KeyMatchAlternatives
import kotlin.test.Test
import shouldEqual
import unknown

internal class TestExplainModule {

    @Test
    fun raw() {
        MatchAlternatives.raw shouldEqual KeyMatchAlternatives
        Other(unknown).raw shouldEqual unknown
    }
}
