package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocHighlightPreTag : TestDocumentation() {

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
            val query = query("query") {
                highlightPreTag = "<strong>"
            }

            index.search(query)
        }
    }
}