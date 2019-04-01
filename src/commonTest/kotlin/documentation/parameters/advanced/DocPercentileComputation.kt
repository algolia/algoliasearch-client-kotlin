package documentation.parameters.advanced

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocPercentileComputation {

//    percentileComputation: Boolean = true|false

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                percentileComputation = true
            }

            index.search(query)
        }
    }
}