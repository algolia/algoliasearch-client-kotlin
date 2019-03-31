package documentation.methods.indices

import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocMoveIndex : TestDocumentation() {

//    suspend fun [Index](#method-param-src).moveIndex(
//        [destination](#method-param-dest): __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun moveIndex() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.moveIndex(indexName)
            }
        }
    }
}