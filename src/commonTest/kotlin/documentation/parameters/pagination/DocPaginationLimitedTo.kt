package documentation.parameters.pagination

import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocPaginationLimitedTo {

//   paginationLimitedTo: Int = number_of_records

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                paginationLimitedTo = 1000
            }

            index.setSettings(settings)
        }
    }
}