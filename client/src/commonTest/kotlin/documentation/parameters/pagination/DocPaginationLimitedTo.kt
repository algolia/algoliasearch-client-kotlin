package documentation.parameters.pagination

import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocPaginationLimitedTo {

//   paginationLimitedTo: Int = number_of_records

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                paginationLimitedTo = 1000
            }

            index.setSettings(settings)
        }
    }
}
