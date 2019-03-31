package documentation.parameters.filtering

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocSumOrFiltersScores {

//     sumOrFiltersScores: Boolean = true|false

    @Test
    fun query() {
        runBlocking {
            val query = query {
                sumOrFiltersScores = true
            }

            index.search(query)
        }
    }
}