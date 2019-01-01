package serialize

import attributeA
import attributeB
import client.data.Snippet
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestSnippet : TestSerializer<Snippet>(Snippet) {

    private val snippetA = Snippet(attributeA)
    private val snippetB = Snippet(attributeB, 10)

    override val item = listOf(
        snippetA to JsonPrimitive(snippetA.raw),
        snippetB to JsonPrimitive(snippetB.raw)
    )
}