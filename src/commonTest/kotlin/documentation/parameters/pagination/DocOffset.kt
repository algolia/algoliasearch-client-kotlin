package documentation.parameters.pagination

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocOffset {

//    offset = record_number

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                offset = 4
            }

            index.search(query)
        }
    }
}