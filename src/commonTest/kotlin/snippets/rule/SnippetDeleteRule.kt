package snippets.rule

import com.algolia.search.model.ObjectID
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetDeleteRule : TestSnippets() {

//    suspend fun Index.deleteRule(
//        #{objectID}: __ObjectID__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun deleteRule() {
        runBlocking {
            index.deleteRule(ObjectID("nyID"), forwardToReplicas = true)
        }
    }
}