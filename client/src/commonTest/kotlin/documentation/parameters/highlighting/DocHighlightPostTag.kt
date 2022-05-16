package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocHighlightPostTag {

//    highlightPostTag: String = "closing_tag"

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                highlightPostTag = "</em>"
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                highlightPostTag = "</strong>"
            }

            index.search(query)
        }
    }
}
