package documentation.parameters.advanced

import com.algolia.search.dsl.analyticsTags
import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocAnalyticsTags {

//    analyticsTags {
//        +"tag value"
//        +...
//    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                analyticsTags {
                    +"front_end"
                    +"website2"
                }
            }

            index.search(query)
        }
    }
}