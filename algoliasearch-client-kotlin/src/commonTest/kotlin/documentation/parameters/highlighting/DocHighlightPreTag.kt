package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocHighlightPreTag {

//    highlightPreTag: String = "opening_tag"

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                highlightPreTag = "<em>"
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                highlightPreTag = "<strong>"
            }

            index.search(query)
        }
    }
}
