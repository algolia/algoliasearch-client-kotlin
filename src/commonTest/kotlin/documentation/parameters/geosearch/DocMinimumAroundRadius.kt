package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocMinimumAroundRadius {

//    minimumAroundRadius: Int = radius

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                minimumAroundRadius = 1000 // 1km
            }

            index.search(query)
        }
    }
}