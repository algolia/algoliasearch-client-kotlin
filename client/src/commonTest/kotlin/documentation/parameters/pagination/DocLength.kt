package documentation.parameters.pagination

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocLength {

//    length: Int = number_of_records

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                length = 4
            }

            index.search(query)
        }
    }
}
