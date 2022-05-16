package documentation.parameters.advanced

import com.algolia.search.dsl.analyticsTags
import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAnalyticsTags {

//    analyticsTags {
//        +"tag value"
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
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
