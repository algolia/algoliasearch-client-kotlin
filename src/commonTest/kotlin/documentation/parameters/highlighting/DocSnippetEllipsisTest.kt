package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSnippetEllipsisTest {

//    snippetEllipsisText: String = "text"

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                snippetEllipsisText = "…"
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                snippetEllipsisText = ""
            }

            index.search(query)
        }
    }
}
