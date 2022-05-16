package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import com.algolia.search.model.search.AroundRadius
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAroundRadius {

//    aroundRadius: AroundRadius = [AroundRadius.InMeters](#parameter-option-radius-in-meters)
//    | [AroundRadius.All](#parameter-option-all)

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                aroundRadius = AroundRadius.InMeters(1000)
            }

            index.search(query)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                aroundRadius = AroundRadius.All
            }

            index.search(query)
        }
    }
}
