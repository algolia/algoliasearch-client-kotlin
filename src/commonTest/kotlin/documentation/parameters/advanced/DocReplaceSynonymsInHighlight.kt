package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocReplaceSynonymsInHighlight : TestDocumentation() {

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