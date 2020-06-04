package documentation.parameters.pagination

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocOffset {

//    offset: Int = record_number

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                offset = 4
            }

            index.search(query)
        }
    }
}
