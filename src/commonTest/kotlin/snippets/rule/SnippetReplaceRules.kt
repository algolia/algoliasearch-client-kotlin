package snippets.rule

import com.algolia.search.model.rule.Rule
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetReplaceRules: TestSnippets() {

//    suspend fun Index.replaceAllRules(
//        #{rules}: __List<Rule>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun replaceRules() {
        runBlocking {
            val rules = listOf<Rule>()

            index.replaceAllRules(rules, forwardToReplicas = true)
        }
    }
}