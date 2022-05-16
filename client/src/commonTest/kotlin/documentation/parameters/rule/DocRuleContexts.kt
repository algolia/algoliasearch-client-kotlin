package documentation.parameters.rule

import com.algolia.search.dsl.query
import com.algolia.search.dsl.ruleContexts
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocRuleContexts {

//    ruleContexts {
//        +"context_value"
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                ruleContexts {
                    +"front_end"
                    +"website2"
                }
            }

            index.search(query)
        }
    }
}
