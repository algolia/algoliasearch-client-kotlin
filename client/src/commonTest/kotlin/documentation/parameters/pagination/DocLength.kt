package documentation.parameters.pagination

import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocLength {

//    length: Int = number_of_records

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                length = 4
            }

            index.search(query)
        }
    }
}
