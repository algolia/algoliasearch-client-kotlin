package dsl

import attributeA
import attributeB
import com.algolia.search.dsl.DSLAttributes
import shouldEqual
import kotlin.test.Test


internal class TestDSLAttributes {

    @Test
    fun default() {
        val dsl = DSLAttributes().apply {
            +"attributeA"
            +attributeB
        }

        dsl.build() shouldEqual listOf(attributeA, attributeB)
    }
}