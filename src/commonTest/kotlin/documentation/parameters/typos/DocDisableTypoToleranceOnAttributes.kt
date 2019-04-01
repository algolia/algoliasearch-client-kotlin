package documentation.parameters.typos

import com.algolia.search.dsl.disableTypoToleranceOnAttributes
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocDisableTypoToleranceOnAttributes : TestDocumentation() {

//    disableTypoToleranceOnAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                disableTypoToleranceOnAttributes {
                    +"sku"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                disableTypoToleranceOnAttributes {
                    +"sku"
                }
            }

            index.search(query)
        }
    }
}