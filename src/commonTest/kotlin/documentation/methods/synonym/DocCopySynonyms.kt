package documentation.methods.synonym

import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocCopySynonyms : TestDocumentation() {

//    suspend fun [Index](#method-param-indexnamesrc).copySynonyms(
//        [destination](#method-param-indexnamedest) __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun copySynonyms() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.copySynonyms(indexName)
            }
        }
    }
}