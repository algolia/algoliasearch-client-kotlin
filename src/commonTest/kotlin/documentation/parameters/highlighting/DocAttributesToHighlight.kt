package documentation.parameters.highlighting

import com.algolia.search.dsl.attributesToHighlight
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAttributesToHighlight {

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
            val query = query("query") {
                attributesToHighlight {
                    +"*"
                }
            }

            index.search(query)
        }
    }
}