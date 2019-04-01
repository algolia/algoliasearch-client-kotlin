package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocAdvancedSyntax: TestDocumentation() {

//    advancedSyntax: Boolean = true|false

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                advancedSyntax = true
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                advancedSyntax = true
            }

            index.search(query)
        }
    }
}