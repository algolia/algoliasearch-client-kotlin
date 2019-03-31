package documentation.methods.synonym

import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocClearSynonyms : TestDocumentation() {

//    suspend fun Index.clearSynonyms(
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun clearSynonyms() {
        runBlocking {
            index.clearSynonyms(forwardToReplicas = true)
        }
    }
}