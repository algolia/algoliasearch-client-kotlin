package documentation.parameters.advanced

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocPercentileComputation {

//    percentileComputation: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                percentileComputation = true
            }

            index.search(query)
        }
    }
}
