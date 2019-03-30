package snippets.parameters.filtering

import com.algolia.search.dsl.query
import runBlocking
import snippets.index
import kotlin.test.Test


internal class SnippetSumOrFiltersScores {

    @Test
    fun parameter() {
        query {
            sumOrFiltersScores = true
        }
    }

    @Test
    fun snippet() {
        runBlocking {
            val query = query {
                sumOrFiltersScores = true
            }

            index.search(query)
        }
    }
}