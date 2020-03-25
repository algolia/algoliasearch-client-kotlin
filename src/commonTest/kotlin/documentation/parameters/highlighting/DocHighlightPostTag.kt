package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocHighlightPostTag {

//    highlightPostTag: String = "closing_tag"

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                highlightPostTag = "</em>"
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                highlightPostTag = "</strong>"
            }

            index.search(query)
        }
    }
}
