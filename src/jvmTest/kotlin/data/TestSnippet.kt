package data

import attributeA
import attributeAll
import attributeB
import com.algolia.search.saas.model.Snippet
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSnippet {

    @Test
    fun raw() {
        Snippet(attributeA).raw shouldEqual attributeA.raw
        Snippet(attributeAll).raw shouldEqual "*"
        Snippet(attributeAll, 20).raw shouldEqual "*:20"
        Snippet(attributeB, 10).raw shouldEqual "$attributeB:10"
    }
}