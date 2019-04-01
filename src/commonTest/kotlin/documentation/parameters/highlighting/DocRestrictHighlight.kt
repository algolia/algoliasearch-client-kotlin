package documentation.parameters.highlighting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocRestrictHighlight {

//    restrictHighlightAndSnippetArrays: Boolean = true|false

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
            val query = query("query") {
                restrictHighlightAndSnippetArrays = true
            }

            index.search(query)
        }
    }
}