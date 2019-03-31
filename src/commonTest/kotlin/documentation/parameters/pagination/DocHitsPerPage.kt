package documentation.parameters.pagination

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocHitsPerPage : TestDocumentation() {

//    hitsPerPage = number_of_hits

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                hitsPerPage = 20
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                hitsPerPage = 10
            }

            index.search(query)
        }
    }
}