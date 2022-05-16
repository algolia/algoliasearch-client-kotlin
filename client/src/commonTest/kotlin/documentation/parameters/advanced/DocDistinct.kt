package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.settings.Distinct
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDistinct {

//    distinct: Distinct = Distinct(0|1|2|3)

    @Test
    fun snippet1() {
        runTest {
            val query = settings {
                distinct = Distinct(0)
                // distinct = Distinct(1)
                // distinct = Distinct(2)
            }

            index.setSettings(query)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                distinct = Distinct(1)
                // distinct = Distinct(0)
                // distinct = Distinct(2)
            }

            index.search(query)
        }
    }
}
