package model.search

import attributeA
import attributeB
import com.algolia.search.dsl.all
import com.algolia.search.model.search.Snippet
import shouldEqual
import kotlin.test.Test

internal class TestSnippet {

    @Test
    fun raw() {
        Snippet(attributeA).raw shouldEqual attributeA.raw
        Snippet(all).raw shouldEqual "*"
        Snippet(all, 20).raw shouldEqual "*:20"
        Snippet(attributeB, 10).raw shouldEqual "$attributeB:10"
    }
}
