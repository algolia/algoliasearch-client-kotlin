package snippets.synonym

import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


class SnippetClearSynonyms : TestSnippets() {

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