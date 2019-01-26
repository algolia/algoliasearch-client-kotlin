package data

import com.algolia.search.saas.model.index.Scope.*
import com.algolia.search.saas.serialize.KeyRules
import com.algolia.search.saas.serialize.KeySettings
import com.algolia.search.saas.serialize.KeySynonyms
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestScope {

    @Test
    fun raw() {
        Settings.raw shouldEqual KeySettings
        Rules.raw shouldEqual KeyRules
        Synonyms.raw shouldEqual KeySynonyms
    }
}