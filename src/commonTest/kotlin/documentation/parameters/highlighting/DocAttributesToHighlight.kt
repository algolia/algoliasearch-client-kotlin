package documentation.parameters.highlighting

import com.algolia.search.dsl.attributesToHighlight
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocAttributesToHighlight : TestDocumentation() {

//    attributesToHighlight {
//        +"attribute"
//        +"*" // returns all attributes in the index not just searchable attributes
//    }

    @Test
    fun snippetSettings() {
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
    fun snippetQuery() {
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