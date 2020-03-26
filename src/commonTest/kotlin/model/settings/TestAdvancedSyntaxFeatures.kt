package model.settings

import com.algolia.search.model.settings.AdvancedSyntaxFeatures.ExactPhrase
import com.algolia.search.model.settings.AdvancedSyntaxFeatures.ExcludeWords
import com.algolia.search.model.settings.AdvancedSyntaxFeatures.Other
import com.algolia.search.serialize.KeyExactPhrase
import com.algolia.search.serialize.KeyExcludeWords
import kotlin.test.Test
import shouldEqual
import unknown

internal class TestAdvancedSyntaxFeatures {

    @Test
    fun raw() {
        ExactPhrase.raw shouldEqual KeyExactPhrase
        ExcludeWords.raw shouldEqual KeyExcludeWords
        Other(unknown).raw shouldEqual unknown
    }
}
