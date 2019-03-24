package snippets.personalization

import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetGetStrategy : TestSnippets() {

//    suspend fun ClientSearch.getPersonalizationStrategy(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponsePersonalizationStrategy

    @Test
    fun getStrategy() {
        runBlocking {
            client.getPersonalizationStrategy()
        }
    }
}