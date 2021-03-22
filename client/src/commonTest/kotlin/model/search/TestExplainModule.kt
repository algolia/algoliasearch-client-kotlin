package model.search

import com.algolia.search.model.search.ExplainModule.MatchAlternatives
import com.algolia.search.model.search.ExplainModule.Other
import com.algolia.search.serialize.KeyMatchAlternatives
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestExplainModule {

    @Test
    fun raw() {
        MatchAlternatives.raw shouldEqual KeyMatchAlternatives
        Other(unknown).raw shouldEqual unknown
    }
}
