package documentation.guides.results.filtering

import com.algolia.search.dsl.filters
import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideFilterDate {

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                filters {
                    and {
                        comparison("date_timestamp", Greater, 1538352000000)
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                filters {
                    and {
                        range("date_timestamp", 1538352000000..1540944000000)
                    }
                }
            }

            index.search(query)
        }
    }
}
