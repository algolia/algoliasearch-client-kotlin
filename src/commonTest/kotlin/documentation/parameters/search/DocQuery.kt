package documentation.parameters.search

import com.algolia.search.model.search.Query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocQuery {

//    index.search(Query("my query"))

    @Test
    fun snippet1() {
        runBlocking {
            index.search(Query("my query"))
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            index.search(Query(""))
        }
    }
}