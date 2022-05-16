package documentation.parameters.pagination

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocHitsPerPage {

//    hitsPerPage: Int = number_of_hits

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                hitsPerPage = 20
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                hitsPerPage = 10
            }

            index.search(query)
        }
    }
}
