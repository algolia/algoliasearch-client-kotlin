package documentation.parameters.strategy

import com.algolia.search.dsl.disableExactOnAttributes
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocDisableExactOnAttributes : TestDocumentation() {

//    disableExactOnAttributes { +"attribute" }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                disableExactOnAttributes { +"keywords" }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                disableExactOnAttributes { +"keywords" }
            }

            index.search(query)
        }
    }
}