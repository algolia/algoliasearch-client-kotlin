package documentation.methods.synonym

import documentation.index
import indexName
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocCopySynonyms {

//    suspend fun [Index](#method-param-indexnamesrc).copySynonyms(
//        [destination](#method-param-indexnamedest) __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.copySynonyms(indexName)
            }
        }
    }
}