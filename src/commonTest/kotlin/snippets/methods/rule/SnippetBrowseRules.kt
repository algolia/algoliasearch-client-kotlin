package snippets.methods.rule

import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetBrowseRules : TestSnippets() {

//    suspend fun Index.browseRules(
//        query: __RuleQuery__ = RuleQuery(),
//        requestOptions: __RequestOptions?__ = null,
//        block: __suspend (ResponseRules) -> Unit__
//    )

    @Test
    fun browseRules() {
        runBlocking {
            index.browseRules {
                println(it)
            }
        }
    }
}