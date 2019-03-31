package documentation.parameters.search

import com.algolia.search.model.search.Query
import runBlocking
import documentation.index
import kotlin.test.Test


internal class DocQuery {

//    index.search(Query("my query"))

    @Test
    fun query() {
        runBlocking {
            index.search(Query(""))
        }
    }
}