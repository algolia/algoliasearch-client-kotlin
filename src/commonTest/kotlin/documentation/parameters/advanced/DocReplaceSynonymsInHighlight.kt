package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocReplaceSynonymsInHighlight {

//    replaceSynonymsInHighlight: Boolean = true|false

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                replaceSynonymsInHighlight = true
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                replaceSynonymsInHighlight = true
            }

            index.search(query)
        }
    }
}