package dsl

import com.algolia.search.dsl.DSLReplicas
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