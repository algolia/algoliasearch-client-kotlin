package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import documentation.index
import runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocMinimumAroundRadius {

//    minimumAroundRadius: Int = radius

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                minimumAroundRadius = 1000 // 1km
            }

            index.search(query)
        }
    }
}
