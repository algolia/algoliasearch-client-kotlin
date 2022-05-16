package documentation.guides.results.filtering

import com.algolia.search.dsl.filters
import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideFilterTag {

    @Test
    fun snippet1() {
        runTest {
            val query = query("harry") {
                filters {
                    and {
                        tag("politics")
                    }
                }
            }

            index.search(query)
        }
    }
}
