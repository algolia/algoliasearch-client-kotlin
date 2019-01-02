package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.Snippet
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestSnippet : TestSerializer<Snippet>(Snippet) {

    override val items = listOf(
        Snippet(attributeA),
        Snippet(attributeB, 10)
    )
}