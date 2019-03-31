package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocAroundPrecision {

//    aroundPrecision: Int = number_of_meters

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                aroundPrecision = 100
            }

            index.search(query)
        }
    }
}