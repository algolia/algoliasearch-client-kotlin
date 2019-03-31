package documentation.parameters.pagination

import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocPaginationLimitedTo : TestDocumentation() {

//   paginationLimitedTo = number_of_records

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                paginationLimitedTo = 1000
            }

            index.setSettings(settings)
        }
    }
}