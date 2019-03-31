package documentation.parameters.faceting

import com.algolia.search.dsl.facets
import com.algolia.search.dsl.query
import runBlocking
import documentation.index
import kotlin.test.Test


internal class DocFacets {

//    facets {
//        +"attribute"
//    }

    @Test
    fun snippet() {
        runBlocking {
            val query = query {
                query = "query"
                facets {
                    +"category"
                    +"author"
                }
            }

            index.search(query)
        }
    }
}