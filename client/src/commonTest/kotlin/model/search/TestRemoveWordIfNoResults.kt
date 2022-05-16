package model.search

import com.algolia.search.model.search.RemoveWordIfNoResults.AllOptional
import com.algolia.search.model.search.RemoveWordIfNoResults.FirstWords
import com.algolia.search.model.search.RemoveWordIfNoResults.LastWords
import com.algolia.search.model.search.RemoveWordIfNoResults.None
import com.algolia.search.model.search.RemoveWordIfNoResults.Other
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestRemoveWordIfNoResults {

    @Test
    fun raw() {
        None.raw shouldEqual Key.None
        LastWords.raw shouldEqual Key.LastWords
        FirstWords.raw shouldEqual Key.FirstWords
        AllOptional.raw shouldEqual Key.AllOptional
        Other(unknown).raw shouldEqual unknown
    }
}
