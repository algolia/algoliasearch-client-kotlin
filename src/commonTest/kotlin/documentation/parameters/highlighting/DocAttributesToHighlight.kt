package documentation.parameters.highlighting

import com.algolia.search.dsl.attributesToHighlight
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocAttributesToHighlight : TestDocumentation() {

//    attributesToHighlight {
//        +"attribute"
//        +"*" // returns all attributes in the index not just searchable attributes
//    }

    @Test
    fun sSettings() {
        runBlocking {
            val settings = settings {
                attributesToHighlight {
                    +"author"
                    +"title"
                    +"content"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                attributesToHighlight {
                    +"*"
                }
            }

            index.search(query)
        }
    }
}