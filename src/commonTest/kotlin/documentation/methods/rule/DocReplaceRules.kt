package documentation.methods.rule

import com.algolia.search.model.rule.Rule
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocReplaceRules: TestDocumentation() {

//    suspend fun Index.replaceAllRules(
//        #{rules}: __List<Rule>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun replaceRules() {
        // Fetch your rules
        runBlocking {
            val rules = listOf<Rule>()

            index.replaceAllRules(rules, forwardToReplicas = true)
        }
    }
}