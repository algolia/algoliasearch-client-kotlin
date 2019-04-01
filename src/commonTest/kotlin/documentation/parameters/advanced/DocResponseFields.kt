package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.responseFields
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocResponseFields : TestDocumentation() {

//    responseFields {
//        +ResponseFields
//        +...
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                responseFields {
                    +Hits
                    +HitsPerPage
                    +NbPages
                    +Page
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                responseFields {
                    +Hits
                    +Facets
                }
            }

            index.search(query)
        }
    }
}