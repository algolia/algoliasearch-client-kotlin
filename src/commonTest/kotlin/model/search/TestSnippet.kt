package model.search

import attributeA
import attributeAll
import attributeB
import com.algolia.search.model.search.Snippet
import kotlin.test.Test
import shouldEqual


internal class TestSnippet {

    @Test
    fun raw() {
        Snippet(attributeA).raw shouldEqual attributeA.raw
        Snippet(attributeAll).raw shouldEqual "*"
        Snippet(attributeAll, 20).raw shouldEqual "*:20"
        Snippet(attributeB, 10).raw shouldEqual "$attributeB:10"
    }
}