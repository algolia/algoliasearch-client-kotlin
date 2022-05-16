package model.index

import com.algolia.search.model.index.Scope.Rules
import com.algolia.search.model.index.Scope.Settings
import com.algolia.search.model.index.Scope.Synonyms
import com.algolia.search.serialize.internal.Key
import shouldEqual
import kotlin.test.Test

internal class TestScope {

    @Test
    fun raw() {
        Settings.raw shouldEqual Key.Settings
        Rules.raw shouldEqual Key.Rules
        Synonyms.raw shouldEqual Key.Synonyms
    }
}
