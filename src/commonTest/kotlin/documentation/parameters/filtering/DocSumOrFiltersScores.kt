package documentation.parameters.filtering

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSumOrFiltersScores {

//     sumOrFiltersScores: Boolean = true|false

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                sumOrFiltersScores = true
            }

            index.search(query)
        }
    }
}