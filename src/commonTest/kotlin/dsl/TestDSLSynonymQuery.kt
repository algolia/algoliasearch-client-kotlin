package dsl

import com.algolia.search.dsl.synonymQuery
import com.algolia.search.dsl.synonymTypes
import shouldNotBeNull
import kotlin.test.Test

internal class TestDSLSynonymQuery {

    @Test
    fun synonymTypes() {
        val ruleQuery = synonymQuery {
            synonymTypes { +OneWay }
        }

        ruleQuery.synonymTypes.shouldNotBeNull()
    }
}
