package serialize

import com.algolia.search.saas.data.HighlightResult
import highlightResult
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestHighlightResult : TestSerializer<HighlightResult>(HighlightResult.serializer()) {

    override val items = listOf(
        highlightResult
    )
}