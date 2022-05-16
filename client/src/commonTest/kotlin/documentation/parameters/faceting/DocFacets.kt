package documentation.parameters.faceting

import com.algolia.search.dsl.facets
import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocFacets {

//    facets {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                facets {
                    +"category"
                    +"author"
                }
            }

            index.search(query)
        }
    }
}
