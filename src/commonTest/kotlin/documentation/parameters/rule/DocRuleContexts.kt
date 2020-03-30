package documentation.parameters.rule

import com.algolia.search.dsl.query
import com.algolia.search.dsl.ruleContexts
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocRuleContexts {

//    ruleContexts {
//        +"context_value"
//        +...
//    }

    @Test
    fun snippet1() {
        runBlocking {
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
