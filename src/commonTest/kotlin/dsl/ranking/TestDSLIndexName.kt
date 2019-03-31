package dsl.ranking

import com.algolia.search.dsl.ranking.DSLIndexName
import indexA
import indexB
import shouldEqual
import kotlin.test.Test


internal class TestDSLIndexName {

    @Test
    fun default() {
        val dsl = DSLIndexName().apply {
            +"indexA"
            +indexB
        }

        dsl.build() shouldEqual listOf(indexA, indexB)
    }
}