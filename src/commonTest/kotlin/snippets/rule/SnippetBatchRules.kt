package snippets.rule

import com.algolia.search.model.rule.Rule
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetBatchRules : TestSnippets() {

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