package documentation.methods.rule

import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocClearRules : TestDocumentation() {

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