package model.search

import com.algolia.search.model.search.RemoveWordIfNoResults.AllOptional
import com.algolia.search.model.search.RemoveWordIfNoResults.FirstWords
import com.algolia.search.model.search.RemoveWordIfNoResults.LastWords
import com.algolia.search.model.search.RemoveWordIfNoResults.None
import com.algolia.search.model.search.RemoveWordIfNoResults.Other
import com.algolia.search.serialize.internal.KeyAllOptional
import com.algolia.search.serialize.internal.KeyFirstWords
import com.algolia.search.serialize.internal.KeyLastWords
import com.algolia.search.serialize.internal.KeyNone
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestRemoveWordIfNoResults {

    @Test
    fun raw() {
        None.raw shouldEqual KeyNone
        LastWords.raw shouldEqual KeyLastWords
        FirstWords.raw shouldEqual KeyFirstWords
        AllOptional.raw shouldEqual KeyAllOptional
        Other(unknown).raw shouldEqual unknown
    }
}
