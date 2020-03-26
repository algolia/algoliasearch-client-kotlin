package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocRestrictHighlight {

//    restrictHighlightAndSnippetArrays: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                restrictHighlightAndSnippetArrays = true
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                restrictHighlightAndSnippetArrays = true
            }

            index.search(query)
        }
    }
}
