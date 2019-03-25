package snippets.apikey

import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetListAPIKey : TestSnippets() {

//    suspend fun ClientSearch.listAPIKeys(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListAPIKey

    @Test
    fun listAPIKey() {
        runBlocking {
            client.listAPIKeys()
        }
    }
}