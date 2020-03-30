package documentation.guides.results.filtering

import com.algolia.search.dsl.filters
import com.algolia.search.dsl.query
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class GuideFilterNumeric {

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                filters {
                    and {
                        comparison("price", Less, 100)
                    }
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                filters {
                    and {
                        range("price", 100..200)
                    }
                }
            }

            index.search(query)
        }
    }
}
