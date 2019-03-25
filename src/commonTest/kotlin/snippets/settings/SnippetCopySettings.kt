package snippets.settings

import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.TestSnippets
import kotlin.test.Test


class SnippetCopySettings : TestSnippets() {

//    suspend fun [Index](#method-param-indexnamesrc).copySettings(
//        [destination](#method-param-indexnamedest): __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun copySettings() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.copySettings(indexName)
            }
        }
    }
}