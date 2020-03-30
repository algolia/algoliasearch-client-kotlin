package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocMinimumAroundRadius {

//    minimumAroundRadius: Int = radius

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                minimumAroundRadius = 1000 // 1km
            }

            index.search(query)
        }
    }
}
