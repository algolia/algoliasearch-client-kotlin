package snippets.parameters.search

import com.algolia.search.model.search.Query
import runBlocking
import snippets.index
import kotlin.test.Test


internal class SnippetQuery {

//    index.search(Query("my query"))

    @Test
    fun query() {
        runBlocking {
            index.search(Query(""))
        }
    }
}