package documentation.parameters.typos

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocAllowTyposOnNumericToken : TestDocumentation() {

//    allowTyposOnNumericTokens: Boolean = true|false

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                allowTyposOnNumericTokens = false
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                allowTyposOnNumericTokens = false
            }

            index.search(query)
        }
    }
}