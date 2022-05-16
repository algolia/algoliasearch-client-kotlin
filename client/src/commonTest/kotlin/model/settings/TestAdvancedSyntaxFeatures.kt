package model.settings

import com.algolia.search.model.settings.AdvancedSyntaxFeatures.ExactPhrase
import com.algolia.search.model.settings.AdvancedSyntaxFeatures.ExcludeWords
import com.algolia.search.model.settings.AdvancedSyntaxFeatures.Other
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestAdvancedSyntaxFeatures {

    @Test
    fun raw() {
        ExactPhrase.raw shouldEqual Key.ExactPhrase
        ExcludeWords.raw shouldEqual Key.ExcludeWords
        Other(unknown).raw shouldEqual unknown
    }
}
