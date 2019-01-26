package serialize

import attributeA
import attributeB
import com.algolia.search.saas.model.enums.Snippet
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestSnippet : TestSerializer<Snippet>(Snippet) {

    override val items = listOf(
        Snippet(attributeA) to JsonLiteral(
            Snippet(
                attributeA
            ).raw),
        snippet to json
    )

    companion object {

        val snippet = Snippet(attributeB, 10)
        val json = JsonLiteral(snippet.raw)
    }
}