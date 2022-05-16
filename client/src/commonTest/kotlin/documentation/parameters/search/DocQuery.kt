package documentation.parameters.search

import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocQuery {

//    index.search(Query("my query"))

    @Test
    fun snippet1() {
        runTest {
            index.search(Query("my query"))
        }
    }

    @Test
    fun snippet2() {
        runTest {
            index.search(Query(""))
        }
    }
}
