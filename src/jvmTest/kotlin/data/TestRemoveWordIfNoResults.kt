package data

import com.algolia.search.saas.data.RemoveWordIfNoResults.*
import com.algolia.search.saas.serialize.KeyAllOptional
import com.algolia.search.saas.serialize.KeyFirstWords
import com.algolia.search.saas.serialize.KeyLastWords
import com.algolia.search.saas.serialize.KeyNone
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
        Unknown(unknown).raw shouldEqual unknown
    }
}