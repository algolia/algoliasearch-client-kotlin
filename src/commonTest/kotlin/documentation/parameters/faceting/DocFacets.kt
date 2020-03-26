package documentation.parameters.faceting

import com.algolia.search.dsl.facets
import com.algolia.search.dsl.query
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocFacets {

//    facets {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runBlocking {
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
