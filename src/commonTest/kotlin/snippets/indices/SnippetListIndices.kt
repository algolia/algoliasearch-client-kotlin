package snippets.indices

import runBlocking
import snippets.client
import kotlin.test.Test


internal class SnippetListIndices {

//    suspend fun ClientSearch.listIndices(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListIndices

    @Test
    fun listIndices() {
        runBlocking {
            val indices = client.listIndices()

            println(indices)
        }
    }
}