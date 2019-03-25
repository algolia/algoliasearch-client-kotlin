package snippets.rule

import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetClearRules : TestSnippets() {

//    suspend fun Index.clearRules(
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun clearRules() {
        runBlocking {
            index.clearRules(forwardToReplicas = true)
        }
    }
}