package documentation.parameters.faceting

import com.algolia.search.dsl.facets
import com.algolia.search.dsl.query
import documentation.index
import runBlocking
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