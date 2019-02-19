package model.enums

import com.algolia.search.model.enums.RemoveWordIfNoResults.*
import com.algolia.search.serialize.KeyAllOptional
import com.algolia.search.serialize.KeyFirstWords
import com.algolia.search.serialize.KeyLastWords
import com.algolia.search.serialize.KeyNone
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
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