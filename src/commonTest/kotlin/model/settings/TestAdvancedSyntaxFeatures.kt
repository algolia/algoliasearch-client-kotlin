package model.settings

import com.algolia.search.model.settings.AdvancedSyntaxFeatures.*
import com.algolia.search.serialize.KeyExactPhrase
import com.algolia.search.serialize.KeyExcludeWords
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestAdvancedSyntaxFeatures {

    @Test
    fun raw() {
        ExactPhrase.raw shouldEqual KeyExactPhrase
        ExcludeWords.raw shouldEqual KeyExcludeWords
        Other(unknown).raw shouldEqual unknown
    }
}