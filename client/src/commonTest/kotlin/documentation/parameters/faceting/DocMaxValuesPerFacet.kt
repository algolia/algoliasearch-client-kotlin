package documentation.parameters.faceting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocMaxValuesPerFacet {

//    maxValuesPerFacet: Int = maxValue

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                maxValuesPerFacet = 100
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                maxValuesPerFacet = 50
            }

            index.search(query)
        }
    }
}
