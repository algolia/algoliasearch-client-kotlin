package dsl.ranking

import com.algolia.search.dsl.ranking.DSLIndexName
import indexA
import indexB
import kotlin.test.Test
import shouldEqual

internal class TestDSLIndexName {

    @Test
    fun default() {
        val dsl = DSLIndexName {
            +"indexA"
            +indexB
        }

        dsl shouldEqual listOf(indexA, indexB)
    }
}
