package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import runBlocking
import documentation.TestDocumentation
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
            val query = query {
                highlightPostTag = "</strong>"
            }

            index.search(query)
        }
    }
}