package model.index

import com.algolia.search.model.index.Scope.Rules
import com.algolia.search.model.index.Scope.Settings
import com.algolia.search.model.index.Scope.Synonyms
import com.algolia.search.serialize.KeyRules
import com.algolia.search.serialize.KeySettings
import com.algolia.search.serialize.KeySynonyms
import shouldEqual
import kotlin.test.Test

internal class TestScope {

    @Test
    fun raw() {
        Settings.raw shouldEqual KeySettings
        Rules.raw shouldEqual KeyRules
        Synonyms.raw shouldEqual KeySynonyms
    }
}
