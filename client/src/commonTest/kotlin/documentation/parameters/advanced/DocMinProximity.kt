package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocMinProximity {

//    minProximity: Int = integer // from 1 to 7

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                minProximity = 1
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                minProximity = 2
            }

            index.search(query)
        }
    }
}
