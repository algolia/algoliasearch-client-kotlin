package documentation.parameters.rule

import com.algolia.search.dsl.query
import com.algolia.search.dsl.ruleContexts
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocRuleContexts: TestDocumentation() {

//    ruleContexts {
//        +"context_value"
//        +...
//    }

    @Test
    fun query() {
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