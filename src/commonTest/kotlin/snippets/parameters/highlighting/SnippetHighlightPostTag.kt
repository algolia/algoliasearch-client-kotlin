package snippets.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetHighlightPostTag : TestSnippets() {

//    highlightPostTag = "closing_tag"

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                highlightPostTag = "</em>"
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                highlightPostTag = "</strong>"
            }

            index.search(query)
        }
    }
}