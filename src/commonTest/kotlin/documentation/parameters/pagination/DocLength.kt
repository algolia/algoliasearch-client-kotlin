package documentation.parameters.pagination

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocLength {

    //    length: Int = number_of_records

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                length = 4
            }

            index.search(query)
        }
    }
}