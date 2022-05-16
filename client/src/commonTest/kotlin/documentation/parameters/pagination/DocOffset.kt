package documentation.parameters.pagination

import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocOffset {

//    offset: Int = record_number

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                offset = 4
            }

            index.search(query)
        }
    }
}
