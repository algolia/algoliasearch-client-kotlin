package snippets.parameters.highlighting

import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetHighlightPreTag : TestSnippets() {

//    highlightPostTag = "opening_tag"

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                highlightPreTag = "<em>"
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = com.algolia.search.dsl.query {
                highlightPreTag = "<strong>"
            }

            index.search(query)
        }
    }
}