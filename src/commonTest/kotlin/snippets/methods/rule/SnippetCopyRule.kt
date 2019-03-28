package snippets.methods.rule

import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetCopyRule : TestSnippets() {

//    suspend fun [Index](#method-param-indexnamesrc).copyRules(
//        [destination](#method-param-indexnamedest): __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun copyRule() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.copyRules(indexName)
            }
        }
    }
}