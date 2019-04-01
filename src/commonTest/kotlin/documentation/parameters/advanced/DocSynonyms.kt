package documentation.parameters.advanced

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocSynonyms {

//    synonyms: Boolean = true|false

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                synonyms = true
            }

            index.search(query)
        }
    }
}