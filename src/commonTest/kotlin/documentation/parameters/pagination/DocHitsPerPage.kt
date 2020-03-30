package documentation.parameters.pagination

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocHitsPerPage {

//    hitsPerPage: Int = number_of_hits

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                hitsPerPage = 20
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                hitsPerPage = 10
            }

            index.search(query)
        }
    }
}
