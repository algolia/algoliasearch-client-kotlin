package snippets.indexing

import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetClearObjects : TestSnippets() {

//    suspend fun Index.clearObjects(
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun deleteObjectBy() {
        runBlocking {
            index.clearObjects()
        }
    }
}