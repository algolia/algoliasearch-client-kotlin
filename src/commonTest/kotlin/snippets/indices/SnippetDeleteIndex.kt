package snippets.indices

import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetDeleteIndex : TestSnippets() {

//    suspend fun Index.deleteIndex(
//        requestOptions: __RequestOptions?__ = null
//    ): DeletionIndex

    @Test
    fun deleteIndex() {
        runBlocking {
            index.deleteIndex()
        }
    }
}