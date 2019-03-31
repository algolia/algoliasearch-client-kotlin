package dsl.highlighting

import attributeA
import attributeB
import com.algolia.search.dsl.highlighting.DSLSnippet
import com.algolia.search.model.search.Snippet
import shouldEqual
import kotlin.test.Test


internal class TestDSLSnippet {

    @Test
    fun default() {
        val dsl = DSLSnippet().apply {
            +"attributeA"
            +attributeB
            +Snippet(attributeA)
        }

        dsl.build() shouldEqual listOf(
            Snippet(attributeA),
            Snippet(attributeB),
            Snippet(attributeA)
        )
    }

    @Test
    fun limit() {
        val dsl = DSLSnippet().apply {
            +("attributeA" limit 10)
            +(attributeB limit 20)
        }

        dsl.build() shouldEqual listOf(
            Snippet(attributeA, 10),
            Snippet(attributeB, 20)
        )
    }
}