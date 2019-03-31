package documentation.methods.rule

import com.algolia.search.model.rule.Rule
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocBatchRules : TestDocumentation() {

//    suspend fun Index.saveRules(
//        #{rules}: __List<Rule>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        #{clearExistingRules}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun batchRules() {
        runBlocking {
            val rules = listOf<Rule>()

            index.saveRules(rules, forwardToReplicas = true, clearExistingRules = true)
        }
    }
}