package dsl.ranking

import com.algolia.search.dsl.ranking.DSLReplicas
import indexA
import indexB
import shouldEqual
import kotlin.test.Test


internal class TestDSLReplicas {

    @Test
    fun default() {
        val dsl = DSLReplicas().apply {
            +"indexA"
            +indexB
        }

        dsl.build() shouldEqual listOf(indexA, indexB)
    }
}