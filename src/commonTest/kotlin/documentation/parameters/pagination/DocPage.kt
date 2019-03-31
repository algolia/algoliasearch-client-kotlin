package documentation.parameters.pagination

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocPage {

//    page = page_number

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                page = 0
            }

            index.search(query)
        }
    }
}