package documentation.parameters.advanced

import com.algolia.search.dsl.query
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
class DocABTest {

//    enableABTest: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                enableABTest = false
            }
            index.search(query)
        }
    }
}
