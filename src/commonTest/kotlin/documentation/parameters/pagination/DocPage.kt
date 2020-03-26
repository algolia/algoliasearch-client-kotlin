package documentation.parameters.pagination

import com.algolia.search.dsl.query
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocPage {

//    page: Int = page_number

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                page = 0
            }

            index.search(query)
        }
    }
}
