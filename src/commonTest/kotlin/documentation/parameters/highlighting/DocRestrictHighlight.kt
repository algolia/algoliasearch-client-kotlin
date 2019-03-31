package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocRestrictHighlight : TestDocumentation() {

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