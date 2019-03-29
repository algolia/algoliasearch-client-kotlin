package snippets.parameters.search

import com.algolia.search.model.search.Query
import runBlocking
import snippets.index
import kotlin.test.Test


internal class SnippetQuery {

    @Test
    fun query() {
        runBlocking {
            index.search(Query("my query"))
        }
    }
}