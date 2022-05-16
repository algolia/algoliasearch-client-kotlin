package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import com.algolia.search.model.search.AroundPrecision
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAroundPrecision {

//    aroundPrecision: Int = number_of_meters

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                aroundPrecision = AroundPrecision.Int(100)
            }

            index.search(query)
        }
    }
}
