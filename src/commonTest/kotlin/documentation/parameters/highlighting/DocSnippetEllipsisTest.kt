package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocSnippetEllipsisTest : TestDocumentation() {

//    snippetEllipsisText = "text"

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                snippetEllipsisText = "…"
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                snippetEllipsisText = ""
            }

            index.search(query)
        }
    }
}