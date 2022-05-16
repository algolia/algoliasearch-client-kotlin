package documentation.parameters.filtering

import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSumOrFiltersScores {

//     sumOrFiltersScores: Boolean = true|false

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                sumOrFiltersScores = true
            }

            index.search(query)
        }
    }
}
