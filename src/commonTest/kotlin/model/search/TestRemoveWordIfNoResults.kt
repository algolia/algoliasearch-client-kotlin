package model.search

import com.algolia.search.model.search.RemoveWordIfNoResults.*
import com.algolia.search.serialize.KeyAllOptional
import com.algolia.search.serialize.KeyFirstWords
import com.algolia.search.serialize.KeyLastWords
import com.algolia.search.serialize.KeyNone
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