package documentation.parameters.advanced

import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
class DocABTest {

//    enableABTest: Boolean = true|false

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                enableABTest = false
            }
            index.search(query)
        }
    }
}
