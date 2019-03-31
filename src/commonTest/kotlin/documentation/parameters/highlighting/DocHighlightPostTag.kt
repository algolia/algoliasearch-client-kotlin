package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocHighlightPostTag : TestDocumentation() {

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
            val query = query("query") {
                highlightPostTag = "</strong>"
            }

            index.search(query)
        }
    }
}