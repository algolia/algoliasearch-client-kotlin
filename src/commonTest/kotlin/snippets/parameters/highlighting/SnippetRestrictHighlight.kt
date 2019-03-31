package snippets.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetRestrictHighlight : TestSnippets() {

//    restrictHighlightAndSnippetArrays = true|false

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                restrictHighlightAndSnippetArrays = true
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                restrictHighlightAndSnippetArrays = true
            }

            index.search(query)
        }
    }
}