package documentation.parameters.filtering

import com.algolia.search.dsl.query
import runBlocking
import documentation.index
import kotlin.test.Test


internal class DocSumOrFiltersScores {

//     sumOrFiltersScores: Boolean = true|false

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